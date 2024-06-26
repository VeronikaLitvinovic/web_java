package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class HomePage {

    @GetMapping("/")
    public String getMultiplyPage(Model model) {
        return "homePage";
    }

}