<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 20150
  Date: 2021/6/7
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>select</title>
</head>
<body>
    <c:forEach items="${userList}" var="user">
        <p>${user.username}的时长为：${user.duration}秒</p>
    </c:forEach>
    <button onclick="window.location='view/home.jsp'">返回</button>
</body>
</html>
