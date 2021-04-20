package com.fprojects.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/home")
    public String home(Model model) {
        //model.addAttribute("title", "Main Page");
        return "home";
    }
}
