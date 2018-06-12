<%--
  Created by IntelliJ IDEA.
  User: P B
  Date: 11.06.2018
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<form method="POST" action="${pageContext.request.contextPath}/remove" class="container">
    <input type="hidden" name="animalToRemoveId" value="${id}"/>

    <h2>Are you sure?</h2>
    <input type="submit" value="Yes"/>
    <a href="${pageContext.request.contextPath}/">No</a>
</form>

</body>
</html>
