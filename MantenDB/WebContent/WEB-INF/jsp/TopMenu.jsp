<%@ page language="java" contentType="text/html; charset=Windows-31J" %>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />
<center>
	<table>
		<tr><td><p><c:out value="${userId}" />����</p></td></tr>
	</table>
</center>
<center>
	<table>
		<tr><td><h3>���j���[���I��ł�������</h3></td></tr>
	</table>
</center>
<center>
	<table>
		<tr>
			<td>
			<form action="MantenDiary" name="form1" method="post">
				<input type="hidden" name="menu" id="menu" value="input">
				<p class="msr_btn13" ><a href="javascript:form1.submit()">���L����͂���</a></p>
			</form>
				<p class="msr_btn13"><a>�ߋ��̓��L������</a></p>
				<p class="msr_btn13"><a>�����L���O������</a></p>
				<p class="msr_btn13"><a>���[�U����ύX����</a></p>
				<p class="msr_btn13"><a>���O�A�E�g����</a></p>

			</td>
		</tr>
	</table>
</center>

<jsp:include page="footer.jsp" />