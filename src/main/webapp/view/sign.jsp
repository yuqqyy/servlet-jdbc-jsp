<%@ page import="com.mysql.cj.util.DnsSrv" %><%--
  Created by IntelliJ IDEA.
  User: 20150
  Date: 2021/6/3
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
  <form action="/sign" method="post" enctype="multipart/form-data">
    用户名：<input type="text" name="username"><br>
    密码：<input type="password" name="pwd"><br>
    请再次确认密码：<input type="password" name="check_pwd"><br>
    专业：<input type="text" name="profession"><br>
    请上传头像<input type="file" name="picture"><br>
    <input type="submit" value="提交">
  </form>
</body>
</html>
