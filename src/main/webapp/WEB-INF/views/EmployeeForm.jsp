<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Employee</title>
</head>
<body>
    <div align="center">
        <h2>New/Edit Employee</h2>
        <br>
        <form:form action="newEmployee/saveEmployee" method="post" modelAttribute="employeeDepartmentDto">
        <table>
            <form:hidden path="employeeId"/>
            <tr>
                <td>First Name:</td>
                <td><form:input id="firstName" path="firstName" /></td>
                <td>${inputFirstname}</td>
            </tr>
            <tr>
                <td>Last Name:</td>
                <td><form:input id="lastName" path="lastName" /></td>
                <td>${inputLastname}</td>
            </tr>
            <tr>
                <td>User Name:</td>
                <td><form:input id="userName" path="userName" /></td>
                <td>${inputUsername}</td>
            </tr>
            <tr>
                <td>Date of Birth:</td>
                <td><form:input id="dateOfBirth" type="date" path="dateOfBirth" /></td>
                <td><p>${inputDob}</p></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><form:input id="email" path="email" /></td>
                <td><p>${inputEmail}</p></td>
            </tr>
            <tr>
                <td>Role:</td>
                <td>
                	User<form:radiobutton path="role" value="user" checked="true"/>
                	Admin<form:radiobutton path="role" value="admin"/>
                </td>
            </tr>
            <tr>
                <td>Department</td>
                <td>
        			<form:select path="departmentId"> 
        				<c:forEach var="department" items="${departmentList}">
        				<!-- pre-select department value if an employee is edited, see: https://stackoverflow.com/questions/28994625/how-to-add-cif-jstl-within-formoption -->
        					<form:option value="${department.id}" selected="${department.id == employeeDepartmentDto.departmentId? 'selected' : '' }">${department.departmentName}</form:option> 
        				</c:forEach>  
			        </form:select>  
                </td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><form:password path="password" /></td>
            </tr>
            <tr></tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Save"></td>
            </tr>
        </table>
        </form:form>
        <br>
        <h4>
			Return to Domain Page Employee <a href="redirectToDomainPageEmployee">here</a>
		</h4>
    </div>
    <script type="text/javascript" src="<c:url value="/resources/js/emp-form-validator.js" />"></script>
</body>
</html>