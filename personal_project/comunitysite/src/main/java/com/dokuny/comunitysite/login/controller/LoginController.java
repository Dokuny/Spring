package com.dokuny.comunitysite.login.controller;

import com.dokuny.comunitysite.login.service.LoginCheckService;
import com.dokuny.comunitysite.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.dokuny.comunitysite.login.domain.MemberLoginDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class LoginController {

    private final LoginCheckService loginCheckService;

    @PostMapping("/login")
    public String login(@ModelAttribute("loginForm") @Validated MemberLoginDTO memberLoginDTO, BindingResult bindingResult
            , HttpServletRequest request, @RequestParam(defaultValue = "/") String redirectUrl) {
        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }

        Member member = loginCheckService.checkLogin(memberLoginDTO);
        if (member == null) {
            bindingResult.reject("loginFail","아이디 또는 패스워드가 올바르지 않습니다");
            return "login/loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute(Session_Const.LOGIN_MEMBER,member);

        return "redirect:"+redirectUrl;
    }

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") MemberLoginDTO memberLoginDTO) {
        return "login/loginForm";
    }

    @PostMapping("/logout")
    public String logoutV3(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }
}
