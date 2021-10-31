<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Regulation Overview</title>
	</head>
	<body>
		<div align="center">
			<h2>${titleMessage}</h2>
			<br>
			<table border="1">
	
				<th>Id</th>
				<th>Regulation type</th>
				<th>Regulation state</th>
				<th>Regulation details</th>
				<th>Creation date</th>
	
				<c:forEach var="regulation" items="${listRegulationForDepartment}">
					<tr>
	
						<td>${regulation.id}</td>
						<td>${regulation.type}</td>
						<td>${regulation.state}</td>
						<td>${regulation.details}</td>
						<td>${regulation.creationDate}</td>
					</tr>
				</c:forEach>
			</table>
			<h4>
				Return to Domain Page Regulation <a href="redirectToDomainPageRegulation">here</a>
			</h4>
		</div>
	</body>
</html>