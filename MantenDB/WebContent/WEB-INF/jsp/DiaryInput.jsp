<%@ page language="java" contentType="text/html; charset=Windows-31J" %>
<jsp:include page="header.jsp" />
<%@ page import="model.MantenDiaryInput" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
// リクエストスコープに保存された満点Diaryの情報を取得する
MantenDiaryInput mantenDiaryInput =
(MantenDiaryInput)request.getAttribute("mantenDiaryInput");
// checkboxのチェック状態を保持する変数
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
// 現在の日付を取得
LocalDate nowDate = LocalDate.now();
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
// 日付跨ぎチェックのため現在日付（画面表示の日付）をhiddenで渡す
String inpDate = nowDate.format(formatter);
%>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<table>
<tr><td><font color=black>ようこそ <c:out value="${userName}"/>さん</font></td></tr>
</table>
<form action="/mantenDiary/DiaryInput" method="post">
<table>
<tr><td colspan=2 align="center">
<h3><c:out value="${datestr}" />の日記を入力してください</h3>
</td></tr>
</table>
<table>
<tr><td><input type="checkbox" checked name="Check1" reaonly onclick="return false;"
onfocus="this.blur();"></td><td>毎日日記を付ける</td></tr>
<tr><td><input type="checkbox" <%=checked2 %> name="Check2"></td><td>時間通りに起きる
</td></tr>
<tr><td><input type="checkbox" <%=checked3 %> name="Check3"></td><td>遅刻をしない
</td></tr>
<tr><td><input type="checkbox" <%=checked4 %> name="Check4"></td><td>栄養バランスを考
えた食事をする</td></tr>
<tr><td><input type="checkbox" <%=checked5 %> name="Check5"></td><td>ちゃんとした挨拶
をする</td></tr>
<tr><td colspan=2>今日の一言(空白可)</br>
<textarea rows="3" cols="40" wrap="soft" name="comment"><c:out
value="${mantenDiaryInput.getComment()}" />
</textarea></td></tr>
<tr><td colspan=2 align="center"><input type="submit" value="送信"></td></tr>
<tr><td colspan=2></td></tr>
<c:choose>
<c:when test="${action==null}">
<tr><td colspan=2 align="center"><a href="/mantenDiary/DiaryInput?action=pre">
←前日の日記を付ける</a></td></tr>
<tr><td><input type="hidden" name="day" value="today"></td></tr>
</c:when>
<c:otherwise>
<tr><td colspan=2 align="center"><a href="/mantenDiary/DiaryInput">翌日の日記
を付ける→</a></td></tr>
<tr><td><input type="hidden" name="day" value="yesterday"></td></tr>
</c:otherwise>
</c:choose>
<tr><td colspan=2 align="center"><a href="/mantenDiary/TopMenuDisp">メニューに戻る
</a></td></tr>
</table>
<input type="hidden" name="inpDate" value="<%=inpDate %>">
</form>
<jsp:include page="footer.jsp" />