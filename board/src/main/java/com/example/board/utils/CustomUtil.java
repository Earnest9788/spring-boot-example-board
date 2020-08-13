package com.example.board.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class CustomUtil {

    public Timestamp getCurrentTime() {
        return Timestamp.valueOf(LocalDateTime.now());
    }

}