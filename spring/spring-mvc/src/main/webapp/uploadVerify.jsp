<%--
  Created by IntelliJ IDEA.
  User: reisen
  Date: 2020/12/2
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试文件上传</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/validation.action" method="post" >
    名称：<input type="text" name="name">
    日期：<input type="text" name="createtime">
    <input type="submit" value="submit">
</form>
</body>
</html>