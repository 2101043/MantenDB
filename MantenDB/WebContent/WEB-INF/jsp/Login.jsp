<%@ page language="java" contentType="text/html; charset=Windows-31J" %>
<jsp:include page="header.jsp" />
<center>
<form action="Login" method="POST">
<table>
<tr><td colspan=2>
	<span class="label label-danger" style="color:red">${message}</span>
	<h3>ログインしてください</h3>
</td></tr>

<tr><td>UserID</td><td><input type=text id ="id" name="id"    title="半角英数字と_以外は入力できません" required></td></tr>
<tr><td>Password</td><td><input type=password id ="pass" name="pass"pattern="[a-zA-Z0-9]{1,15}" title="16文字以内で入力して下さい" required></td></tr>
<tr><td colspan=2></br><center><input type=submit value="ログイン"></center></td></tr>
<tr><td colspan=2></td></tr>
<tr><td colspan=2></br></br><center><a href="/MantenDB/UserEntry">新規登録はこちら</a></center></td></tr>
</table>
</center>
</form>
<jsp:include page="footer.jsp" />