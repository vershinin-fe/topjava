<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Edit Meal</title>
    <style>
        <jsp:include page="../static/table.css"/>
    </style>
</head>
<body>
<h2>Edit Meal</h2>
<p><a href="meals">Cancel</a></p>

<form method="POST" action='meals' name="frmAddMeal">
    <label>ID</label>
    <input type="text" readonly="readonly" name="mealid" value="<c:out value="${meal.id}" />" />
    <br/>
    <label>Date/Time</label>
    <input type="datetime-local" name="mealDate" value="<c:out value="${meal.dateTime}" />" />
    <br/>
    <label>Description</label>
    <input type="text" name="mealDescription" value="<c:out value="${meal.description}" />" />
    <br/>
    <label>Calories</label>
    <input type="number" name="mealCalories" min="0" max="3000" value="<c:out value="${meal.calories}" />" />
    <br/>
    <input class="button" type="submit" value="Submit" />
</form>

</body>
</html>
