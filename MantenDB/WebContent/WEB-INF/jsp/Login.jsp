<%@ page language="java" contentType="text/html; charset=Windows-31J" %>
<jsp:include page="header.jsp" />
<center>
<form action="Login" method="POST">
<table>
<tr><td colspan=2>
	<span class="label label-danger" style="color:red">${message}</span>
	<h3>���O�C�����Ă�������</h3>
</td></tr>

<tr><td>UserID</td><td><input type=text id ="id" name="id"    title="���p�p������_�ȊO�͓��͂ł��܂���" required></td></tr>
<tr><td>Password</td><td><input type=password id ="pass" name="pass"pattern="[a-zA-Z0-9]{1,15}" title="16�����ȓ��œ��͂��ĉ�����" required></td></tr>
<tr><td colspan=2></br><center><input type=submit value="���O�C��"></center></td></tr>
<tr><td colspan=2></td></tr>
<tr><td colspan=2></br></br><center><a href="/MantenDB/UserEntry">�V�K�o�^�͂�����</a></center></td></tr>
</table>
</center>
</form>
<jsp:include page="footer.jsp" />