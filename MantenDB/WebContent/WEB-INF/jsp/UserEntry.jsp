<%@ page language="java" contentType="text/html; charset=Windows-31J" %>
<jsp:include page="header.jsp" />
<center>
<table>
<span class="label label-danger" style="color:red">${message}</span>
</table>

<form action="UserEntry" method="POST">
<table>
<tr><td colspan=2>
	<h3>�K�{��������͂��Ă�������</h3>
</td></tr>
<tr><td>���[�U��</td><td><input type=text id="name" name="name" pattern="[A-Z]{1,9}" title="�S�p10�����ȓ�"></td></tr>
<tr><td colspan=2><center>�����[�U���͉�ʏ�ɕ\������܂�</center></td></tr>
<tr><td>UserID</td><td><input type=text id="id" name="id" title="���p�p������16�����ȓ�"></td></tr>
<tr><td colspan=2><center>��UserID�͔��p�p��L���ł�</br>
                  (�L����"_"�̂ݎg�p�\)</center></td></tr>
<tr><td>Password</td><td><input type=password id="pass" name="pass" ></td></tr>
<tr><td>Password(�ē���)</td><td><input type=password id="pass2" name="pass2"></td></tr>
<tr><td colspan=2></br><center><input type=submit value="�o�^"></center></td></tr>
<tr><td colspan=2></td></tr>
<tr><td colspan=2></br></br><center><a href="index.html">�C���f�b�N�X��ʂɖ߂�</a></center></td></tr>
</table>
</center>
</form>
<jsp:include page="footer.jsp" />