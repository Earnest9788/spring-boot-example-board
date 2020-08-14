package com.example.board.services.article;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.board.dao.article.IArticleDao;
import com.example.board.utils.CustomUtil;
import com.example.board.utils.Pager;
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

    @Autowired
    private Pager pager;

    @Override
    public void list(String pageNum, String contentNum, Model model) {

        int cPageNum = Integer.parseInt(pageNum);
        int cContentNum = Integer.parseInt(contentNum);

        pager.setTotalCount(articleDao.totalCount());
        pager.setPageNum(cPageNum - 1);
        pager.setContentNum(cContentNum);
        pager.setCurrentBlock(cPageNum);
        pager.setLastBlock(pager.getTotalCount());

        pager.prevnext(cPageNum);
        pager.setStartPage(pager.getCurrentBlock());
        pager.setEndPage(pager.getLastBlock(), pager.getCurrentBlock());

        ArrayList<ArticleVo> articleList = articleDao.getList(pager.getPageNum() * 10, pager.getContentNum());

        model.addAttribute("articleList", articleList);
        model.addAttribute("pager", pager);

    }

    @Override
    public void regist(HttpServletRequest req, ArticleVo articleVo) {

        HttpSession session = req.getSession(true);
        String writerId = (String) session.getAttribute("memberId");

        articleVo.setWriterId(writerId);
        articleVo.setRegiDate(customUtil.getCurrentTime());

        articleDao.createArticle(articleVo);

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

        articleDao.updateArticle(keyIdx, articleVo.getTitle(), articleVo.getContent());

    }
    
}