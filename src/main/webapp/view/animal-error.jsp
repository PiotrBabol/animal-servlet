<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: P B
  Date: 13.06.2018
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
    <meta http-equiv="Refresh" content="5;url=${pageContext.request.contextPath}/list">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

</head>
<body>
<h1> Something has gone wrong </h1>

<div class="container">
    <p> You will be redirected to list after 5 s</p>
    <p><a href="${pageContext.request.contextPath}/list">I want to be redirected now</a></p>
</div>


</body>
</html>
