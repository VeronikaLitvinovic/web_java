package com.example.demo.controllers;

import com.example.demo.exceptions.ValidationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
    public String handleMultiply(@RequestParam("number") String numberStr,
                                 @RequestParam("multiplier") String multiplierStr,
                                 Model model) {
        try {
            int number = Integer.parseInt(numberStr);
            int multiplier = Integer.parseInt(multiplierStr);
            int result = number * multiplier;
            return "redirect:/result?number=" + number + "&multiplier=" + multiplier + "&result=" + result;
        } catch (NumberFormatException e) {
            throw new ValidationException("Please enter valid numbers.");
        }
    }

    @GetMapping("/result")
    public String getResultPage(@RequestParam("number") int number,
                                @RequestParam("multiplier") int multiplier,
                                @RequestParam("result") int result,
                                Model model) {
        model.addAttribute("number", number);
        model.addAttribute("multiplier", multiplier);
        model.addAttribute("result", result);
        return "result";
    }

    @ExceptionHandler(ValidationException.class)
    public String handleValidationException(ValidationException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error_500";
    }
}