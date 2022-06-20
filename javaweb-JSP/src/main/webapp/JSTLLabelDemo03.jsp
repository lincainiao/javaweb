<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--引入jstl核心标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    ArrayList<String> people = new ArrayList<>();
    people.add("芝芝");
    people.add("梦心玥");
    people.add("王心怡");
    people.add("张思允");
    people.add("叉子");
    request.setAttribute("list",people);
%>
<%--foreach遍历
var：每次遍历出来的变量
items：需要遍历的对象
--%>
<c:forEach var="people" items="${list}">
    <c:out value="${people}"/> <br>
</c:forEach>
<hr>
<%--设置开始、结束、步长--%>
<c:forEach var="people" items="${list}" begin="1" end="4" step="2">
    <c:out value="${people}"/> <br>
</c:forEach>
</body>
</html>
