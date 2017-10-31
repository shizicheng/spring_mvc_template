<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>University Enrollments</title>
    <style>
        tr:first-child {
            font-weight: bold;
            background-color: #C6C9C4;
        }
    </style>

</head>

<body>
<h2>List of ResAreaNllj</h2>
<table>
    <tr>
        <td>dateline</td>
        <td>areaguid</td>
        <td>areaname</td>
        <td>meternllj</td>
        <td></td>
    </tr>
    <c:forEach items="${list}" var="one">
        <tr>
            <td>${one.dateline}</td>
            <td>${one.areaguid}</td>
            <td>${one.areaname}</td>
            <td>${one.meternllj}</td>
            <td></td>
        </tr>
    </c:forEach>
</table>
<br/>
<a href="<c:url value='/new' />">Add New Employee</a>
</body>
</html>
