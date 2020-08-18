package com.example.board.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    
    // 관리자 페이지 뷰
    @GetMapping("/admin")
    public String getAdminPage(HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        String memberRole = (String) session.getAttribute("memberRole");

        if (memberRole.equals("ROLE_ADMIN")) {
            return "pages/admin/home";
        } else {
            session.invalidate();
            return "redirect:/";
        }
    }

}