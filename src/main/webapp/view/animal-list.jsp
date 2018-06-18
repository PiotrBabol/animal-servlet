<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: P B
  Date: 10.06.2018
  Time: 09:32
  To change this template use File | Settings | File Templates.

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="foo.bar.animal.service.AnimalService" %>
<html>
<head>
    <title>List</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<h1>list</h1>
<div class="table-responsive container">
    <p>you cannot remove first 6 initial animals</p>
    <table class="table">
        <tr>
            <th>name</th>
            <th>kingdom</th>
        </tr>
        <c:forEach items="${animalList}" var="nameKingdom">
            <tr>
                <td>
                    <a href="${pageContext.request.contextPath}/det?id=${nameKingdom.getId()}"><c:out value="${nameKingdom.getName()}"/></a>
                </td>
                <td>
                    <c:out value="${nameKingdom.getKingdom().toString().toLowerCase()}"/>
                </td>

            </tr>
        </c:forEach>
    </table>
</div>
<p><a href="${pageContext.request.contextPath}/">HOME</a></p>

</body>
</html>
