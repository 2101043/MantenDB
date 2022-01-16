<%@ page language="java" contentType="text/html; charset=Windows-31J" %>
<jsp:include page="header.jsp" />
<%@ page import="model.MantenDiaryInput" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
// ���N�G�X�g�X�R�[�v�ɕۑ����ꂽ���_Diary�̏����擾����
MantenDiaryInput mantenDiaryInput =
(MantenDiaryInput)request.getAttribute("mantenDiaryInput");
// checkbox�̃`�F�b�N��Ԃ�ێ�����ϐ�
String checked2 = "";
String checked3 = "";
String checked4 = "";
String checked5 = "";
if(mantenDiaryInput != null){
if (mantenDiaryInput.getCheck2().equals("on")){
checked2 = "checked";
}
if (mantenDiaryInput.getCheck3().equals("on")){
checked3 = "checked";
}
if (mantenDiaryInput.getCheck4().equals("on")){
checked4 = "checked";
}
if (mantenDiaryInput.getCheck5().equals("on")){
checked5 = "checked";
}
}
// ���݂̓��t���擾
LocalDate nowDate = LocalDate.now();
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
// ���t�ׂ��`�F�b�N�̂��ߌ��ݓ��t�i��ʕ\���̓��t�j��hidden�œn��
String inpDate = nowDate.format(formatter);
%>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<table>
<tr><td><font color=black>�悤���� <c:out value="${userName}"/>����</font></td></tr>
</table>
<form action="/mantenDiary/DiaryInput" method="post">
<table>
<tr><td colspan=2 align="center">
<h3><c:out value="${datestr}" />�̓��L����͂��Ă�������</h3>
</td></tr>
</table>
<table>
<tr><td><input type="checkbox" checked name="Check1" reaonly onclick="return false;"
onfocus="this.blur();"></td><td>�������L��t����</td></tr>
<tr><td><input type="checkbox" <%=checked2 %> name="Check2"></td><td>���Ԓʂ�ɋN����
</td></tr>
<tr><td><input type="checkbox" <%=checked3 %> name="Check3"></td><td>�x�������Ȃ�
</td></tr>
<tr><td><input type="checkbox" <%=checked4 %> name="Check4"></td><td>�h�{�o�����X���l
�����H��������</td></tr>
<tr><td><input type="checkbox" <%=checked5 %> name="Check5"></td><td>�����Ƃ������A
������</td></tr>
<tr><td colspan=2>�����̈ꌾ(�󔒉�)</br>
<textarea rows="3" cols="40" wrap="soft" name="comment"><c:out
value="${mantenDiaryInput.getComment()}" />
</textarea></td></tr>
<tr><td colspan=2 align="center"><input type="submit" value="���M"></td></tr>
<tr><td colspan=2></td></tr>
<c:choose>
<c:when test="${action==null}">
<tr><td colspan=2 align="center"><a href="/mantenDiary/DiaryInput?action=pre">
���O���̓��L��t����</a></td></tr>
<tr><td><input type="hidden" name="day" value="today"></td></tr>
</c:when>
<c:otherwise>
<tr><td colspan=2 align="center"><a href="/mantenDiary/DiaryInput">�����̓��L
��t���遨</a></td></tr>
<tr><td><input type="hidden" name="day" value="yesterday"></td></tr>
</c:otherwise>
</c:choose>
<tr><td colspan=2 align="center"><a href="/mantenDiary/TopMenuDisp">���j���[�ɖ߂�
</a></td></tr>
</table>
<input type="hidden" name="inpDate" value="<%=inpDate %>">
</form>
<jsp:include page="footer.jsp" />