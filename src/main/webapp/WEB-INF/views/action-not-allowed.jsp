<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Action Not Allowed</title>
	</head>
	<body>
		<div align="center">
			<h2>Your are not allowed to do this action with role "${sessionScope.role}"</h2>
		    <br>
		    <h4>
				Return to Welcome Page <a href="redirectToWelcome">here</a>
			</h4>
		</div>
	</body>
</html>