<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--引入jstl核心标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>text</h1>
<form action="JSTLLabelDemo01.jsp" method="get">
<%--    EL表达式，获取表单的数据:param.参数名--%>
    <input type="text" name="username" value="${param.username}">
    <input type="submit" value="登录">
</form>
<%--判断：如果提交的用户名为。。。，则登录成功
用EL表达式取值，返回值为isadmin--%>
<c:if test="${param.username == '芝芝'}" var="isAdmin">
    <c:out value="欢迎芝芝"/>
</c:if>
<c:out value="${isAdmin}"/>
</body>
</html>
