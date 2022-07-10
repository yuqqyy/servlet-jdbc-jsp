<%--
  Created by IntelliJ IDEA.
  User: 20150
  Date: 2021/5/29
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>home</title>
</head>
<body>
    <p>登录成功！</p>
    <p>欢迎你，${sessionScope.user.username}</p>

    <form action="/signIn" method="post">
        <input type="submit" name="signInTime" value="签到" onclick="alert('签到成功')">
    </form>

    <form action="/signOut" method="post">
        <input type="submit" name="signOutTime" value="签退" onclick="alert('签退成功')">
    </form>
    <form action="/select" method="post">
        请输入想要查询的姓名<input type="text" name="username">
        <input type="submit" value="查询时长">
    </form>
    <button onclick="window.location='/rank'">排名</button><br>
</body>
</html>
