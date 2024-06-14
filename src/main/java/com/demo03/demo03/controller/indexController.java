package com.demo03.demo03.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo03.demo03.model.testModel;
import com.demo03.demo03.service.testService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class indexController {
    @Autowired
    private testService tService;

    @GetMapping("/")
    public String test() {
        return "test";
    }

    @PostMapping("/testForm")
    public String User(@ModelAttribute testModel user, Model model) {
        model.addAttribute("userData", user);
        tService.userInfo(user);
        return "test";
    }

    @PostMapping("/test01")
    @ResponseBody
    public Map postMethodId(@RequestBody Map<String, Object> request) {
        String userId = (String) request.get("id");
        boolean notFound = tService.userGetId(userId);
        Map<String, Object> checkMap = new HashMap<>();
        checkMap.put("key", notFound);
        System.out.println(userId);

        return checkMap;
    }

    @PostMapping("/test02")
    @ResponseBody
    public Map postMethodEmail(@RequestBody Map<String, Object> request) {
        String email = (String) request.get("email");
        String checkNum = tService.emailCheck(email);
        System.out.println(email);
        Map<String, Object> checkMap = new HashMap<>();
        checkMap.put("key", checkNum);
        return checkMap;
    }

}
