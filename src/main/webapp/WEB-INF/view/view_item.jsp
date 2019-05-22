<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="pavel.demo.util.Mappings" %>
<html>
<head>
    <title>View Item</title>
</head>

<body>
<div align="center">

    <table border="1" cellpadding="5">

        <caption><h2>Todo Item</h2></caption>

        <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Details</th>
            <th>Deadline</th>
        </tr>

        <tr>
            <td><c:out value="${todoItem.id}"/></td>
            <td><c:out value="${todoItem.title}"/></td>
            <td><c:out value="${todoItem.details}"/></td>
            <td><c:out value="${todoItem.deadline}"/></td>
        </tr>

    </table>
    <br>
    <c:url var="backToItemsUrl" value="${Mappings.ITEMS}"/>
    <a href="${backToItemsUrl}">Back to list of items</a>

</div>
</body>

</html>