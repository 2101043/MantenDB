package model;

import java.util.Date;

import dao.MantenDiaryDAO;

public class IsMantenDiaryLogic {
		public boolean execute(String userId, Date entryDate) {
			MantenDiaryDAO dao = new MantenDiaryDAO();
			boolean result = dao.isMantenDiary(userId,entryDate);
			return result;
			}
	}

