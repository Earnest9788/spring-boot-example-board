package com.example.board.services.article;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.example.board.vo.article.ArticleVo;

import org.springframework.ui.Model;

public interface IArticleService {
    
    public void regist(HttpServletRequest req, ArticleVo articleVo);

    public void list(HttpServletRequest req, Model model);

	public ArticleVo detail(String keyIdx);

	public void delete(String keyIdx);

	public void update(String string, ArticleVo articleVo);

}