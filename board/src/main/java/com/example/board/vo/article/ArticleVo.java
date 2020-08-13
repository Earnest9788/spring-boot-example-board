package com.example.board.vo.article;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ArticleVo {
    
    private int keyId;
    private String title;
    private String content;
    private Timestamp regiDate;
    private String writerId;
    private int hit;

}