<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Meals</title>
    <style>
        <jsp:include page="../static/table.css"/>
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>

<table class="tg">
<tr>
    <th>Date</th>
    <th>Time</th>
    <th>Description</th>
    <th>Calories</th>
</tr>
<c:forEach items="${mealList}" var="element">
    <c:choose>
        <c:when test = "${element.exceed == false}">
            <tr style="color: #077200;">
                <td>${element.date}</td>
                <td>${element.time}</td>
                <td>${element.description}</td>
                <td>${element.calories}</td>
            </tr>
        </c:when>
        <c:otherwise>
            <tr style="color: #ff0012">
                <td>${element.date}</td>
                <td>${element.time}</td>
                <td>${element.description}</td>
                <td>${element.calories}</td>
            </tr>
        </c:otherwise>
    </c:choose>
</c:forEach>
</table>

</body>
</html>
