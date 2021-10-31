<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Department Domain</title>
		<!-- disable form submit button if user has no rights for that action-->		
		<script type="text/javascript" src="<c:url value="/resources/js/disable-submit-button.js" />"></script>

	</head>
	<body>
		<div align="center">
			<h2>On this page you can create new department or see already saved departments</h2>
			<br>
			<form action="departmentDomain/newDepartment" method="GET">
				<input id="new-dep-btn" type="submit" value="Click here to add new Department"/>
			</form>
			<c:if test="${sessionScope.role == 'user'}">
				<p style="color:red;">--> Only for admins</p>
			     <script>
			     	disableSubmitButton("new-dep-btn")
			     </script>   
			</c:if>	
			<br>
			<form action="departmentDomain/listAllDepartments" method="POST">
				<input id="list-dep-btn" type="submit" value="Click here to see all Departments"/>
			</form>
			<c:if test="${sessionScope.role == 'user'}">
				<p style="color:red;">--> Only for admins</p>
			     <script>
			     	disableSubmitButton("list-dep-btn")
			     </script>   
			</c:if>
			<br>
			
			<!-- the following block could be used to edit / delete department. But its not in the business requirements and therefore not implemented
			
			<form action="departmentDomain/chooseDepartment" method="POST">
				<input id="delete-dep-btn" type="submit" value="Click here to delete or update department"/>
			</form>
			<c:if test="${sessionScope.role == 'user'}">
				<p style="color:red;">Only for admins</p>
			     <script>
			     	disableSubmitButton("delete-dep-btn")
			     </script>   
			</c:if>	
			
			 -->
			
			<br>
			<h4>
				Return to Welcome Page <a href="welcome">here</a>
			</h4>
		</div>
	</body>
</html>