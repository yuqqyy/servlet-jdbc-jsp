<%--
  Created by IntelliJ IDEA.
  User: 20150
  Date: 2021/6/5
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>排名</title>
</head>
<body>
<form action="/rank" method="post">
  <table border="2">
    <tr>
      <td>排名</td>
      <td>头像</td>
      <td>用户名</td>
      <td>专业</td>
      <td>时长</td>
    </tr>
    <c:forEach items="${userList}" var="user" varStatus="iter">
    <tr>
      <td>${iter.index+1}</td>
      <td><img src="${user.fileName}" width="100" height="100" alt="加载失败"></td>
      <td>${user.username}</td>
      <td>${user.profession}</td>
      <td>${user.duration}秒</td>
    </tr>
    </c:forEach>
  </table>
</form>
<button onclick="window.location='view/home.jsp'">返回</button>

</body>
</html>
