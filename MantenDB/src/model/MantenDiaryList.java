package model;

public class MantenDiaryList {
	private int entryDate; // 日付
	private int point; // 点数
	private String comment; // 今日の一言
	// コンストラクタ
	public MantenDiaryList(int entryDate, int point, String comment) {
	super();
	this.entryDate = entryDate;
	this.point = point;
	this.comment = comment;
	}
	// ゲッターおよびセッター
	public int getEntryDate() {
	return entryDate;
	}
	public void setEntryDate(int entryDate) {
	this.entryDate = entryDate;
	}
	public int getPoint() {
	return point;
	}
	public void setPoint(int point) {
	this.point = point;
	}
	public String getComment() {
	return comment;
	}
	public void setComment(String comment) {
	this.comment = comment;
	}

}
