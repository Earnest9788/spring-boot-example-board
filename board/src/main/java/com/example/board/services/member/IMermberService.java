package com.example.board.services.member;

import javax.servlet.http.HttpServletRequest;

import com.example.board.vo.member.MemberVo;

public interface IMermberService {

    public void regist(MemberVo memberVo);

    public MemberVo login(String id, String pw);

    public void logout(HttpServletRequest req);
    
    public void setSession(HttpServletRequest req, MemberVo memberVo);

}