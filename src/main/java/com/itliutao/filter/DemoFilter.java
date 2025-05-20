package com.itliutao.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
@Slf4j
//@WebFilter(urlPatterns = "/*")//拦截所有请求

public class DemoFilter implements Filter {
    //初始化方法，web启动时执行，只执行一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("DemoFilter初始化");
        Filter.super.init(filterConfig);
    }
    //拦截请求后执行，执行多次
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    log.info("DemoFilter执行了");
    //放行
    filterChain.doFilter(servletRequest,servletResponse);
    }
    //销毁方法，web关闭时执行，只执行一次
    @Override
    public void destroy() {
        log.info("DemoFilter销毁");
        Filter.super.destroy();
    }
}
