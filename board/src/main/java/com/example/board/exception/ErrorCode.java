package com.example.board.exception;

import lombok.Getter;

public enum ErrorCode {
    
    NOT_EMPTY("ERROR_CODE_0001", "필수값이 누락되었습니다.");

    @Getter
    private String code;

    @Getter
    private String description;

    private ErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

}