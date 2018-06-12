<%@ page import="foo.bar.animal.service.AnimalService" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="foo.bar.animal.model.Animal" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: P B
  Date: 10.06.2018
  Time: 09:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Details</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<h1>details</h1>
<div class="container">
    <div>
        <p>id : <c:out value="${param.id}"/></p>
        <p>name : <c:out value="${AnimalService.animalList.get(param.id).getName()}"/></p>
        <p>kingdom : <c:out value="${AnimalService.animalList.get(param.id).getKingdom().toString().toLowerCase()}"/></p>
        <p>details : <c:out value="${AnimalService.animalList.get(param.id).getDetails()}"/></p>
        <p><a href="edit?id=${AnimalService.animalList.get(param.id).getId()}">Edit</a></p>
        <p><a href="remove?id=${AnimalService.animalList.get(param.id).getId()}">Remove</a></p>

    </div>
</div>
<p><a href="${pageContext.request.contextPath}/">HOME</a></p>


</body>
</html>
