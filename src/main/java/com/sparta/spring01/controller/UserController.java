package com.sparta.spring01.controller;

import com.sparta.spring01.dto.SignupRequestDto;
import com.sparta.spring01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    //회원 로그인 페이지
    @GetMapping("/user/loginView") // WebSecurityConfig에서  .loginPage("/user/login")을 타고 여기로옴
    public String login(){
        return "login";
    }

    //회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup(){
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto){
        userService.registerUser(requestDto);
        return "redirect:/user/loginView";
    }
}
