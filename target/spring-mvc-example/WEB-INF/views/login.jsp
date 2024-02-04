<%--
  Created by IntelliJ IDEA.
  User: Sania
  Date: 22.01.2024
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login form</h1>
<form action="login" method="post">
    <b>Login : </b><input type="email" name="login" required><br>
    <b>Password : </b><input type="password" name="password" required><br>
    <input type="submit" value="Login">
</form>
</body>
</html>
