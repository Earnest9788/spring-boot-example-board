package com.example.board.services.member;

import com.example.board.vo.member.MemberVo;

public interface MermberService {
    
    /**
     * 1. 회원가입
     * 2. 로그인
     * 3. 로그아웃
     */

    public void regist(MemberVo memberVo);
    public void login();
    public void logout();

}