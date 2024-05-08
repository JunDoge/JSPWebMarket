package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import admin.AdminBean;

public class AdminValidateDb {
	public static int LoginCheck(AdminBean admin) {
		
		String sql = "SELECT id FROM ADMIN WHERE id= ? and pwd = ?";
		
		return JdbcTemplate.executeQuery(sql, new JdbcPreSelInterface() {
			
			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setString(1, admin.getId());
				ps.setString(2, admin.getPwd());
				
			}
			
			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				if (rs.next()) {					
					return 1;
				}
				return 0;
			}
		});
	}
}
