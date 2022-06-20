<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--内置对象--%>
<%
    pageContext.setAttribute("name1","芝芝"); // 保存的数据只在一个页面中有效
    request.setAttribute("name2","梦心玥");    // 保存的数据只在一次请求中有效，请求转发会携带着个数据
    session.setAttribute("name3","张思允");    // 保存的数据只在一次会话中有效，从打开浏览器到关闭浏览器
    application.setAttribute("name4","王心怡");    // 保存的数据只在服务器中，从打开服务器到关闭服务器

//    public static final int PAGE_SCOPE = 1;
//    public static final int REQUEST_SCOPE = 2;
//    public static final int SESSION_SCOPE = 3;
//    public static final int APPLICATION_SCOPE = 4;
//    pageContext.setAttribute("name","girl",PageContext.APPLICATION_SCOPE);
%>

<%
    // 通过pageContext取值
    // 从底层到高层找 : pageContext->request->session->application
    // 没找到就返回null
    Object name1 = (String)pageContext.findAttribute("name1");
    Object name2 = (String)pageContext.findAttribute("name2");
    Object name3 = (String)pageContext.findAttribute("name3");
    Object name4 = (String)pageContext.findAttribute("name4");
    Object name5 = (String)pageContext.findAttribute("name5");  // 不存在
%>
<%--使用EL表达式输出--%>
<h1>取出的值：</h1>
<h3>${name1}</h3>
<h3>${name2}</h3>
<h3>${name3}</h3>
<h3>${name4}</h3>
<%--不存在的不输出--%>
<h3>${name5}</h3>
<%--输出null--%>
<%--<%=name5%>--%>
</body>
</html>
