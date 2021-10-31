<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login</title>
	</head>
	<body>
		<div align="center">
			<p><font color="red">${errorMessage}</font></p>
		    <form action="login" method="POST">
		        Username: <input name="userName" type="text" /> Password : <input name="password" type="password" /> <input type="submit" />
		    </form>
		    <br>
		    <h4>
				Return to Home <a href="redirectToHome">here</a>
			</h4>
		</div>
	</body>
</html>