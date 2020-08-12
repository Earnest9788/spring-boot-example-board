package com.example.board.controllers.serviceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/test")
    public String home() {
        return "pages/test";
    }

}