package com.lin.servlet;

import org.apache.jasper.runtime.HttpJspBase;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// 保存上一次访问的时间
public class CookieDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        // 服务器告诉你，你来的时间，把这个时间封装起来，下次访问的时候，服务器就知道来过了

        PrintWriter out = resp.getWriter();
        // cookie,服务器从客户端获取
        Cookie[] cookies = req.getCookies(); // 返回数组，说明cookie可能存在多个
        // 判断cookie是否存在
        if (cookies != null) {
            // 如果存在
            out.write("your last time to here : ");
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                // 获得cookie中的键
               if (cookie.getName().equals("girl")){
                   out.write(cookie.getValue());
               }
            }
        }else {
            // 第一次访问
            out.write("this is your first time to here!");
            // 给服务器给客户端响应一个cookie
            Cookie cookie = new Cookie("girl", "zhizhi");
            // 设置有效期，以秒为单位
            cookie.setMaxAge(24*60*60);
            resp.addCookie(cookie);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
