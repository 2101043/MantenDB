package model;

import dao.MantenDiaryDAO;

public class MantenDiaryInputLogic {
	/**
	* 満点ダイアリー登録実行処理
	* @Description：指定した満点ダイアリーインプット情報で満点ダイアリーを登録す
	る
	* @param mantenDiaryInput 満点ダイアリーインプット情報
	* @return 登録成功:true 登録失敗:false
	*/
	public boolean execute(MantenDiaryInput mantenDiaryInput) {
	boolean result = false; // 処理結果を初期値falseでセット
	MantenDiaryDAO dao = new MantenDiaryDAO();
	// 登録済みデータのチェック
	boolean inputOK = dao.isMantenDiary(mantenDiaryInput.getUserId(),
	mantenDiaryInput.getEntryDate());
	if(inputOK) { // データ登録済みの時
	// データ更新処理
	result = dao.update(mantenDiaryInput);
	}else { // データ未登録の時
	// データ登録処理
	result = dao.create(mantenDiaryInput);
	}
	return result;
	}

}
