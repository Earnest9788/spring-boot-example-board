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
    public void list(String pageNum, String contentNum, HttpServletRequest req, Model model) {
        // Searching
        String searchType = req.getParameter("st");
        String searchValue = req.getParameter("sv");
        String titleKeyword = null;
        String writerKeyword = null;

        if (searchType != null) {
            switch (searchType) {
                case "title" :
                    titleKeyword = searchValue;
                    break;
                case "writer" :
                    writerKeyword = searchValue;
                    break;
            }
        }        
        
        // Paging
        int cPageNum = Integer.parseInt(pageNum);
        int cContentNum = Integer.parseInt(contentNum);

        pager.setTotalCount(articleDao.totalCount(titleKeyword, writerKeyword));
        pager.setPageNum(cPageNum - 1);
        pager.setContentNum(cContentNum);
        pager.setCurrentBlock(cPageNum);
        pager.setLastBlock(pager.getTotalCount());

        pager.prevnext(cPageNum);
        pager.setStartPage(pager.getCurrentBlock());
        pager.setEndPage(pager.getLastBlock(), pager.getCurrentBlock());

        ArrayList<ArticleVo> articleList = articleDao.getList(
            pager.getPageNum() * 10, 
            pager.getContentNum(),
            titleKeyword,
            writerKeyword
        );

        model.addAttribute("articleList", articleList);
        model.addAttribute("pager", pager);
    }

    @Override
    public void regist(HttpServletRequest req, ArticleVo articleVo, String files) {
        HttpSession session = req.getSession(true);
        String writerId = (String) session.getAttribute("memberId");

        articleVo.setWriterId(writerId);
        articleVo.setRegiDate(customUtil.getCurrentTime());

        if (files != null) {
            articleVo.setFiles(files);
        }

        articleDao.createArticle(articleVo);
        articleDao.updateGroupId(articleVo.getKeyIdx());
    }

    @Override
    public void reply(HttpServletRequest req, ArticleVo articleVo, String files) {
        HttpSession session = req.getSession(true);
        String groupIndex = req.getParameter("groupIdx"); // 원글 idx
        String writerId = (String) session.getAttribute("memberId"); // 답변글 작성자

        articleVo.setWriterId(writerId);
        articleVo.setReplyGroup(Integer.parseInt(groupIndex));

        if (files != null) {
            articleVo.setFiles(files);
        }

        articleDao.replyShape(groupIndex, articleVo.getReplyStep());
        articleDao.createReply(articleVo);
    }

    @Override
    public ArticleVo detail(String keyIdx, boolean isHit) {
        if (isHit) {
            articleDao.hitUp(keyIdx);
        }

        ArticleVo article = articleDao.findByKey(keyIdx);

        return article;
    }

    @Override
    public void delete(String keyIdx) {
        articleDao.deleteArticle(keyIdx);
    }

    @Override
    public void update(String keyIdx, ArticleVo articleVo) {
        articleDao.updateArticle(keyIdx, articleVo.getTitle(), articleVo.getContent(), articleVo.getFiles());
    }
    
}