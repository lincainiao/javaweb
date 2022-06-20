package com.smbs.servlet;

import com.smbs.pojo.User;
import com.smbs.service.user.UserServiceImpl;
import com.smbs.utils.Constant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    // servlet控制层调用service业务层代码

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("LoginServlet-start");
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");
        
        // 和数据库的密码进行对比，调用业务层
        UserServiceImpl userService = new UserServiceImpl();
        // 将登录的用户返回到loginUser
        User loginUser = userService.LoginSer(userCode, userPassword);
        if (loginUser != null) {
            // 存在该用户
            // 将用户的信息保存到session中
            HttpSession session = req.getSession();
            session.setAttribute(Constant.USER_SESSION,loginUser);
            // 跳转页面
            resp.sendRedirect("jsp/frame.jsp");
        }else {
            // 没有该用户
            // 返回到登录页面，并提示用户密码错误
            req.setAttribute("error","用户名或密码不正确");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
