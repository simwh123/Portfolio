package com.second.second.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {

    @GetMapping("/")
    public String Login() {
        return "login";
    }

}
