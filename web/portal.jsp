<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test MVC</title>
    <style>
        tr:first-child {
            font-weight: bold;
            background-color: #C6C9C4;
        }
    </style>
</head>
<body>
<p>dateline:'${dateline}'</p>
<p>areaguid:'${areaguid}'</p>

<table>
    <tr>
        <td>dateline</td>
        <td>areaguid</td>
        <td>areaname</td>
        <td>meternllj</td>
        <td></td>
    </tr>
    <tr>
        <td>${one.dateline}</td>
        <td>${one.areaguid}</td>
        <td>${one.areaname}</td>
        <td>${one.meternllj}</td>
        <td></td>
    </tr>
</table>
</body>
</html>
