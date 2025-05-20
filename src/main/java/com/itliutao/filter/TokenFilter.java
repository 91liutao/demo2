package com.itliutao.filter;

import com.itliutao.utils.CurrentHolder;
import com.itliutao.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;

        //获取请求的路径
        String requestURI =request.getRequestURI();// /employee/login


        //判断是否是登录请求，如路径包含/login，则放行
        if(requestURI.contains("/login")){
            log.info("登录请求，放行");
            filterChain.doFilter(request,response);
            return;
        }

        //获取请求头中的token，判断token是否存在，若不存在，说明未登录，返回错误信息（401）
        String token =request.getHeader("token");

        //获取token对应的用户信息，判断用户信息是否存在，若不存在，说明token已失效，返回错误信息（401）
        if(token==null||token.isEmpty()){
            log.info("令牌为空");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        //校验令牌
        try {
            Claims claims = JwtUtils.parseToken(token);
            Integer id = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(id);
            log.info("当前用户id为："+id);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        //校验通过，放行
        log.info("令牌合法放行");
        filterChain.doFilter(request,response);
        //删除threadLocal中数据
        CurrentHolder.remove();
    }


}
