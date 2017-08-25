<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>spring security 需改密码页面</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
	<h3>我自己定义的需改密码页面</h3>
	<form method='POST' action="/security/savePassword">
		<label for="oldpassword">Old Password</label>: <input id="oldPassword"
			name="oldPassword" size="20" maxlength="50" type="password" /> <br />
		<label for="password">New Password</label>
		<input type="password" id="newPassword" name="newPassword" size="20"
			maxlength="50" />  <br>
		<input type="submit" value="Change Password" />
	</form>
</body>
</html>