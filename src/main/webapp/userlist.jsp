<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User List</title>
</head>
<body>
    <h1>User List</h1>
    <table>
        <tr>
            <th>Name</th>
            <th>Mobile</th>
            <th>Email</th>
            <th>Address</th>
            <th>Password</th>
            <th>Date of Birth</th>
        </tr>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td>${user.name}</td>
                <td>${user.mobile}</td>
                <td>${user.email}</td>
                <td>${user.address}</td>
                <td>${user.password}</td>
                <td>${user.dob}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
