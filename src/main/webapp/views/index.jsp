<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<body>
	<div class="head">
		<c:url value="/logOut" var="ddfdd"/>
		<a href="${ddfdd }">Log Out</a>
	</div>
	<div class="change_password">
		<a href="<c:url value="/changePassword"/>">Change PassWord</a>
	</div>
	<h2>Hello World!</h2>
</body>
</html>
