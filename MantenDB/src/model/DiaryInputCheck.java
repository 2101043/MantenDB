package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DiaryInputCheck {
	public String execute(MantenDiaryInputCheck mantenDiaryInputCheck) {
		String msg = "" ;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		// 日付跨ぎチェック
		Date entryDate = mantenDiaryInputCheck.getEntryDate(); // エントリー日付
		Date dInpDate = mantenDiaryInputCheck.getdInpDate(); // 入力日付
		// ↓***TEST
		// System.out.println("entryDate = " + entryDate);
		// System.out.println("dInpDate = " + dInpDate);
		// ↑***TEST
		// 書式に合わせてエントリー日付と入力日付を文字列変換する
		String strEntryDate = formatter.format(entryDate);
		String strInpDate = formatter.format(dInpDate);
		// 文字列の比較
		if(!strEntryDate.equals(strInpDate)) {
		msg= "日付跨ぎのエラーが発生しました";
		}
		return msg;
		}
}
