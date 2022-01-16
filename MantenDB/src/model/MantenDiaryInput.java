package model;

import java.util.Date;

public class MantenDiaryInput {
	private String userId; // ユーザID
	private Date entryDate; // 登録日
	private String check1; // チェック1
	private String check2; // チェック2
	private String check3; // チェック3
	private String check4; // チェック4
	private String check5; // チェック5
	private String comment; // コメント
	private int point; // ポイント
	// コンストラクタ
	public MantenDiaryInput(String userId, Date entryDate, String check1, String check2, String check3,String check4, String check5, String comment, int point) {
	this.userId = userId;
	this.entryDate = entryDate;
	this.check1 = check1;
	this.check2 = check2;
	this.check3 = check3;
	this.check4 = check4;
	this.check5 = check5;
	this.comment = comment;
	this.point = point;
	}
	// ゲッター
	public String getUserId() {
	return userId;
	}
	public Date getEntryDate() {
	return entryDate;
	}
	public String getCheck1() {
	return check1;
	}
	public String getCheck2() {
	return check2;
	}
	public String getCheck3() {
	return check3;
	}
	public String getCheck4() {
	return check4;
	}
	public String getCheck5() {
	return check5;
	}
	public String getComment() {
	return comment;
	}
	public int getPoint() {
	return point;
	}
}
