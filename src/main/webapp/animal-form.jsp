<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
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
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<h1>form</h1>
<form method="POST" action="${pageContext.request.contextPath}/Animal">
    <div class="container">
        <p>Insert an animal into collection</p>
        <div class="form-group">
            <select name="kingdom">
                <c:forTokens items="REPTILES,FISH,AMPHIBIANS,BIRDS,MAMMALS,INVERTABRATES" delims="," var="nameKingdom">
                    <option value="<c:out value="${nameKingdom}"/>">
                        <c:out value="${nameKingdom}"/>
                    </option>
                </c:forTokens>
            </select>
        </div>
        <div class="form-group">
            Name:<input type="text" name="animalName">
        </div>
        <div class="form-group">
            <input type="submit" class="btn btn-default" value="Submit"/>
        </div>
    </div>
</form>

<p><a href="${pageContext.request.contextPath}/Animal">HOME</a></p>

</body>
</html>
