<%@ page language="java" contentType="text/html; charset=Windows-31J" %>
<jsp:include page="header.jsp" />
<center>
<table>
<span class="label label-danger" style="color:red">${message}</span>
</table>

<form action="UserEntry" method="POST">
<table>
<tr><td colspan=2>
	<h3>必須事項を入力してください</h3>
</td></tr>
<tr><td>ユーザ名</td><td><input type=text id="name" name="name" pattern="[A-Z]{1,9}" title="全角10文字以内"></td></tr>
<tr><td colspan=2><center>※ユーザ名は画面上に表示されます</center></td></tr>
<tr><td>UserID</td><td><input type=text id="id" name="id" title="半角英数字の16文字以内"></td></tr>
<tr><td colspan=2><center>※UserIDは半角英語記号です</br>
                  (記号は"_"のみ使用可能)</center></td></tr>
<tr><td>Password</td><td><input type=password id="pass" name="pass" ></td></tr>
<tr><td>Password(再入力)</td><td><input type=password id="pass2" name="pass2"></td></tr>
<tr><td colspan=2></br><center><input type=submit value="登録"></center></td></tr>
<tr><td colspan=2></td></tr>
<tr><td colspan=2></br></br><center><a href="index.html">インデックス画面に戻る</a></center></td></tr>
</table>
</center>
</form>
<jsp:include page="footer.jsp" />