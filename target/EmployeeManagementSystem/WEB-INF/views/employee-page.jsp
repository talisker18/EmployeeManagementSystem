<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Employee Domain</title>
		<!-- disable form submit button if user has no rights for that action-->		
		<script type="text/javascript" src="<c:url value="/resources/js/disable-submit-button.js" />"></script>

	</head>
	<body>
		<div align="center">
			<h2>On this page you can create, update, delete or see already saved employees</h2>
			<br>
			<form action="employeeDomain/newEmployee" method="GET">
				<input id="new-emp-btn" type="submit" value="Click here to open formular for adding new employee"/>
			</form>
			<c:if test="${sessionScope.role == 'user'}">
				<p style="color:red;">--> Only for admins</p>
			     <script>
			     	disableSubmitButton("new-emp-btn")
			     </script>   
			</c:if>	
			<br>
			<form action="employeeDomain/listAllEmployees" method="POST">
				<input type="submit" value="Click here to see all employees"/>
			</form>
			<br>
			<form action="employeeDomain/chooseEmployee" method="POST">
				<input id="delete-emp-btn" type="submit" value="Click here to delete or update employee"/>
			</form>
			<c:if test="${sessionScope.role == 'user'}">
				<p style="color:red;">--> Only for admins</p>
			     <script>
			     	disableSubmitButton("delete-emp-btn")
			     </script>   
			</c:if>	
			<br>
			<h4>
				Return to Welcome Page <a href=welcome>here</a> <!-- redirectToWelcome -->
			</h4>
		</div>
	</body>
</html>