package com.example.board.dao.article;

import java.util.ArrayList;

import com.example.board.vo.article.ArticleVo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IArticleDao {
    
	public int createArticle(
		ArticleVo articleVo
	);
	
	public void updateGroupId(
		int groupId
	);

	public void createReply(
		ArticleVo articleVo
	);

	public void replyShape(
		@Param("_replyGroup") String groupIndex, 
		@Param("_replyStep") int replyStep
	);

    public ArrayList<ArticleVo> getList(
		@Param("_pageNum") int cPageNum, 
		@Param("_contentNum") int cContentNum,
		@Param("_titleKeyword") String titleKeyword,
		@Param("_writerKeyword") String writerKeyword
	);

	public ArticleVo findByKey(
		@Param("_keyIdx") String keyIdx
	);

	public void hitUp(
		@Param("_keyIdx") String keyIdx
	);

	public void deleteArticle(
		@Param("_keyIdx") String keyIdx
	);

	public void updateArticle(
		@Param("_keyIdx") String keyIdx, 
		@Param("_title") String title, 
		@Param("_content") String content, 
		@Param("_files") String files
	);

	public int totalCount(
		@Param("_titleKeyword") String titleKeyword,
		@Param("_writerKeyword") String writerKeyword		
	);

}