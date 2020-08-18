package com.example.board.services.article;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.board.vo.article.ArticleVo;

import org.springframework.ui.Model;

public interface IArticleService {
	
	public void list(String pageNum, String contentNum, HttpServletRequest req, Model model);

    public void regist(HttpServletRequest req, ArticleVo articleVo, String files);

	public ArticleVo detail(String keyIdx, boolean isHit);

	public void delete(String keyIdx);

	public void update(String parameter, @Valid ArticleVo articleVo);

	public void reply(HttpServletRequest req, ArticleVo articleVo, String fileList);

}