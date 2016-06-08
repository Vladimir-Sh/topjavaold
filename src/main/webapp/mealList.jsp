<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Meal list</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>
<jsp:useBean id="mealList" class="java.util.ArrayList" scope="request"/>

<table>
    <tr>
    <th>Дата</th>
    <th>Опсание</th>
    <th>Калории</th>
    </tr>

<c:forEach items="${mealList}" var="meal">
    <tr style="background: gainsboro; color: #080;">
    <c:if test="${meal.exceed}">
    <tr style="background: gainsboro; color: #800;">
    </c:if>

    <c:set var="cleanedDateTime" value="${fn:replace(meal.dateTime, 'T', ' ')}" />
    <fmt:parseDate value="${ cleanedDateTime }" pattern="yyyy-MM-dd HH:mm" var="parsedDateTime" type="both" />

    <td align="left"><fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }" /></td>
    <td align="left">${meal.description}</td>
    <td align="left">${meal.calories}</td>
    <td align="left">${meal.exceed}</td>

</tr>
</c:forEach>

    </table>

</body>
</html>
