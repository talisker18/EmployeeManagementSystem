<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Regulation Domain</title>
		<!-- disable form submit button if user has no rights for that action-->		
		<script type="text/javascript" src="<c:url value="/resources/js/disable-submit-button.js" />"></script>

	</head>
	<body>
		<div align="center">
			<h2>On this page you can create, update, delete or see already saved regulations</h2>
			<br>
			<form action="regulationDomain/newRegulation" method="GET">
				<input id="new-reg-btn" type="submit" value="Click here to add new Regulation"/>
			</form>
			<c:if test="${sessionScope.role == 'user'}">
				<p style="color:red;">--> Only for admins</p>
			     <script>
			     	disableSubmitButton("new-reg-btn")
			     </script>   
			</c:if>	
			<br>
			<form action="regulationDomain/listAllRegulations" method="POST">
				<input id="list-reg-btn" type="submit" value="Click here to see all Regulations"/>
			</form>
			<c:if test="${sessionScope.role == 'user'}">
				<p style="color:red;">--> Only for admins</p>
			     <script>
			     	disableSubmitButton("list-reg-btn")
			     </script>   
			</c:if>
			<br>
			
			<!-- the following block could be used to edit / delete regulation. But its not in the business requirements and therefore not implemented
			
			<form action="regulationDomain/chooseRegulation" method="POST">
				<input id="delete-reg-btn" type="submit" value="Click here to delete or update Regulation"/>
			</form>
			<c:if test="${sessionScope.role == 'user'}">
				<p style="color:red;">Only for admins</p>
			     <script>
			     	disableSubmitButton("delete-reg-btn")
			     </script>   
			</c:if>	
			
			 -->
	
			<form action="regulationDomain/showRegulationsForDepartment" method="POST">
				<input id="show-reg-dep-btn" type="submit" value="Click here to show Regulations for your department"/>
			</form>	
			<br>
			<form action="regulationDomain/showRegulationsForDepartmentAndAddComment" method="POST">
				<input id="show-reg-dep-btn" type="submit" value="Click here to add comment to a Regulation"/>
			</form>	
			<br>
			<h4>
				Return to Welcome Page <a href="welcome">here</a>
			</h4>
		</div>
	</body>
</html>