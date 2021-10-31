<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add/change Comment</title>
</head>
<body>
    <div align="center">
        <h2>Add/change Comment</h2>
        <br>
        <form:form action="saveComment" method="post" modelAttribute="commentDto">
        <table>
            <form:hidden path="commentId"/>
            <form:hidden path="regulationId"/>
            <form:hidden path="userId"/>
            <tr>
                <td>Comment text:</td>
                <td><form:input id="commentText" path="commentText" /></td>
                <td>${inputCommentText}</td>
            </tr>
            <tr>
                <td>Creation date:</td>
                <td><form:input id="creationDateComment" type="date" path="commentCreationDate" /></td>
                <td><p>${inputCommentCreationDate}</p></td>
            </tr>
            <tr></tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Save"></td>
            </tr>
        </table>
        </form:form>
        <br>
        <h4>
			Return to Domain Page Regulation <a href="redirectToDomainPageRegulation">here</a>
		</h4>
    </div>
    <script type="text/javascript" src="<c:url value="/resources/js/emp-form-validator.js" />"></script>
</body>
</html>