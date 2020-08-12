package com.example.board.controllers.serviceController.member;

import com.example.board.services.member.MermberService;
import com.example.board.vo.member.MemberVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    @Autowired
    MermberService memberService;

    @PostMapping("/api/user/regist")
    public String registMember(MemberVo memberVo) {

        memberService.regist(memberVo);
        return "redirect:/login";

    }

}