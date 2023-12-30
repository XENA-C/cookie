package com.example.cookie.controller;

import com.example.cookie.model.LoginRequest;
import com.example.cookie.service.LoginService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountApiController { //로그인 요청 처리

    private final LoginService loginService;
    //@RequiredArgsConstructor 생성자메소드 필요

    @PostMapping("/login")//로그인 로직
    public void login(
            @RequestBody
            LoginRequest loginRequest,
            HttpServletResponse httpServletResponse
           // HttpSession httpSession
    ){
            loginService.login(loginRequest, httpServletResponse);
    }
}
