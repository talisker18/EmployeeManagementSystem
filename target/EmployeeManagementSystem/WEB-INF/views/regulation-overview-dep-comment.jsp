<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Add comment to Regulation</title>
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
				<th>Your comment</th>
				<th>Action</th>
				
				<c:forEach var="commentDto" items="${commentList}">
					<tr>
						<td>${commentDto.regulationId}</td>
						<td>${commentDto.regulationType}</td>
						<td>${commentDto.regulationState}</td>
						<td>${commentDto.regulationDetails}</td>
						<td>${commentDto.regulationCreationDate}</td>
						<td>${commentDto.commentText}</td>
						<td><a href="addComment?commentId=${commentDto.commentId}&regulationId=${commentDto.regulationId}">Add / change Comment</a></td>
					</tr>
				</c:forEach>
			</table>
			<h4>
				Return to Domain Page Regulation <a href="redirectToDomainPageRegulation">here</a>
			</h4>
		</div>
	</body>
</html>