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
<style>
	label, input { display:block; }
    input.text { margin-bottom:12px; width:95%; padding: .4em; }
    fieldset { padding:0; border:0; margin-top:25px; }
    h1 { font-size: 1.2em; margin: .6em 0; }
    div#users-contain { width: 350px; margin: 20px 0; }
    div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
    div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
    .ui-dialog .ui-state-error { padding: .3em; }
    .validateTips { border: 1px solid transparent; padding: 0.3em; color: red;}
</style>
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
					<td><input id="register_button" type="button" value="註冊新帳號"/></td>
					<td><span><input id="login_button" type="submit" value="登入" /></span></td>
					<td>${errMsg}</td>
				</tr>
			</table>
	</form>

	<div id="dialog-form" title="註冊新帳號">
  		<p class="validateTips">All form fields are required.</p>
 
  		<form id="submit-form">
    		<fieldset>
    		<!-- for表示label與哪個元件綁定，傳入元件的id -->
      		<label for="name">帳號</label>
      		<input type="text" name="name" id="name" placeholder="帳號 12碼英數字" maxlength="12" size="13" class="text ui-widget-content ui-corner-all" >
      		<label for="passwd">密碼</label>
      		<input type="password" name="passwd" id="passwd" placeholder="6~12碼英數字" maxlength="12" size="13" class="text ui-widget-content ui-corner-all">
      		<label for="confirmpasswd">再次輸入密碼</label>
      		<input type="password" name="confirmpasswd" id="confirmpasswd" placeholder="再次輸入密碼" maxlength="12" size="13" class="text ui-widget-content ui-corner-all">
      		<label for="phone">電話號碼</label>
      		<input type="text"  name="phone" id="phone" placeholder="09xxxxxxxx, 10碼數字" maxlength="10" size="11"  class="text ui-widget-content ui-corner-all">
      		<label for="email">Email</label>
      		<input type="text" name="email" id="email" placeholder="jane@smith.com" class="text ui-widget-content ui-corner-all">
      		
      		<label for="birth">生日</label>
      		<input type="text" id ="birth" name="birth" class="text ui-widget-content ui-corner-all" >
 
      		<!-- Allow form submission with keyboard without duplicating the dialog button -->
      		<input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
    		</fieldset>
  		</form>
	</div>
	
	<script>
		var dialog; // 註冊新帳號的的dialog
		$(document).ready(function() {
			initial(); // 需要先進行初始化的物件都先放在這裡面處理
			cleanDialogInput();
		})

		function initial() {
			$("#register_button").button();
			$("#login_button").button();
			dialog = $("#dialog-form").dialog({
				autoOpen: false,
				height: 400,
				width: 350,
				modal: true,
				buttons: {"註冊": addUser,	Cancel: function() {
					dialog.dialog( "close" );
				}},
				close: function() {
					console.log("dialog close!");
					cleanDialogInput();
				}
			});
		}

		$(function(){
			$("#birth").datepicker({
				dateFormat: "yy/mm/dd"});
		});

		function cleanDialogInput() {
			$("#submit-form input").val("");
		}

		function addUser() {
			var valid = true;
			var name = $("#name").val();
			var passwd = $("#passwd").val();
			var confirm = $("#confirmpasswd").val();
			var phone = $("#phone").val();
			var email = $("#email").val();
			var birth = $("#birth").val();
			var map = {
				name: name, passwd: passwd, confirm: confirm, phone: phone, email: email, birth: birth
			};
			if(checkUserInfo(map)) { // 輸入資料正確
				if(!isUserExist(name, passwd)) {
					dialog.dialog( "close" );
					$.ajax({
						url: "create.do",
						data: map,
						type: "post",
						success: function(result) {
						}
					});
					alert("註冊成功");
				}
				else {
					alert("此帳號已被註冊過")
				}
			}
			else { // 輸入資料錯誤
				alert("輸入資料有誤");
			}
			return valid;
		}

		function isUserExist(name, passwd) {
			var hsaUser = false;
			$.ajax({
				url: "findUser.do",
				dataType: "json",
				data: {
					name: name,
					passwd: passwd
				},
				success: function(result) {
					console.log(hasUser);
					if(result == "exist")
						hasUser = true;
				}
			});
			return hsaUser;
		}
		
		$("#register_button").click(function() {
			dialog.dialog( "open" );
		})

		// 傳入使用者的註冊資訊，並確認是否有資訊輸入錯誤
		function checkUserInfo(map) { 
			var result = true;
			if(isUserNameCorrect(map.name) == false) {
				result = false;
				alert("帳號輸入錯誤");
			}
			if(isPasswdCorrect(map.passwd) == false) {
				result = false;
				alert("密碼輸入錯誤");
			}
			if(isConfirmPasswdCorrect(map.passwd, map.confirm) == false) {
				result = false;
				alert("再次輸入密碼欄位錯誤");
			}
			if(isPhoneCorrect(map.phone) == false) {
				result = false;
				alert("電話格式錯誤");
			}
			if(isEmailCorrect(map.email) == false) {
				result = false;
				alert("Email格式錯誤");
			}
			return result;
		}
		function isUserNameCorrect(name) {
			var nameRule = /^[a-zA-Z]+[a-zA-Z0-9_]*/;
			if(name.search(nameRule) == -1)
				return false;
		}
		function isPasswdCorrect(passwd) {
			var passwdRule = /[a-zA-Z0-9]{6}/;
			if(passwd.search(passwdRule) == -1)
				return false;
		}
		function isConfirmPasswdCorrect(passwd, confirm) {
			return confirm == passwd? true: false;
		}
		function isPhoneCorrect(phone) {
			var phoneRule = /^(09)[0-9]{8}/;
			if(phone.search(phoneRule) == -1)
				return false;
		}
		function isEmailCorrect(email) {
			var emailRule = /^\w+((\.[A-Za-z0-9]+)|(\-[A-Za-z0-9]+))*@[A-Za-z0-9]+((\.|\-)([A-Za-z0-9]+))*\.[A-Za-z]+$/;
			if(email.search(emailRule) == -1)
				return false;
		}

	</script>
</body>
</html>