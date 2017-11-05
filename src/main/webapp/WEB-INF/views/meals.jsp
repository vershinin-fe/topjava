<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Meals</title>
    <style type="text/css">
        <jsp:include page="../static/table.css"/>
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>

<table class="tg">
<tr>
    <th>ID</th>
    <th>Date</th>
    <th>Time</th>
    <th>Description</th>
    <th>Calories</th>
    <th colspan="2">Action</th>
</tr>
<c:forEach items="${mealList}" var="element">
    <c:choose>
        <c:when test = "${element.exceed == false}">
            <tr style="color: #077200;">
                <td>${element.id}</td>
                <td>${element.date}</td>
                <td>${element.time}</td>
                <td>${element.description}</td>
                <td>${element.calories}</td>
                <td><a href="meals?action=edit&mealId=<c:out value="${element.id}"/>">Update</a></td>
                <td><a href="meals?action=delete&mealId=<c:out value="${element.id}"/>">Delete</a></td>
            </tr>
        </c:when>
        <c:otherwise>
            <tr style="color: #ff0012">
                <td>${element.id}</td>
                <td>${element.date}</td>
                <td>${element.time}</td>
                <td>${element.description}</td>
                <td>${element.calories}</td>
                <td><a href="meals?action=edit&mealId=<c:out value="${element.id}"/>">Update</a></td>
                <td><a href="meals?action=delete&mealId=<c:out value="${element.id}"/>">Delete</a></td>
            </tr>
        </c:otherwise>
    </c:choose>
</c:forEach>
</table>
<p><a href="meals?action=insert">Add Meal</a></p>

</body>
</html>
