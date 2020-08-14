package com.example.board.vo.article;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ArticleVo {
    
    private int keyIdx;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;
    
    private Timestamp regiDate;
    private String writerId;
    private int hit;

}