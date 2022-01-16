package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DiaryInputCheck;
import model.GetMantenDiaryLogic;
import model.IsMantenDiaryLogic;
import model.MantenDiaryInput;
import model.MantenDiaryInputCheck;
import model.MantenDiaryInputLogic;

/**
 * Servlet implementation class DiaryInput
 */
@WebServlet("/DiaryInput")
public class DiaryInput extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			// セッションスコープに保存されたユーザ情報を取得
			HttpSession session = request.getSession();
			String userId = (String)session.getAttribute("userId");
			// 前日処理なのか当日処理なのかを判断するパラメータを受け取る
			String action = request.getParameter("action");
			// 現在日時の取得
			GregorianCalendar cal = new GregorianCalendar();
			// SimpleDateFormat format = new SimpleDateFormat("yyyy年M月d日 E曜日");
			SimpleDateFormat format = new SimpleDateFormat("M月d日");
			// LocalDate nowDate = LocalDate.now();
			// DateTimeFormatter formater = DateTimeFormatter.ofPattern("M月d日");
			String diaryInputURL;
			if(action == null) { // actionが未入力であれば当日をセット
			diaryInputURL = "/WEB-INF/jsp/diaryInput.jsp";
			} else { // actionが入っていれば前日をセット
			diaryInputURL = "/WEB-INF/jsp/diaryInput.jsp?action=pre";
			cal.add(GregorianCalendar.DATE, -1); // 前日を求める
			// nowDate = nowDate.minusDays(1) ; // 前日を求める
			}
			String datestr = format.format(cal.getTime());
			// String datestr = nowDate.format(format);
			// 日付情報をリクエストスコープに保存
			request.setAttribute("datestr", datestr);
			// actionをリクエストスコープに保存
			request.setAttribute("action", action);
			// 指定されたuserIdと日にちのデータが存在するか確認
			IsMantenDiaryLogic bo = new IsMantenDiaryLogic();
			boolean result = bo.execute(userId, cal.getTime());
			// データの有無によって処理を分岐
			if (result) { // データ有りの時
			// 登録済みの日記データがあればそのデータを取得
			GetMantenDiaryLogic bo2 = new GetMantenDiaryLogic();
			MantenDiaryInput mantenDiaryInput = bo2.getMantenDiary(userId,cal.getTime());
			// 取得したデータをリクエストスコープに保存
			request.setAttribute("mantenDiaryInput", mantenDiaryInput);
			}else { // データ無しの時
			MantenDiaryInput mantenDiaryInput = null;
			// 空のデータをリクエストスコープに保存
			request.setAttribute("mantenDiaryInput", mantenDiaryInput);
			}
			// 満点Diary日記入力画面に遷移
			RequestDispatcher dispatcher =
			request.getRequestDispatcher(diaryInputURL);
			dispatcher.forward(request, response);
			}
			/**
			* 満点Diary日記入力画面を登録する処理
			* @Description：満点Diary日記入力画面の情報を受け登録または更新する
			* @param request response
			* @return なし
			*/
			protected void doPost(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
			// リクエストパラメータの取得
			request.setCharacterEncoding("UTF-8");
			String check1 = request.getParameter("Check1");
			String check2 = request.getParameter("Check2");
			String check3 = request.getParameter("Check3");
			String check4 = request.getParameter("Check4");
			String check5 = request.getParameter("Check5");
			String comment = request.getParameter("comment");
			String day = request.getParameter("day");
			String inpDate = request.getParameter("inpDate");
			// DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyyMMdd");
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			Date dInpDate = null; // 画面表示日を初期化
			try {
			dInpDate = format.parse(inpDate); // 渡された画面表示日を設定
			} catch(ParseException e) {
			e.printStackTrace();
			}
			int point = 0; // ポイント計算
			if(check1 != null && check1.length() != 0) {
			point +=20;
			}
			if(check2 != null && check2.length() != 0) {
			point +=20;
			}
			if(check3 != null && check3.length() != 0) {
			point +=20;
			}
			if(check4 != null && check4.length() != 0) {
			point +=20;
			}
			if(check5 != null && check5.length() != 0) {
			point +=20;
			}
			// dayの値により当日または前日を登録日に設定
			Date entryDate; // 登録日
			if(day.equals("today")) {
			entryDate = new Date(); // 当日を設定
			} else {
			Calendar calender = Calendar.getInstance();
			calender.setTime(new Date()); // カレンダーに当日をセット
			// カレンダーから1日減算
			calender.add(Calendar.DAY_OF_MONTH, -1);
			entryDate = calender.getTime(); // 登録日に前日を設定
			// 画面表示日をマイナス1日する
			calender.setTime(dInpDate);
			calender.add(Calendar.DAY_OF_MONTH, -1);
			dInpDate = calender.getTime(); // 画面表示日に前日を設定
			}
			// セッションスコープに保存されたユーザ情報を取得
			HttpSession session = request.getSession();
			String userId = (String)session.getAttribute("userId");
			// 満点Diaryインプットチェック情報のインスタンス作成
			MantenDiaryInputCheck mantenDiaryInputCheck = new MantenDiaryInputCheck(userId, entryDate,dInpDate,check1, check2, check3, check4, check5, comment,point);
			// 満点Diaryインプットチェックを実施
			DiaryInputCheck dic = new DiaryInputCheck();
			String errorMsg = dic.execute(mantenDiaryInputCheck);
			// 入力値チェック
			if ( errorMsg == "" ) {
			// 満点Diary登録情報のインスタンス作成
			MantenDiaryInput mantenDiaryInput =
			new MantenDiaryInput(userId, entryDate,check1,
			check2, check3, check4, check5, comment,point);
			// 満点Diary登録処理の実行
			MantenDiaryInputLogic bo = new MantenDiaryInputLogic();
			boolean result = bo.execute(mantenDiaryInput);
			// 満点Diary登録処理の成否によって処理を分岐
			if(result) { // 登録OKの時
			// 満点Diary登録完了画面にフォワード
			RequestDispatcher dispatcher =
			request.getRequestDispatcher("/WEB-INF/jsp/diaryInputOK.jsp");
			dispatcher.forward(request, response);
			}else { // 登録失敗の時
			// 登録に失敗した旨のエラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "登録に失敗しました");
			// 満点Diary登録失敗画面にフォワード
			RequestDispatcher dispatcher =
			request.getRequestDispatcher("/WEB-INF/jsp/diaryInputNG.jsp");
			dispatcher.forward(request, response);
			}
			} else {
			// 入力値エラー処理
			// 入力値に誤りがある旨のエラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", errorMsg);
			// 満点Diary登録失敗画面にフォワード
			RequestDispatcher dispatcher =
			request.getRequestDispatcher("/WEB-INF/jsp/diaryInputNG.jsp");
			dispatcher.forward(request, response);
			}
			}

}
