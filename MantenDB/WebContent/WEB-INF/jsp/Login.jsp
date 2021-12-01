<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="manten.css">
<title>満点ダイアリー</title>
</head>
<body>
<h2>満点ダイアリー</h2>
<center>
<form action="Login" method="POST">
<table>
<tr><td colspan=2>
	<span class="label label-danger">${message}</span>
	<h3>ログインしてください</h3>
</td></tr>

<tr><td>UserID</td><td><input type=text id ="id" name="id"></td></tr>
<tr><td>Password</td><td><input type=password id ="pass" name="pass"></td></tr>
<tr><td colspan=2></br><center><input type=submit value="ログイン"></center></td></tr>
<tr><td colspan=2></td></tr>
<tr><td colspan=2></br></br><center><a href="NewUser.jsp">新規登録はこちら</a></center></td></tr>
</table>
</center>
</form>
</body>
</html>