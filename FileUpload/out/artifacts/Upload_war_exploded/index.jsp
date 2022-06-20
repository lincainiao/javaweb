<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--上传文件，通过表单上传
    post:无限制
    get:文件大小有限制--%>
<form action="/f1" method="post" enctype="multipart/form-data">
    <p>
        <input type="text" name="username">
    </p>
<%--    <p>--%>
<%--        <input type="file" name="file1">--%>
<%--    </p>--%>
<%--    <p>--%>
<%--        <input type="file" name="file2">--%>
<%--    </p>--%>
    <p>
        <input type="submit"> | <input type="reset">
    </p>

</form>
</body>
</html>
