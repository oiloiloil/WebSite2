<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" href="css/jquery-ui-1.8.20.custom.css" rel="stylesheet" />
	<link type="text/css" href="css/login.css" rel="stylesheet" />
	<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="js/jquery-ui-1.8.20.custom.min.js"></script>
<title>WebSite Login</title>
</head>
<body>
	<form id="login_form" method="POST" action="login.do">
		<table>
				<tr>
					<td><span class="short_label">使用者帳號 : </span></td>
					<td>
						<span class="short_text"><input name="userName" type="text"/></span>
					</td>
				</tr>
				<tr>
					<td><span class="short_text">使用者密碼 : </span></td>
					<td>
						<span class="short_text"><input name="password" type="password"/></span>
					</td>
				</tr>
				<tr>
					<td><span><input id="login_button" type="submit" value="登入" /></span></td>
					<td>${errMsg}</td>
				</tr>
			</table>
	</form>
</body>
</html>