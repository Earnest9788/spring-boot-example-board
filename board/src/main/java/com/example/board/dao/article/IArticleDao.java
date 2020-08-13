package com.example.board.dao.article;

import java.util.ArrayList;

import com.example.board.vo.article.ArticleVo;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IArticleDao {
    
    public void createArticle(ArticleVo articleVo);

    public ArrayList<ArticleVo> getList();

}