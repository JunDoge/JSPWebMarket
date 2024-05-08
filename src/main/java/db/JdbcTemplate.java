package db;

import java.sql.*;

public class JdbcTemplate {
	private static DbCon pool = DbCon.getInstance();

	// select용
	public static int executeQuery(String sql, JdbcPreSelInterface jdbc) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = pool.getConnection();
			ps = con.prepareStatement(sql);
			jdbc.prepared(ps);

			rs = ps.executeQuery();
			result = jdbc.executeQuery(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, ps, rs);
		}
		return result;
	}
	

	public static int executeQuery(String sql, JdbcSelInterface jdbc) {
		int result = 0;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = pool.getConnection();
			stmt = con.createStatement();

			rs = stmt.executeQuery(sql);
			result = jdbc.executeQuery(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, stmt, rs);
		}
		return result;
	}
	
	
		public static int executeUpdate(String sql, JdbcUpdateInterface jdbc) {
			int result = 0;
			Connection con = null;
			PreparedStatement ps = null;

			try {
				con = pool.getConnection();
				ps = con.prepareStatement(sql);

				jdbc.update(ps); // 바뀌는 부분

				result = ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con,ps);
			}

			return result;
		}
}
