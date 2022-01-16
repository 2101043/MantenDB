package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserEntryCheck {
	String stats;
	private final String JDBC_URL ="jdbc:h2:tcp://localhost/~/MantenDB";
	private final String DB_USER = "sa";
	private final String DB_PASS = "2434";
public String FindByCheck(User user) {

	if(!(user.getPass().equals(user.getPass2()))) {
		stats = "err";
		return stats;
	}
	try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
		String sql = "SELECT * FROM MANTENUSER WHERE USERID = ?";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setNString(1,  user.getId());
		ResultSet rs = pStmt.executeQuery();
		if(rs.next()) {
			stats="err2";
			return stats;
		}else {

		String sql2 = "INSERT INTO MANTENUSER(USERID,USERNAME,PASSWORD) VALUES(?,?,?)";
		pStmt = conn.prepareStatement(sql2);
		pStmt.setNString(1,  user.getId());
		pStmt.setNString(2,  user.getName());
		pStmt.setNString(3,  user.getPass());

		int r = pStmt.executeUpdate();


		if(r != 0) {
			stats="OK";
			return stats;
		}
		}

}catch(SQLException e){
	e.printStackTrace();
}
	return stats;
}
}

