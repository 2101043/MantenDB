package model;

import java.util.Date;

import dao.MantenDiaryDAO;

public class GetMantenDiaryLogic {
	/**
	* 指定年月ダイアリー存在日取得処理
	* @Description：指定したユーザIDと年月のダイアリーが存在する日のリストを取得
	する
	* @param userId ユーザID
	* @param yearMonth 指定年月
	* @return ダイアリー存在日付のリスト
	*/
	public MantenDiaryInput getMantenDiary(String userId,Date entryDate) {
		MantenDiaryDAO dao = new MantenDiaryDAO();
		MantenDiaryInput mantenDiaryInput =
		dao.findMantenDiary(userId,entryDate);
		return mantenDiaryInput;
		}


}
