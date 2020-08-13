package com.example.board.services.article;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.board.dao.article.IArticleDao;
import com.example.board.utils.CustomUtil;
import com.example.board.vo.article.ArticleVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService implements IArticleService {

    @Autowired
    private CustomUtil customUtil;

    @Autowired
    private IArticleDao articleDao;

    @Override
    public void regist(HttpServletRequest req, ArticleVo articleVo) {

        HttpSession session = req.getSession(true);
        String writerId = (String) session.getAttribute("memberId");

        articleVo.setWriterId(writerId);
        articleVo.setRegiDate(customUtil.getCurrentTime());

        articleDao.createArticle(articleVo);

    }
    
}