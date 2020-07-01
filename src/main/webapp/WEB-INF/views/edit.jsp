<%--
  Created by IntelliJ IDEA.
  User: tdimo
  Date: 01.07.2020
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>edit</title>
</head>
<body>
<form action="/edit" method="post">
    Name: <input type="text" name="name" value="${user.name}"/>
    Password: <input type="text" name="password" value="${user.password}"/>
    <br>
    <button type="submit" name="id" value="${user.id}">edit</button>
</form>

</body>
</html>
