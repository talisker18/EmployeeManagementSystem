<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Delete / update department</title>
	</head>
	<body>
		<div align="center">
			<h1>Department List</h1>
			
			<table border="1">
	
				<th>Id</th>
				<th>Department Name</th>
	
				<c:forEach var="department" items="${listDepartment}">
					<tr>
	
						<td>${department.id}</td>
						<td>${department.departmentName}</td>
						<td><a href="editDepartment?id=${department.id}">Edit</a>
							&nbsp;&nbsp;&nbsp;&nbsp; <a
							href="deleteDepartment?id=${department.id}">Delete</a></td>
	
					</tr>
				</c:forEach>
			</table>
			<h4>
				Return to Domain Page Department <a href="redirectToDomainPageDepartment">here</a>
			</h4>
		</div>
	</body>
</html>