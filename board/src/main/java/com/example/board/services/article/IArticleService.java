package com.example.board.services.article;

import javax.servlet.http.HttpServletRequest;

import com.example.board.vo.article.ArticleVo;

public interface IArticleService {
    
    public void regist(HttpServletRequest req, ArticleVo articleVo);

}