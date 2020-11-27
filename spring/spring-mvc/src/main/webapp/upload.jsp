<%--
  Created by IntelliJ IDEA.
  User: reisen
  Date: 2020/11/27
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试文件上传</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/upload.action" method="post" enctype="multipart/form-data">
    <input type="file" name="file">
    <input type="submit" name="submit">
</form>
</body>
</html>
