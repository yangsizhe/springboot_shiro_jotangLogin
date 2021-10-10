<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>success</title>
</head>
<body>
  success！！
  <br/>
  <%--使资源受限，super用户才能访问--%>
  <shiro:hasRole name="super">
    <a href="https://jotang.club/">焦糖官网</a>
  </shiro:hasRole>
</body>
</html>
