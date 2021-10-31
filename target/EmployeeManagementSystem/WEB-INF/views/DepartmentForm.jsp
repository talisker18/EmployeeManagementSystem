<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Department</title>
</head>
<body>
    <div align="center">
        <h2>New/Edit Department</h2>
        <br>
        <form:form action="newDepartment/saveDepartment" method="post" modelAttribute="department">
        <table>
            <form:hidden path="id"/>
            <tr>
                <td>Department Name:</td>
                <td><form:input id="departmentName" path="departmentName" /></td>
                <td>${inputDepartmentName}</td>
            </tr>
            
            <tr></tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Save"></td>
            </tr>
        </table>
        </form:form>
        <br>
        <h4>
			Return to Domain Page Department <a href="redirectToDomainPageDepartment">here</a>
		</h4>
    </div>
    <script type="text/javascript" src="<c:url value="/resources/js/emp-form-validator.js" />"></script>
</body>
</html>