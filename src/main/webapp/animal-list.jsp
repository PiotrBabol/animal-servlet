<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <table class="table">
        <tr>
            <th>name</th>
            <th>kingdom</th>
        </tr>
        <c:forEach items="${AnimalService.animalList}" var="nameKingdom">
            <tr>
                <td>
                    <a href="<c:url value = "/det"><c:param name = "id" value = "${nameKingdom.id}"/></c:url>">
                        <c:out value="${nameKingdom.name}"/>
                    </a>
                </td>

                <td><c:out value="${nameKingdom.getKingdom().toString().toLowerCase()}"/></td>

            </tr>
        </c:forEach>
    </table>
</div>
<p><a href="${pageContext.request.contextPath}/Animal">HOME</a></p>

</body>
</html>
