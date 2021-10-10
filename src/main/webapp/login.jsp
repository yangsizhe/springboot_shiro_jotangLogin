<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>登录</title>
</head>
<body>
<h1>登录</h1>
<form action="${pageContext.request.contextPath}/login" method="post">
    账&nbsp&nbsp号：<input type="text" name="account"/><br/>
    密&nbsp&nbsp码：<input type="password" name="password"/><br/>
    <input type="submit" value="登录"/>
</form>
</body>
</html>
