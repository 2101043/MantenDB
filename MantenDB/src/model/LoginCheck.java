package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginCheck {
	private final String JDBC_URL ="jdbc:h2:tcp://localhost/~/MantenDB";
	private final String DB_USER = "sa";
	private final String DB_PASS = "2434";
public boolean loginCheck(User use) {
	try {


		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
				String sql = "SELECT * FROM MANTENUSER WHERE USERID = ? AND PASSWORD = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setNString(1,  use.getId());
				pStmt.setNString(2,  use.getPass());

				ResultSet rs = pStmt.executeQuery();


				if(rs.next()) {
					return true;
				}else {
					return false;

				}
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}finally {

	}

}
}
