<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>index</title>
    <script type="text/javascript" src="/js/jQuery.min.js"></script>
</head>
<body>
    <h2>userManager::index</h2>
    <table>
        <c:forEach items="${userList}" var="user">
            <tr >
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.username}</td>
                <td>${user.email}</td>
            </tr>
        </c:forEach>
    </table>
</body>
<script type="text/javascript">
    $(function () {
       console.log("userManager::index");
    })
</script>
</html>
