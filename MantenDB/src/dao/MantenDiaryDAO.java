package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.MantenDiaryInput;
import model.MantenDiaryList;

public class MantenDiaryDAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/MantenDB";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	/**
	* 満点Diaryデータ検索
	* @Description：ユーザIDと日付もとにダイアリーデータを求める
	* @param ユーザID
	* @param 登録日付
	* @return 満点Diaryデータ登録情報
	*/
	public MantenDiaryInput findMantenDiary(String userId, Date entryDate) {
	MantenDiaryInput mantenDiaryInput = null; // 満点Diaryのインスタンス変数
	// データベースへ接続
	try(Connection conn =
	DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
	// SELECT文を準備
	String sql = "SELECT UserID,EntryDate,Check1,Check2,Check3,Check4,Check5,Comment,PointFROM MantenDiary WHERE UserID = ? AND EntryDate = ?";
	// PreparedStatementを準備してログイン情報をパラメータにセット
	PreparedStatement pStmt = conn.prepareStatement(sql);
	pStmt.setString(1, userId);
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMdd");
	pStmt.setString(2, dateFormat.format(entryDate));
	// SQLを実行し結果表を取得
	ResultSet rs = pStmt.executeQuery();
	// 結果表を１件読み込む
	if(rs.next()) { // 一致したユーザーが存在した場合
	// 結果表からデータを取得
	String UserId = rs.getString("UserID");
	Date EntryDate = rs.getDate("EntryDate");
	String Check1 = "";
	String Check2 = "";
	String Check3 = "";
	String Check4 = "";
	String Check5 = "";
	if(rs.getString("Check1").equals("1")) {
	Check1 = "on";
	}
	if(rs.getString("Check2").equals("1")) {
	Check2 = "on";
	}
	if(rs.getString("Check3").equals("1")) {
	Check3 = "on";
	}
	if(rs.getString("Check4").equals("1")) {
	Check4 = "on";
	}
	if(rs.getString("Check5").equals("1")) {
	Check5 = "on";
	}
	String Comment = rs.getString("Comment");
	// TEST MantenDiaryDAO findMantenDiary 2
	// System.out.println("今日の一言 =" + Comment);
	int point = rs.getInt("point");
	// 読み込まれたデータでMantenDiaryInputのインスタンスを作成
	mantenDiaryInput = new
	MantenDiaryInput(UserId,EntryDate,Check1,Check2,Check3,Check4,Check5,Comment,point);
	}
	}catch(SQLException e) {
	e.printStackTrace();
	// return null;
	}
	// MantenDiaryInputデータを返す
	return mantenDiaryInput;
	}
	/**
	* 指定年月満点Diaryデータ日付リスト取得
	* @Description：ユーザIDと年月をもとにダイアリーデータの存在する日付を求める
	* @param ユーザID
	* @param 年月
	* @return 満点Diaryデータが存在する日付リスト
	*/
	public List<Integer> monthDiaryCal(String userId,String yearMonth) {
	// カレンダー格納領域の定義
	List<Integer> calData = new ArrayList<>();
	Calendar cal = Calendar.getInstance();
	// カレンダーに当月（URLで渡された値）１日をセット
	cal.set(Integer.parseInt(yearMonth.substring(0, 4)),
	Integer.parseInt(yearMonth.substring(4, 6))-1,1);
	// その月の最終日を求める
	int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	// 日付範囲の開始日
	// 月初日付の文字列を求める
	String firstDay = yearMonth + "01";
	// 月末日付の文字列を求める
	String lastDay = yearMonth + String.format("%02d",lastDate) ;
	// データベースへ接続
	try(Connection conn =
	DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
	// SELECT文を準備
	String sql = "SELECT EntryDate, TO_CHAR(EntryDate,'yyyymmdd') FROM MantenDiary WHERE UserID = ? AND ( TO_CHAR(EntryDate,'yyyymmdd')BETWEEN ? AND ? )  ORDER BY EntryDate";
	// PreparedStatementを準備してインプット情報をパラメータにセット
	PreparedStatement pStmt = conn.prepareStatement(sql);
	pStmt.setString(1, userId);
	pStmt.setString(2, firstDay);
	pStmt.setString(3, lastDay);
	// SQLを実行し結果表を取得
	ResultSet rs = pStmt.executeQuery();
	// 結果表を順に読み込みながら格納領域にセットする
	int intDate ; // 日付
	while (rs.next()) { // 日付データが存在した場合
	// 日付を取り出しintに変換
	intDate = Integer.parseInt(rs.getString(2).substring(6,
	8));
	// calDataリスト に 日付データを追加
	calData.add(intDate);
	}
	}catch(SQLException e) {
	e.printStackTrace();
	// return null;
	}
	return calData;
	}
	/**
	* 指定年月満点Diaryデータリスト表示用データ取得
	* @Description：ユーザIDと年月をもとに存在するダイアリーデーを求める
	* @param ユーザID
	* @param 年月
	* @return 満点Diaryデータが存在するデータのリスト
	*/
	public List<MantenDiaryList> monthDiaryList(String userId,String yearMonth) {
	// リストデータ格納領域の定義
	List<MantenDiaryList> listData = new ArrayList<>();
	Calendar cal = Calendar.getInstance();
	// カレンダーに当月（URLで渡された値）１日をセット
	cal.set(Integer.parseInt(yearMonth.substring(0, 4)),
	Integer.parseInt(yearMonth.substring(4, 6))-1,1);
	// その月の最終日を求める
	int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	// 日付範囲の開始日
	// 月初日付の文字列を求める
	String firstDay = yearMonth + "01";
	// 月末日付の文字列を求める
	String lastDay = yearMonth + String.format("%02d",lastDate) ;
	// データベースへ接続
	try(Connection conn =
	DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
	// SELECT文を準備
	String sql = "SELECT ENTRYDATE, TO_CHAR(ENTRYDATE,'yyyymmdd') POINT,COMMENT  FROM MANTENDIARY WHERE USERID = ?  AND ( TO_CHAR(ENTRYDATE,'yyyymmdd')BETWEEN ? AND ? ) ORDER BY EntryDate";
	// PreparedStatementを準備してインプット情報をパラメータにセット
	PreparedStatement pStmt = conn.prepareStatement(sql);
	pStmt.setString(1, userId);
	pStmt.setString(2, firstDay);
	pStmt.setString(3, lastDay);
	// SQLを実行し結果表を取得
	ResultSet rs = pStmt.executeQuery();
	// 結果表を順に読み込みながら格納領域にセットする
	int intDate ; // 日付
	int point; // 点数
	String comment;// 今日の一言
	while (rs.next()) { // 日付データが存在した場合
	// 日付を取り出しintに変換
	intDate = Integer.parseInt(rs.getString(2).substring(6,
	8));
	// リストに追加するデータの準備
	point = rs.getInt("POINT");
	comment = rs.getString("COMMENT");
	MantenDiaryList data = new
	MantenDiaryList(intDate,point,comment);
	// リスト にデータを追加
	listData.add(data);
	}
	}catch(SQLException e) {
	e.printStackTrace();
	// return null;
	}
	return listData;
	}
	/**
	* 満点Diaryデータ存在チェック
	* @Description：指定したuserIdと日付で満点Diaryデータがあるかどうかを確認する
	* @param userId ユーザID
	* @param entryDate 日付
	* @return 満点Diaryデータあり:true なし:false
	*/
	public boolean isMantenDiary(String userId, Date entryDate) {
	boolean result = false; // resultを初期値falseにセット
	// データベースへ接続
	try(Connection conn =
	DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
	// SELECT文を準備
	String sql = "SELECT UserID,EntryDate FROM MantenDiary WHERE UserID = ? ANDEntryDate = ?";
	// PreparedStatementを準備してログイン情報をパラメータにセット
	PreparedStatement pStmt = conn.prepareStatement(sql);
	pStmt.setString(1, userId);
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMdd");
	pStmt.setString(2, dateFormat.format(entryDate));
	// SQLを実行し結果表を取得
	ResultSet rs = pStmt.executeQuery();
	// 結果表を順に読み込む
	if(rs.next()) { // 一致したユーザーが存在した場合
	// result に true をセット
	result = true;
	}
	}catch(SQLException e) {
	e.printStackTrace();
	// return null;
	}
	return result;
	}
	/**
	* 満点ダイアリー日記管理テーブル登録処理
	* @Description：インプット情報をもとに満点ダイアリー日記管理テーブルにデータ
	を登録する
	* @param mantenDiaryInput 満点ダイアリーインプット情報
	* @return 登録成功:true 登録失敗:false
	*/
	public boolean create(MantenDiaryInput mantenDiaryInput) {
	boolean result = false; //戻り値にfalseをセット
	// データベースへ接続
	try(Connection conn =
	DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
	// INSERT文を準備
	String sql = "INSERT INTO MantenDiary(UserID,EntryDate,Check1,Check2,Check3,Check4,Check5,Comment,Point)VALUES(?,?,?,?,?,?,?,?,?)";
	//PreparedStatementのインスタンスを生成
	PreparedStatement pStmt = conn.prepareStatement(sql);
	//パラメータの設定
	pStmt.setString(1, mantenDiaryInput.getUserId());
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMdd");
	pStmt.setString(2,
	dateFormat.format(mantenDiaryInput.getEntryDate()));
	if(mantenDiaryInput.getCheck1() == null ||
	mantenDiaryInput.getCheck1().length() == 0) {
	pStmt.setString(3, "0"); // チェック無し
	} else {
	pStmt.setString(3, "1"); // チェック有り
	}
	if(mantenDiaryInput.getCheck2() == null ||
	mantenDiaryInput.getCheck2().length() == 0) {
	pStmt.setString(4, "0"); // チェック無し
	} else {
	pStmt.setString(4, "1"); // チェック有り
	}
	if(mantenDiaryInput.getCheck3() == null ||
	mantenDiaryInput.getCheck3().length() == 0) {
	pStmt.setString(5, "0"); // チェック無し
	} else {
	pStmt.setString(5, "1"); // チェック有り
	}
	if(mantenDiaryInput.getCheck4() == null ||
	mantenDiaryInput.getCheck4().length() == 0) {
	pStmt.setString(6, "0"); // チェック無し
	} else {
	pStmt.setString(6, "1"); // チェック有り
	}
	if(mantenDiaryInput.getCheck5() == null ||
	mantenDiaryInput.getCheck5().length() == 0) {
	pStmt.setString(7, "0"); // チェック無し
	} else {
	pStmt.setString(7, "1"); // チェック有り
	}
	pStmt.setString(8, mantenDiaryInput.getComment());
	pStmt.setInt(9, mantenDiaryInput.getPoint());
	// SQLを実行(戻り値：追加数)
	int num = pStmt.executeUpdate();
	// データ登録できた場合
	if(num > 0) {
	// trueを返す
	result = true;
	}else {
	// falseを返す
	result = false;
	}
	}catch(SQLException e) {
	e.printStackTrace();
	return result;
	}
	// 結果を返す
	return result;
	}
	/**
	* 満点ダイアリー日記管理テーブル更新処理
	* @Description：インプット情報をもとに満点ダイアリー日記管理テーブルにデータ
	を更新する
	* @param mantenDiaryInput 満点ダイアリーインプット情報
	* @return 更新成功:true 登録失敗:false
	*/
	public boolean update(MantenDiaryInput mantenDiaryInput) {
	boolean result = false; //戻り値にfalseをセット
	// データベースへ接続
	try(Connection conn =
	DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
	// UPDATE文を準備
	String sql = "UPDATE MantenDiary SET"
	+ " Check1 = ?,"
	+ " Check2 = ?,"
	+ " Check3 = ?,"
	+ " Check4 = ?,"
	+ " Check5 = ?,"
	+ " Comment = ?, "
	+ " Point = ? "
	+ " WHERE UserID = ? AND EntryDate = ?";
	//PreparedStatementのインスタンスを生成
	PreparedStatement pStmt = conn.prepareStatement(sql);
	//パラメータの設定
	if(mantenDiaryInput.getCheck1() == null ||
	mantenDiaryInput.getCheck1().length() == 0) {
	pStmt.setString(1, "0"); // チェック無し
	} else {
	pStmt.setString(1, "1"); // チェック有り
	}
	if(mantenDiaryInput.getCheck2() == null ||
	mantenDiaryInput.getCheck2().length() == 0) {
	pStmt.setString(2, "0"); // チェック無し
	} else {
	pStmt.setString(2, "1"); // チェック有り
	}
	if(mantenDiaryInput.getCheck3() == null ||
	mantenDiaryInput.getCheck3().length() == 0) {
	pStmt.setString(3, "0"); // チェック無し
	} else {
	pStmt.setString(3, "1"); // チェック有り
	}
	if(mantenDiaryInput.getCheck4() == null ||
	mantenDiaryInput.getCheck4().length() == 0) {
	pStmt.setString(4, "0"); // チェック無し
	} else {
	pStmt.setString(4, "1"); // チェック有り
	}
	if(mantenDiaryInput.getCheck5() == null ||
	mantenDiaryInput.getCheck5().length() == 0) {
	pStmt.setString(5, "0"); // チェック無し
	} else {
	pStmt.setString(5, "1"); // チェック有り
	}
	pStmt.setString(6, mantenDiaryInput.getComment());
	pStmt.setInt(7, mantenDiaryInput.getPoint());
	pStmt.setString(8, mantenDiaryInput.getUserId());
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMdd");
	pStmt.setString(9,
	dateFormat.format(mantenDiaryInput.getEntryDate()));
	// SQLを実行(戻り値：更新数)
	int num = pStmt.executeUpdate();
	// データ更新できた場合
	if(num > 0) {
	// trueを返す
	result = true;
	}else {
	// falseを返す
	result = false;
	}
	}catch(SQLException e) {
	e.printStackTrace();
	return result;
	}
	// 結果を返す
	return result;
	}
	/**
	* 指定ユーザ登録日付最小値最大値取得
	* @Description：指定ユーザのEntryDateの最小値と最大値を求める
	* @param userId ユーザID
	* @return LocalDateのリスト:最小値、最大値
	*/
	public LocalDate[] getMinMaxEntryDate(String userId) {
	LocalDate[] entryDate = new LocalDate[2];
	// データベースへ接続
	try(Connection conn =
	DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
	// SELECT文を準備
	String sql = "SELECT MIN(ENTRYDATE) AS MINDATE,MAX(ENTRYDATE)AS MAXDATE FROM MantenDiary WHERE UserID = ?";
	// PreparedStatementを準備してログイン情報をパラメータにセット
	PreparedStatement pStmt = conn.prepareStatement(sql);
	pStmt.setString(1, userId);
	// SQLを実行し結果表を取得
	ResultSet rs = pStmt.executeQuery();
	// 結果表を順に読み込む
	if(rs.next()) { // 一致したユーザーが存在した場合
	// result に true をセット
	entryDate[0] =
	LocalDate.parse(rs.getString("MINDATE"));
	entryDate[1] =
	LocalDate.parse(rs.getString("MAXDATE"));
	}
	}catch(SQLException e) {
	e.printStackTrace();
	// return null;
	}
	return entryDate;
	}
	/**
	* 月間合計得点計算
	* @Description：指定ユーザの集計年月の合計ポイント数を計算する
	* @param userId ユーザID
	* @param entryMonth 集計年月
	* @return 月間合計得点
	*/
	public int calcMonthTotalPoint(String userId,String entryMonth) {
	int totalPoint = 0; // 月間集計ポイント
	Calendar cal = Calendar.getInstance();
	// カレンダーに当月（URLで渡された値）１日をセット
	cal.set(Integer.parseInt(entryMonth.substring(0, 4)),
	Integer.parseInt(entryMonth.substring(4, 6)),1);
	// その月の最終日を求める
	int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	// 日付範囲の開始日
	// 月初日付の文字列を求める
	String firstDay = entryMonth + "01";
	// 月末日付の文字列を求める
	String lastDay = entryMonth + String.format("%02d",lastDate) ;
	// データベースへ接続
	try(Connection conn =
	DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
	// SELECT文を準備
	String sql = "SELECT SUM(Point) FROM MantenDiary WHERE UserID = ? AND ( TO_CHAR(EntryDate,'yyyymmdd')BETWEEN ? AND ? ) ";
	// PreparedStatementを準備してインプット情報をパラメータにセット
	PreparedStatement pStmt = conn.prepareStatement(sql);
	pStmt.setString(1, userId);
	pStmt.setString(2, firstDay);
	pStmt.setString(3, lastDay);
	// SQLを実行し結果表を取得
	ResultSet rs = pStmt.executeQuery();
	// 結果表を順に読み込む
	if(rs.next()) { // データが存在した場合
	// 結果表から合計点を取得する
	totalPoint = rs.getInt(1);
	}
	}catch(SQLException e) {
	e.printStackTrace();
	// return null;
	}
	// MantenDiaryInputデータを返す
	return totalPoint;
	}
	/**
	* 年間合計得点計算
	* @Description：指定ユーザの集計年の合計ポイント数を計算する
	* @param userId ユーザID
	* @param entryYear 集計年
	* @return 年間合計得点
	*/
	public int calcYearTotalPoint(String userId,String entryYear) {
	int totalPoint = 0; // 年間集計ポイント
	// 日付範囲の開始日
	// 年初日付の文字列を求める
	String firstDay = entryYear + "0101";
	// 年末日付の文字列を求める
	String lastDay = entryYear + "1231" ;
	// データベースへ接続
	try(Connection conn =
	DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
	// SELECT文を準備
	String sql = "SELECT SUM(Point) FROM MantenDiary WHERE UserID = ?  AND ( TO_CHAR(EntryDate,'yyyymmdd')BETWEEN ? AND ? ) ";
	// PreparedStatementを準備してインプット情報をパラメータにセット
	PreparedStatement pStmt = conn.prepareStatement(sql);
	pStmt.setString(1, userId);
	pStmt.setString(2, firstDay);
	pStmt.setString(3, lastDay);
	// SQLを実行し結果表を取得
	ResultSet rs = pStmt.executeQuery();
	// 結果表を順に読み込む
	if(rs.next()) { // データが存在した場合
	// 結果表から合計点を取得する
	totalPoint = rs.getInt(1);
	}
	}catch(SQLException e) {
	e.printStackTrace();
	// return null;
	}
	// MantenDiaryInputデータを返す
	return totalPoint;
	}


}
