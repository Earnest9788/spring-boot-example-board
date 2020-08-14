package com.example.board.services.article;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.example.board.vo.article.ArticleVo;

import org.springframework.ui.Model;

public interface IArticleService {
	
	public void list(String pageNum, String contentNum, Model model);

    public void regist(HttpServletRequest req, ArticleVo articleVo);

	public ArticleVo detail(String keyIdx);

	public void delete(String keyIdx);

	public void update(String string, ArticleVo articleVo);

}