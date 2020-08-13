package com.example.board.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.board.services.article.IArticleService;
import com.example.board.vo.article.ArticleVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

}