<%@ page import="com.lin.pojo.People" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--引入jstl核心标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    // People people = new People();
%>

<jsp:useBean id="people" class="com.lin.pojo.People" scope="page"/>
<jsp:setProperty name="people" property="id" value="1"/>
<jsp:setProperty name="people" property="name" value="芝芝"/>
<jsp:setProperty name="people" property="age" value="22"/>
<jsp:setProperty name="people" property="address" value="广州"/>


id : <jsp:getProperty name="people" property="id"/>
<br>
姓名 : <jsp:getProperty name="people" property="name"/>
<br>
年龄 : <jsp:getProperty name="people" property="age"/>
<br>
地址 : <jsp:getProperty name="people" property="address"/>

</body>
</html>
