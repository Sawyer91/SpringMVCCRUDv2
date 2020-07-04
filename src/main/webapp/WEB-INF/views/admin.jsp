<%--
  Created by IntelliJ IDEA.
  User: tdimo
  Date: 01.07.2020
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<form action="/add" method="post">
    name: <input type="text" name="name"/>
    Password: <input type="text" name="password"/>
    <br>
    <button type="submit">add</button>
</form>
<a href="<c:url value="/logout" />">Logout</a>
<br>
<table>
    <tr>
        <th> </th>
        <th>name</th>
        <th>password</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td></td>
            <td><c:out value="${user.name}"/></td>
            <td><c:out value="${user.password}"/></td>
            <td><c:out value="${user.role}"/></td>
            <td>
                <form action="/edit" method="get">
                    <input type="hidden" name="name" value="${user.name}">
                    <input type="hidden" name="password" value="${user.password}">
                    <button type="submit" name="id" value="${user.id}">edit</button>
                </form>
            </td>
            <td>
                <form action="/delete" method="post">
                    <button type="submit" name="id" value="${user.id}">delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
