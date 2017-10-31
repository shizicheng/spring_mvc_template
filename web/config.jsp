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
<h2>List of Config</h2>
<c:forEach items="${config}" var="map">
    <c:out value="${map.key}"/>:<c:out value="${map.value}"/><br/>
</c:forEach>
<br/>
</body>
</html>
