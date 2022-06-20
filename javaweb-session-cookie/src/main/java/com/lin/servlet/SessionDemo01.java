package com.lin.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        // 得到Session
        HttpSession session = req.getSession();
        // 给Session中存东西
//        session.setAttribute("girl","芝芝");
        session.setAttribute("person",new Person());
        // 获取Session的id
        String id = session.getId();
        // 判断是否是新创建的
        if (session.isNew()){
            resp.getWriter().write("session 创建成功，id为："+id);
        }else {
            resp.getWriter().write("this session is already exists : "+id);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
