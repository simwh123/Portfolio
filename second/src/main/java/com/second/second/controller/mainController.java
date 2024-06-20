package com.second.second.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.second.second.service.loginService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class mainController {
    @Autowired
    private loginService login;

    @GetMapping("/")
    public String Login() {
        return "login";
    }

    @PostMapping("/loginCheck")
    @ResponseBody
    public Map<String, Object> postLogin(@RequestParam(name = "id") String id, @RequestParam(name = "pw") String pw,
            HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        boolean loginCheck = login.checkID(id, pw);
        if (loginCheck) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", id);
            response.put("success", true);
            response.put("redirectUrl", "main");
        } else {
            response.put("success", false);
            response.put("message", "Invalid credentials");
        }
        return response;
    }

    @GetMapping("/main")
    public String getMain() {
        return "main";
    }

    @GetMapping("/findId")
    public String getfindId() {
        return "findId";
    }

    @GetMapping("/findPw")
    public String getfindPw() {
        return "findPw";
    }

    @PostMapping("/findId")
    @ResponseBody
    public Map<String, Object> findId(@RequestParam(name = "name") String name,
            @RequestParam(name = "email") String email,
            HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        boolean checkEmail = login.findID(name, email);
        if (checkEmail) {
            String findNom = login.checkNom(email);
            response.put("success", findNom);
            response.put("userId", "");
            response.put("redirectUrl", "main");
        } else {
            response.put("success", false);
            response.put("message", "Invalid credentials");
        }
        return response;
    }

    @PostMapping("/successNum")
    @ResponseBody
    public Map<String, String> findIdSuccess(@RequestParam(name = "pw") String pw) {
        Map<String, String> response = new HashMap<>();
        String checkSucces = login.pw(pw);
        if (checkSucces.equals("인증성공")) {
            String userId = login.userId();
            response.put("status", checkSucces);
            response.put("id", userId);
        } else {
            response.put("status", checkSucces);
            response.put("message", "인증번호를 확인하세요");
        }
        return response;
    }
}
