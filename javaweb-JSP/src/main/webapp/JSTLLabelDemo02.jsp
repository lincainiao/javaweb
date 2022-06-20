<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--引入jstl核心标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>text</h1>
<%--保存数据
定义变量为name，值为芝芝--%>
<c:set var="name" value="zhizhi"/>
<c:set var="age" value="22"/>
<c:choose>
    <c:when test="${age <= 22}">
        <c:out value="${name} 真年轻"/>
    </c:when>
</c:choose>
</body>
</html>
