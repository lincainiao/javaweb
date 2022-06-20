<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--在请求转发时添加参数--%>
<jsp:forward page="/JSPLabelDemo02.jsp">
    <jsp:param name="name" value="zhizhi"/>
    <jsp:param name="age" value="22"/>
</jsp:forward>

</body>
</html>
