<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <title>注册</title>
</head>
<body>
    <h1>注册</h1>
    <form action="${pageContext.request.contextPath}/register" method="post">
        账&nbsp&nbsp号：<input type="text" name="account"/><br/>
        密&nbsp&nbsp码：<input type="password" name="password"/><br/>
        <input type="submit" value="注册">
    </form>
</body>
</html>