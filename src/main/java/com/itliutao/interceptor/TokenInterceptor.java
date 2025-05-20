package com.itliutao.interceptor;

import com.itliutao.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Slf4j
//@Component
public class TokenInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       String requestURI =request.getRequestURI();
       if(requestURI.contains("/login")){
           log.info("放行");
           return true;
       }
       String token =request.getHeader("token");
       if (token ==null || token.isEmpty()){
           response.setStatus(401);
           return false;
       }
        try {
            JwtUtils.parseToken("token");
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
        log.info("放行");
        return true;
    }
}
