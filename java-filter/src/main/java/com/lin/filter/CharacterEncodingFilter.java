package com.lin.filter;

import javax.servlet.*;
import java.io.IOException;

// 过滤器中的所有代码，在过去待定请求的时候都会执行
// 必须让过滤器继续执行:filterChain.doFilter(servletRequest,servletResponse);

public class CharacterEncodingFilter implements Filter {
    // 初始化，web服务启动就已经初始化，随时等待过滤对象
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        System.out.println("CharacterEncoding 已经初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");
        servletResponse.setContentType("text/html;charset=UTF-8 ");
        System.out.println("filter执行前");
        // 通过chain（链），让请求继续；如果不写，程序到这里被拦截停止
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("filter执行后");
    }

    // 销毁，在web服务器关闭的时候，过滤器会销毁
    @Override
    public void destroy() {
//        System.out.println("CharacterEncoding 已经销毁");
    }
}
