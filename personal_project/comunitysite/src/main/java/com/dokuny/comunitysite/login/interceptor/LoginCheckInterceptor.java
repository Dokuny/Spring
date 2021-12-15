package com.dokuny.comunitysite.login.interceptor;

import com.dokuny.comunitysite.login.controller.Session_Const;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(Session_Const.LOGIN_MEMBER) == null) {
            response.sendRedirect("/login?redirectUrl="+requestURI);
        }

        return true;
    }
}
