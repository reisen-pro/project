<%--
  Created by IntelliJ IDEA.
  User: reisen
  Date: 2020/11/23
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>form</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/hello.controller" method="post">
    <table align="center">
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>编码</td>
            <td><input type="text" name="id"></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="提交">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
