<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Employee Overview</title>
	</head>
	<body>
		<div align="center">
			<h2>Employee List</h2>
			<br>
			<table border="1">
	
				<th>Id</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Date of Birth</th>
				<th>Email</th>
				<th>Role</th>
				<th>Username</th>
				<th>Department</th>
	
				<c:forEach var="employee" items="${listEmployee}">
					<tr>
	
						<td>${employee.id}</td>
						<td>${employee.firstName}</td>
						<td>${employee.lastName}</td>
						<td>${employee.dateOfBirth}</td>
						<td>${employee.email}</td>
						<td>${employee.role}</td>
						<td>${employee.userName}</td>
						<td>${employee.department}</td>
					</tr>
				</c:forEach>
			</table>
			<h4>
				Return to Domain Page Employee <a href="redirectToDomainPageEmployee">here</a>
			</h4>
		</div>
	</body>
</html>