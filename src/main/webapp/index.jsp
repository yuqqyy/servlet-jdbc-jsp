<%--
  Created by IntelliJ IDEA.
  User: 20150
  Date: 2021/5/29
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<form action="/login" method="post">
    用户名：<input type="text" name="username"><br>
    密码：<input type="password" name="pwd"><br>
    <input type="submit" value="登录">
</form>
    <button onclick="window.location='view/sign.jsp'">注册</button>
</body>
</html>
