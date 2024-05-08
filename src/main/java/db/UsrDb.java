package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import prdBean.PayHistoryBean;
import prdBean.PrdBean;
import usrBean.BasketBean;
import usrBean.PhoneBean;
import usrBean.UsrBean;


public class UsrDb {
	
public static int codeInsert(PhoneBean usrInfo) {
		
		String sql ="insert into phone values(?,?)";
				
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setString(1, usrInfo.getPh_nm());
				ps.setString(2, usrInfo.getPh_code());
			}
			
			
	
		});
	}


public static int UsrInsert(UsrBean usr) {
	
	String ins ="insert into usr(addr,email,name,ph_nm) values(?,?,?,?)";
	String sel = "select last_insert_id() as usr_id;";
	JdbcTemplate.executeUpdate(ins, new JdbcUpdateInterface() {

		@Override
		public void update(PreparedStatement ps) throws SQLException {
			ps.setString(1, usr.getAddr());
			ps.setString(2, usr.getEmail());
			ps.setString(3, usr.getName());
			ps.setString(4, usr.getPh_nm());
		}
		
		

	});
	
	
	return JdbcTemplate.executeQuery(sel, new JdbcSelInterface() {
		
		@Override
		public int executeQuery(ResultSet rs) throws SQLException {
			if(rs.next()) {
				usr.setUsr_id(rs.getInt("usr_id"));
				return 1;
			}
			return 0;
		}
	});
	
}
	
	
	public static int emailValidate(UsrBean usr) {
		
		String sql ="select usr_id, email, name, ph_nm, addr "
				+ "from usr where email = ?" ;
				
		return JdbcTemplate.executeQuery(sql, new JdbcPreSelInterface() {
			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setString(1, usr.getEmail());
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				if(rs.next()) {
					usr.setUsr_id(rs.getInt("usr_id"));
					usr.setEmail(rs.getString("email"));
					usr.setName(rs.getString("name"));
					usr.setPh_nm(rs.getString("ph_nm"));
					usr.setAddr(rs.getString("addr"));
					return 1;
				}else {
					return 0;	
				}
				
				
			}
			
	
		});
	}
	
	
	
	
	public static int codeValidate(PhoneBean usrInfo) {
		
		String sql = " delete from phone where ph_nm = ? and ph_code = ?";
				
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setString(1, usrInfo.getPh_nm());
				ps.setString(2, usrInfo.getPh_code());
			}
			
			
	
		});
	}
	
	public static PayHistoryBean selBasket(int bk_id) {
		PayHistoryBean payHis = new PayHistoryBean();
		String sql = "select prd_id, size from basket where bk_id= ?";
		
		JdbcTemplate.executeQuery(sql, new JdbcPreSelInterface() {
			
			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, bk_id);
				
			}
			
			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				if(rs.next()) {
					System.out.println(rs.getInt("prd_id"));
					payHis.setPrd_id(rs.getInt("prd_id"));
					payHis.setSize(rs.getString("size"));
				}
				
				return 0;
			}
		});
		return payHis;
	}
	

	public static List<BasketBean> selBasket(UsrBean usr) {
		
		List<BasketBean> bks = new ArrayList<BasketBean>();
		
		
		String sql = "select p.prd_id, p.title, p.price, b.bk_id from prd p left join basket b on p.prd_id = b.prd_id where usr_id = ?";
		
		JdbcTemplate.executeQuery(sql, new JdbcPreSelInterface() {
			
			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, usr.getUsr_id());
				
			}
			
			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {
					PrdBean prd = new PrdBean();
					BasketBean bk = new BasketBean();
					bk.setPrd_id(rs.getInt("prd_id"));
					bk.setTitle(rs.getString("title"));
					bk.setPrice(rs.getInt("price"));
					bk.setBk_id(rs.getInt("bk_id"));
					
					bks.add(bk);
					
				}
				return 0;
			}
		});
		
		return bks;
	}


	public static int updUsr(String updateColumn, String updateValue, int usr_id) {
		String sql = "update usr set " + updateColumn + "= '" + updateValue + "' where usr_id=?";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, usr_id);
				
			}
		});
	}
}
