package com.example.board.dao.article;

import java.util.ArrayList;

import com.example.board.vo.article.ArticleVo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IArticleDao {
    
    public void createArticle(ArticleVo articleVo);

    public ArrayList<ArticleVo> getList();

	public ArticleVo findByKey(@Param("_keyIdx") String keyIdx);

	public void hitUp(@Param("_keyIdx") String keyIdx);

	public void deleteArticle(@Param("_keyIdx") String keyIdx);

	public void updateArticle(@Param("_keyIdx") String keyIdx, @Param("_title") String title, @Param("_content") String content);

}