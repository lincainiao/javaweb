<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--显示配置错误页面--%>
<%--<%@ page isErrorPage="true" %>--%>


<html>
<head>
    <title>Title</title>
</head>
<body>
<%--包含，通常提取共同页面--%>
<%--会将多个页面合成一个--%>
<%@ include file="common/head.jsp" %>
<h1>this is body</h1>
<%@ include file="common/footer.jsp"%>
<hr>

<%--jsp:include标签拼接页面，相当于引用，本质还是三个--%>
<jsp:include page="common/head.jsp"/>
<h1>this is body</h1>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
