package com.smbs.servlet;

import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.util.StringUtils;
import com.smbs.pojo.Role;
import com.smbs.pojo.User;
import com.smbs.service.role.RoleServiceImpl;
import com.smbs.service.user.UserService;
import com.smbs.service.user.UserServiceImpl;
import com.smbs.utils.Constant;
import com.smbs.utils.PageSupport;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

// 修改密码Servlet
public class UserServlet extends HttpServlet {
    // 实现servlet复用
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method.equals("savepwd") && method != null){
            this.updatePwd(req,resp);
        }else if(method.equals("pwdmodify") && method != null){
            this.pwdModify(req,resp);
        }else if(method.equals("query") && method != null){
            try {
                this.query(req,resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    // 修改旧密码
    public void updatePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        // 从session中获取id
        Object attribute = req.getSession().getAttribute(Constant.USER_SESSION);
        String newpassword = req.getParameter("newpassword");
        System.out.println("UserServlet : "+newpassword);
        boolean flag = false;
        if (attribute != null && !StringUtils.isNullOrEmpty(newpassword)){
            UserService userService = new UserServiceImpl();
            flag = userService.updatePwdSer(((User) attribute).getId(), newpassword);
            if (flag) {
                req.setAttribute("message","修改成功！清退出重新登录");
                // 密码修改成功，移除session
                req.getSession().removeAttribute(Constant.USER_SESSION);
            }else {
                // 密码修改失败
                req.setAttribute("message","密码修改失败！");
            }
        }else {
            req.setAttribute("message","新密码有问题");
        }
        req.getRequestDispatcher("pwdmodify.jsp").forward(req,resp);
    }

    // 验证旧密码，session中有用户的密码
    public void pwdModify(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        Object o = req.getSession().getAttribute(Constant.USER_SESSION);
        String oldpassword = req.getParameter("oldpassword");

        //结果集
        Map<Object,String> resultMap = new HashMap<Object,String>();
        if (o == null) {
            // session失效
            resultMap.put("result","sessionerror");
        }else if(StringUtils.isNullOrEmpty(oldpassword)){
            // 输入的旧密码为空
            resultMap.put("result","error");
        }else {
            String userPassword = ((User) o).getUserPassword();
            if (oldpassword.equals(userPassword)){
                // 输入的旧密码和数据库中的密码相同
                resultMap.put("result","true");
            }else {
                // 输入的旧密码和数据库的密码不同
                resultMap.put("result","false");
            }
        }
        // 返回json
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        // 阿里巴巴的JSON工具类，主要用于格式转换
        writer.write(JSONArray.toJSONString(resultMap));
        System.out.println("DONE!");
        writer.flush();
        writer.close();
    }

    //
    public void query(HttpServletRequest req,HttpServletResponse resp) throws Exception{
//        // 查询用户列表
        UserServiceImpl userService = new UserServiceImpl();
        List<User> userList=null;
//
        RoleServiceImpl roleService = new RoleServiceImpl();
        List<Role> roleList=null;
//        // 从前端获取数据
        String queryUserName = req.getParameter("queryname");
        String temp = req.getParameter("queryUserRole");
//        // 如果下拉框中没有选择角色类型，默认为0
        int queryUserRole=0;
//        // 要选择的第几页
        String pageIndex = req.getParameter("pageIndex");
//
//        // 第一次进入该页面，显示首页，而且页面大小固定
        int pageSize=Constant.maxPageSize;
        int currentPageNo=1;
//
        if(queryUserName  ==  null){
            //这里为空，说明用户没有输入要查询的用户名，则sql语句传值为""，%%，会查询所有记录
            queryUserName="";
        }
        if(temp != null && !temp.equals("")){
            //不为空，说明前端有传来的用户所设置的userCode，更新真正的角色码
            queryUserRole=Integer.parseInt(temp);//强制转换，前端传递的参数都是默认字符串，要转成int类型
        }
        if(pageIndex != null){//说明当前用户有进行设置跳转页面
            currentPageNo=Integer.valueOf(pageIndex);
        }
//
//        // 获取用户总数
        int totalCount = userService.getUserCountSer(queryUserName, queryUserRole);
//        // 总页数、上一页、下一页
        PageSupport pageSupport = new PageSupport();
        pageSupport.setCurrentPageNo(currentPageNo);
        pageSupport.setPageSize(pageSize);
        pageSupport.setTotalCount(totalCount);

//        // 控制首页和尾页
        int totalPageCount = pageSupport.getTotalPageCount();
        if(currentPageNo <1 ){
            currentPageNo = 1;
        }else if(currentPageNo > totalPageCount){
            currentPageNo = totalPageCount;
        }
//
//        // 用户列表展示
        userList = userService.getUserListSer(queryUserName, queryUserRole, currentPageNo, pageSize);
        roleList = roleService.getRoleListSer();
        req.setAttribute("userList",userList);
        req.setAttribute("roleList",roleList);
        req.setAttribute("queryUserName", queryUserName);
        req.setAttribute("queryUserRole", queryUserRole);
        req.setAttribute("totalPageCount", totalPageCount);
        req.setAttribute("totalCount", totalCount);
        req.setAttribute("currentPageNo", currentPageNo);
        //将所得到的的所有req参数送回给前端
        req.getRequestDispatcher("userlist.jsp").forward(req,resp);
    }
}
