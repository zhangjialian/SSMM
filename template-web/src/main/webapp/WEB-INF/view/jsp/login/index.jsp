<%@ page import="com.starfish.common.systemEnum.PageURLEnum" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Login</title>
    <script type="text/javascript" src="/js/jQuery.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/js/bootstrap/bootstrap.min.css" type="text/css" />
</head>
<body>
<div>
    <a href="<%= PageURLEnum.SUBMIT_LOGIN.getUrl() %>" class="btn btn-success">登录</a>
</div>
</body>
</html>
