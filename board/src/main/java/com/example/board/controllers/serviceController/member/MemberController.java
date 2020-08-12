package com.example.board.controllers.serviceController.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    @PostMapping("/api/user/regist")
    public String registMember() {
        System.out.println("@@@@@@@@@@@@@@@@");
        return "redirect:/login";

    }

}