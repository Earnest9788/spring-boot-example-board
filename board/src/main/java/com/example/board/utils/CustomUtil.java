package com.example.board.utils;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.example.board.vo.article.FileVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component
public class CustomUtil {

    public Timestamp getCurrentTime() {
        return Timestamp.valueOf(LocalDateTime.now());
    }

    public String uploadFile(MultipartHttpServletRequest mReq) {
        FileVo fileVo = new FileVo();

        List<MultipartFile> fileList = mReq.getFiles("file");
        StringBuilder stringBuilder = new StringBuilder();

        for (MultipartFile mf : fileList) {
            try {
                String fileName = mf.getOriginalFilename();

                mf.transferTo(new File("/Users/kkw10/my-asset", fileName));
                stringBuilder.append(fileName);
                stringBuilder.append(",");

            } catch (IllegalStateException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (stringBuilder.length() > 0) {
            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        }

        return stringBuilder.toString();
    }

}