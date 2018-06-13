<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ page import="foo.bar.animal.service.AnimalService" %>
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
    <title>Form</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<h1>form</h1>
<form method="POST" action="${pageContext.request.contextPath}/Animal/">
    <div class="container">
        <%--<c:if test="${pageContext.request.getServletPath().equals(\"/add\")}">--%>

        <c:if test="${requestScope['javax.servlet.forward.servlet_path'].equals('/add')}">
            <p>Insert an animal into collection</p>
        </c:if>
        <c:if test="${requestScope['javax.servlet.forward.servlet_path'].equals('/edit')}">
            <p>Edit an animal in collection</p>
        </c:if>
        <c:if test="${param.id != null || param.id.equals('')}">
            <input type="hidden" value="${param.id}" name="id"/>
        </c:if>

        <div class="form-group">
            Kingdom: <select name="kingdom">
                <c:forTokens items="REPTILES,FISH,AMPHIBIANS,BIRDS,MAMMALS,INVERTABRATES" delims="," var="nameKingdom">
                    <option value="<c:out value="${nameKingdom}"/>"
                            <c:if test="${nameKingdom.equals(animalEdit.getKingdom().toString())}"> selected</c:if>>
                        <c:out value="${nameKingdom}"/>
                    </option>
                </c:forTokens>
            </select>
        </div>
        <div class="form-group">
            Name:<input type="text" placeholder="Animal name..." name="animalName" <c:if
                test="${param.id != null || param.id.equals('')}"> value="${animalEdit.getName()}"</c:if>/>
        </div>
        <div class="form-group">
            Description:
            <textarea placeholder="Describe the animal here..." name="animalDescription" wrap="soft"
                      rows="4" cols="50"><c:if test="${param.id != null || param.id.equals('')}">${animalEdit.getDetails()}</c:if></textarea>
        </div>
        <div class="form-group">
            <input type="submit" class="btn btn-default" value="Submit"/>
        </div>
    </div>
</form>

<p><a href="${pageContext.request.contextPath}/">HOME</a></p>


</body>
</html>
