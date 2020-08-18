package com.example.board.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.board.services.article.IArticleService;
import com.example.board.utils.CustomUtil;
import com.example.board.vo.article.ArticleVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class ArticleController {

    @Autowired
    private CustomUtil customUtil;

    @Autowired
    private IArticleService articleService;

    @InitBinder
    public void InitBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    // 글 리스트 가져오기
    @GetMapping(value = { "/", "/home" })
    public String getArticleList(
        @RequestParam(defaultValue = "1") String pageNum,
        @RequestParam(defaultValue = "10") String contentNum,
        HttpServletRequest req,     
        Model model
    ) {

        articleService.list(pageNum, contentNum, req, model);
        return "pages/home";

    }

    // 글 가져오기
    @GetMapping("/article")
    public String getArticleDetail(HttpServletRequest req, Model model) {

        ArticleVo article = articleService.detail(req.getParameter("keyIdx"), true);
        model.addAttribute("article", article);

        return "pages/article/detail";

    }

    // 글쓰기 폼 가져오기
    @GetMapping("/article/form")
    public String getWriteForm(HttpServletRequest req, Model model) {

        String keyIdx = req.getParameter("keyIdx");

        if (keyIdx != null) {
            ArticleVo article = articleService.detail(keyIdx, false);
            model.addAttribute("article", article);
        }

        return "pages/article/write";
    }

    // 답변 폼 가져오기
    @GetMapping("/article/reply")
    public String getReplyForm(HttpServletRequest req, Model model) {

        String keyIdx = req.getParameter("keyIdx"); // 원글 키인덱스

        if (keyIdx != null) {
            ArticleVo article = articleService.detail(keyIdx, false);
            model.addAttribute("article", article);
        }

        return "pages/article/reply";

    }

    // 글쓰기
    @PostMapping("/api/article/regist")
    public String reigstArticle(MultipartHttpServletRequest mReq, ArticleVo articleVo,
            BindingResult result, HttpServletRequest req) throws Exception {

        String fileList = customUtil.uploadFile(mReq);

        if (result.hasErrors()) {
            return "redirect:/article/form?fail";

        } else {
            articleService.regist(req, articleVo, fileList);
            return "redirect:/";
        }

    }

    // 답변달기
    @PostMapping("/api/article/reply")
    public String registReply(MultipartHttpServletRequest mReq, ArticleVo articleVo,
    BindingResult result, HttpServletRequest req) throws Exception {
        String fileList = customUtil.uploadFile(mReq);

        articleService.reply(req, articleVo, fileList);

        return "redirect:/";
    }

    // 글 업데이트
    @PostMapping("/api/article/update") 
    public String updateArticle(MultipartHttpServletRequest mReq, HttpServletRequest req, @Valid ArticleVo articleVo, BindingResult result) {

        List<MultipartFile> fileList = mReq.getFiles("file");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(req.getParameter("files")); // 기존 파일들 저장.
        stringBuilder.append(",");
        
        for (MultipartFile mf : fileList) {
            try {
                String fileName = mf.getOriginalFilename();

                mf.transferTo(new File("/Users/kkw10/my-asset", fileName));
                stringBuilder.append(fileName); // 새로 추가된 파일들 저장.
                stringBuilder.append(",");

            } catch (IllegalStateException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (stringBuilder.length() > 0) {
            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        }

        articleVo.setFiles(stringBuilder.toString());

        if (result.hasErrors()) {
            return "redirect:/article/form?fail&keyIdx=" + req.getParameter("keyIdx");

        } else {
            articleService.update(req.getParameter("keyIdx"), articleVo);
            return "redirect:/";
        }

    }

    // 글 삭제
    @GetMapping("/api/article/delete")
    public String deleteArticle(HttpServletRequest req) {

        articleService.delete(req.getParameter("keyIdx"));

        return "redirect:/";

    }

    // 다운로드
    @GetMapping("/api/file/download")
    public ResponseEntity<Resource> download(HttpServletRequest req) throws IOException {
        Path path = Paths.get("/Users/kkw10/my-asset/" + req.getParameter("file"));
        String contentType = Files.probeContentType(path);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);
    
        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

}