package com.example.board.services.article;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.board.dao.article.IArticleDao;
import com.example.board.utils.CustomUtil;
import com.example.board.vo.article.ArticleVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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

    @Override
    public void list(HttpServletRequest req, Model model) {

        ArrayList<ArticleVo> articleList = articleDao.getList();

        model.addAttribute("articleList", articleList);

    }

    @Override
    public ArticleVo detail(String keyIdx) {

        articleDao.hitUp(keyIdx);
        ArticleVo article = articleDao.findByKey(keyIdx);

        return article;
    }

    @Override
    public void delete(String keyIdx) {

        articleDao.deleteArticle(keyIdx);

    }

    @Override
    public void update(String keyIdx, ArticleVo articleVo) {

        System.out.println("[----- ArticleService.java -----]");
        System.out.println(keyIdx);
        System.out.println(articleVo.getTitle());

        articleDao.updateArticle(keyIdx, articleVo.getTitle(), articleVo.getContent());

    }
    
}