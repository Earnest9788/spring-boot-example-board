package com.example.board.vo.member;

import lombok.Data;

@Data
public class member {
    
    private Long keyIdx;
    private String id;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String role;
    private Data regiDate;

}