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
<table>
<tr><td><font color=red>ユーザ名を入力してください</font></td></tr>
</table>

<form action="NewUserOK.html">
<table>
<tr><td colspan=2>
	<h3>必須事項を入力してください</h3>
</td></tr>
<tr><td>ユーザ名</td><td><input type=text></td></tr>
<tr><td colspan=2><center>※ユーザ名は画面上に表示されます</center></td></tr>
<tr><td>UserID</td><td><input type=text></td></tr>
<tr><td colspan=2><center>※UserIDは半角英語記号です</br>
                  (記号は"_"のみ使用可能)</center></td></tr>
<tr><td>Password</td><td><input type=password></td></tr>
<tr><td>Password(再入力)</td><td><input type=password></td></tr>
<tr><td colspan=2></br><center><input type=submit value="登録"></center></td></tr>
<tr><td colspan=2></td></tr>
<tr><td colspan=2></br></br><center><a href="index.html">インデックス画面に戻る</a></center></td></tr>
</table>
</center>
</form>
</body>
</html>