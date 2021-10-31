<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Regulation</title>
</head>
<body>
    <div align="center">
        <h2>New/Edit Regulation</h2>
        <br>
        <form:form action="newRegulation/saveRegulation" method="post" modelAttribute="regulationDepartmentDto">
        <table>
            <form:hidden path="regulationId"/>
            <tr>
                <td>Regulation Type:</td>
                <td><form:input id="regulationType" path="type" /></td>
                <td>${inputRegulationType}</td>
            </tr>
            
            <tr>
                <td>Regulation Details:</td>
                <td><form:input id="regulationDetails" path="details" /></td>
                <td>${inputRegulationDetails}</td>
            </tr>
            
            <tr>
                <td>Creation Date:</td>
                <td><form:input id="regulationCreationDate" type="date" path="creationDate" /></td>
                <td><p>${inputRegulationCreationDate}</p></td>
            </tr>
            
            <tr>
                <td>Department</td>
                <td>
        			<form:select path="departmentId"> 
        				<c:forEach var="department" items="${departmentList}">
        				<!-- pre-select department value if an regulation is edited, see: https://stackoverflow.com/questions/28994625/how-to-add-cif-jstl-within-formoption -->
        					<form:option value="${department.id}" selected="${department.id == regulationDepartmentDto.departmentId? 'selected' : '' }">${department.departmentName}</form:option> 
        				</c:forEach>  
			        </form:select>  
                </td>
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