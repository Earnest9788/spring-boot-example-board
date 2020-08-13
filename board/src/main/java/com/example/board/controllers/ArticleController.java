package com.example.board.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.board.services.article.IArticleService;
import com.example.board.vo.article.ArticleVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    
    @Autowired
    IArticleService articleService;

    @PostMapping("/api/article/regist")
    public String reigstArticle(HttpServletRequest req, ArticleVo articleVo) {

        articleService.regist(req, articleVo);

        return "redirect:/";

    }

    @GetMapping("/")
    public String getArticleList(Model model) {

        ArrayList<ArticleVo> articleList = articleService.list();
        model.addAttribute("articleList", articleList);

        return "pages/home";

    }

}