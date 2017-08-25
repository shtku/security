<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
	<title>spring security 的登入页面</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body onload='document.f.j_username.focus();'>
	<h3>我自己定义的登录页面</h3>
	<form name='f' action='/security/j_spring_security_check' method='POST'>
		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='j_username' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='j_password' /></td>
			</tr>
			<tr>
				<td colspan='2'>
					<input id="_remeber_me" name="_remeber_me" type="checkbox" value="true"/>
					<lable for="_remeber_me">Remember Me?</lable>
				
				</td>
				
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="Login" /></td>
			</tr>
		</table>
		<div> username : </div>  
	</form>
</body>
</html>