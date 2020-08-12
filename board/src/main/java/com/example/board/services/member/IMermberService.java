package com.example.board.services.member;

import javax.servlet.http.HttpServletRequest;

import com.example.board.vo.member.MemberVo;

public interface IMermberService {
    
    /**
     * 1. 회원가입
     * 2. 로그인
     * 3. 로그아웃
     */

    public void regist(MemberVo memberVo);

    public MemberVo login(String id, String pw);

    public void logout(HttpServletRequest req);
    
    public void setSession(HttpServletRequest req, MemberVo memberVo);

}