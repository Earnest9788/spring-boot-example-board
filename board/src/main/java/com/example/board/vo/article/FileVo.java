package com.example.board.vo.article;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
public class FileVo {
    
    private int keyIdx;

    private int articleIdx;

    private String originalNam;

    private String saveName;

    private String savePath;
    
    private Timestamp regiDate;

}