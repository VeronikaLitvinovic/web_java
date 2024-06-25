package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MultiplyController {

    @GetMapping("/multiply")
    public String getMultiplyPage(Model model) {
        model.addAttribute("number");
        model.addAttribute("multiplier");
        return "multiply";
    }

    @PostMapping("/multiply")
    public String handleMultiply(@RequestParam("number") int number,
                                 @RequestParam("multiplier") int multiplier) {
        int result = number * multiplier;
        return "redirect:/result?number=" + number + "&result=" + result;
    }

    @GetMapping("/result")
    public String getResultPage(@RequestParam("number") int number,
                                @RequestParam("result") int result,
                                Model model) {
        model.addAttribute("number", number);
        model.addAttribute("result", result);
        return "result";
    }
}
