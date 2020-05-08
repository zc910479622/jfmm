package com.jinfei.jfmm.framework.shiro.service;

import org.apache.shiro.SecurityUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SystemFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException,
            ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String basePath = request.getContextPath();
        String URL = request.getRequestURI();
        request.setAttribute("basePath", basePath);
        if (!"/login.action".equals(URL)) {
            if (!SecurityUtils.getSubject().isAuthenticated()) {
                //判断session里是否有用户信息
                if (request.getHeader("x-requested-with") != null
                        && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                    //如果是ajax请求响应头会有，x-requested-with
                    response.setHeader("session-status", "timeout");//在响应头设置session状态
                    return;
                }
            }
        }
        filterChain.doFilter(request, servletResponse);

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
