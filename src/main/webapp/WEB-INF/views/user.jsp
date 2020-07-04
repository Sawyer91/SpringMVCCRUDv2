<%--
  Created by IntelliJ IDEA.
  User: tdimo
  Date: 04.07.2020
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>user</title>
</head>
<body>
    login: ${user.name}
    Password: ${user.password}
    <br>
<a href="<c:url value="/logout" />">Logout</a>
</body>
</html>
