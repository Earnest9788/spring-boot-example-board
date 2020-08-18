package com.example.board.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.board.services.member.IMermberService;
import com.example.board.vo.member.MemberVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    @Autowired
    IMermberService memberService;

    
    @InitBinder
	public void InitBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

    // 회원가입
    @PostMapping("/api/user/regist")
    public String registMember(@Valid MemberVo memberVo, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "redirect:/regist?fail";

        } else {
            memberService.regist(memberVo);
            return "redirect:/login";
        }

    }

    // 로그인
    @PostMapping("/api/user/login")
    public String login(HttpServletRequest req, Model model) {

        String id = req.getParameter("id");
        String pw = req.getParameter("password");

        MemberVo memberVo = memberService.login(id, pw);
        
        if (memberVo != null) {
            memberService.setSession(req, memberVo);
            return "redirect:/";
        }

        return "redirect:/login?fail";

    }

    // 로그아웃
    @GetMapping("/api/user/logout")
    public String logout(HttpServletRequest req) {

        memberService.logout(req);

        return "redirect:/";

    }

}