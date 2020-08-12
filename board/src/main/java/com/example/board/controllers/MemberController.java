package com.example.board.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.board.services.member.IMermberService;
import com.example.board.vo.member.MemberVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import groovyjarjarpicocli.CommandLine.Model;

@Controller
public class MemberController {

    @Autowired
    IMermberService memberService;

    @PostMapping("/api/user/regist")
    public String registMember(MemberVo memberVo) {

        memberService.regist(memberVo);
        return "redirect:/login";

    }

    @PostMapping("/api/user/login")
    public String login(HttpServletRequest req, Model model) {

        String id = req.getParameter("id");
        String pw = req.getParameter("password");

        MemberVo memberVo = memberService.login(id, pw);
        
        if (memberVo != null) {
            memberService.setSession(req, memberVo);
            return "redirect:/";
        }

        return "redirect:/login";

    }

    @GetMapping("/api/user/logout")
    public String logout(HttpServletRequest req) {

        memberService.logout(req);

        return "redirect:/";

    }

}