<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--取出Demo01的参数--%>
<h1>name：<%=request.getParameter("name")%></h1><br>
<h1>age：<%=request.getParameter("age")%></h1>

</body>
</html>
