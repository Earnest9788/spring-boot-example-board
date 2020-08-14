package com.example.board.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.example.board.services.article.IArticleService;
import com.example.board.vo.article.ArticleVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ArticleController {
    
    @Autowired
    private IArticleService articleService;

    @InitBinder
	public void InitBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

    @GetMapping(value={"/", "/home"})
    public String getArticleList(
        @RequestParam(defaultValue = "1") String pageNum, 
        @RequestParam(defaultValue = "10") String contentNum,
        Model model
    ) {

        articleService.list(pageNum, contentNum, model);
        return "pages/home";

    }

    @GetMapping("/article/form")
    public String getWriteForm(HttpServletRequest req, Model model) {

        String keyIdx = req.getParameter("keyIdx");

        if (keyIdx != null) {
            ArticleVo article = articleService.detail(keyIdx);
            model.addAttribute("article", article);
        }

        return "pages/article/write";
    }
        
    @PostMapping("/api/article/regist")
    public String reigstArticle(@Valid ArticleVo articleVo, BindingResult result, HttpServletRequest req ) {

        if (result.hasErrors()) {
            return "redirect:/article/form?fail";

        } else {
            articleService.regist(req, articleVo);
            return "redirect:/";
        }

    }

    @PostMapping("/api/article/update") 
    public String updateArticle(HttpServletRequest req, @Valid ArticleVo articleVo, BindingResult result) {

        if (result.hasErrors()) {
            return "redirect:/article/form?fail&keyIdx=" + req.getParameter("keyIdx");

        } else {
            articleService.update(req.getParameter("keyIdx"), articleVo);
            return "redirect:/";
        }

    }

    @GetMapping("/article")
    public String getArticleDetail(HttpServletRequest req, Model model) {

        ArticleVo article = articleService.detail(req.getParameter("keyIdx"));
        model.addAttribute("article", article);

        return "pages/article/detail";

    }

    @GetMapping("/api/article/delete")
    public String deleteArticle(HttpServletRequest req) {

        articleService.delete(req.getParameter("keyIdx"));

        return "redirect:/";

    }

}