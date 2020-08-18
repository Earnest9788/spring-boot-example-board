package com.example.board.vo.article;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class ArticleVo {
    
    private int keyIdx;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    private String writerId;

    private Timestamp regiDate;
    private String files;
    private int hit;
    private int replyGroup;
    private int replyStep;
    private int replyIndent;

}