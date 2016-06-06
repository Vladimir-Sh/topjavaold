<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meal list</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>
<jsp:useBean id="mealList" class="java.util.ArrayList" scope="request"/>


<c:forEach items="${mealList}" var="cell">
    <td align="left">${cell.dateTime.toLocalDate()}</td>
    <td align="left">${cell.dateTime.toLocalTime()}</td>
    <td align="left">${cell.description}</td>
    <td align="left">${cell.calories}</td>
    <td align="left">${cell.exceed}</td>
    <br>
</c:forEach>



</body>
</html>
