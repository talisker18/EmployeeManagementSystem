<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Welcome!!</title>
		<link href="<c:url value="/resources/css/logout-link.css" />" rel="stylesheet">
	</head>
	<body>
		<div align="center">
			<h2>Welcome ${sessionScope.username} with role "${sessionScope.role}", what do you want to do next?</h2>
			<br>
			<form action="employeeDomain" method="GET">
				<input type="submit" style="background-color:#cc66ff;color:black;padding:15px 32px;text-align:center;text-decoration:none;display:inline-block;font-size:12px;" value="Click here to enter employee domain"/>
			</form>
			<br>
			<form action="departmentDomain" method="GET">
				<input type="submit" style="background-color:#66ff99;color:black;padding:15px 32px;text-align:center;text-decoration:none;display:inline-block;font-size:12px;" value="Click here to enter department domain"/>
			</form>
			<br>
			<form action="regulationDomain" method="GET">
				<input type="submit" style="background-color:#ffcc00;color:black;padding:15px 32px;text-align:center;text-decoration:none;display:inline-block;font-size:12px;" value="Click here to enter regulation domain"/>
			</form>
			<br>
			<form action="logout" method="POST">
				<input type="submit" style="background-color:#f44336;color:white;padding:15px 32px;text-align:center;text-decoration:none;display:inline-block;font-size:16px;" value="Logout"/>
			</form>
		</div>
	</body>
</html>