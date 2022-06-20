package com.lin.listener;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取前端的参数
        String username = req.getParameter("username");
        HttpSession session = req.getSession();
        if (username.equals("zhizhi")){
            // 登陆成功
            session.setAttribute("USER_SESSION",session.getId());
            resp.sendRedirect("/sys/main.jsp");
        }else {
            // 登录失败
            resp.sendRedirect("/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
