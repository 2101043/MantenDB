<%@ page language="java" contentType="text/html; charset=Windows-31J" %>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />
<center>
	<table>
		<tr><td><p><c:out value="${userId}" />さん</p></td></tr>
	</table>
</center>
<center>
	<table>
		<tr><td><h3>メニューより選んでください</h3></td></tr>
	</table>
</center>
<center>
	<table>
		<tr>
			<td>
			<form action="MantenDiary" name="form1" method="post">
				<input type="hidden" name="menu" id="menu" value="input">
				<p class="msr_btn13" ><a href="javascript:form1.submit()">日記を入力する</a></p>
			</form>
				<p class="msr_btn13"><a>過去の日記を見る</a></p>
				<p class="msr_btn13"><a>ランキングを見る</a></p>
				<p class="msr_btn13"><a>ユーザ情報を変更する</a></p>
				<p class="msr_btn13"><a>ログアウトする</a></p>

			</td>
		</tr>
	</table>
</center>

<jsp:include page="footer.jsp" />