package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import prdBean.Cat;
import prdBean.ImgBean;
import prdBean.PrdBean;

public class AdminPrdDb {
	
	//insert 시작부분
	public static void prdIns(String sql) {
		JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {

			}

		});
	}
	
	public static void prdIns(PrdBean prd) {
		
		String ins = "insert into prd(p_dt,title,price,count,cat_id) values(NOW(),?,?,?,?)";
		String sel = "select last_insert_id() as prd_id";

		JdbcTemplate.executeUpdate(ins, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setString(1, prd.getTitle());
				ps.setInt(2, prd.getPrice());
				ps.setString(3, prd.getCount());
				ps.setInt(4, prd.getCat_id());

			}

		});
		
		
		JdbcTemplate.executeQuery(sel, new JdbcSelInterface() {
			
			
			
			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				if(rs.next()) {
					prd.setPrd_id(rs.getInt("prd_id"));

				}
				return 0;
			}
		});
	}
	
	
	public static void insImg(ImgBean img) {
		String sql = "insert into img(prd_id, img_id) values(?,?)";
		JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, img.getPrd_id());
				ps.setString(2, img.getImg_id());
			}

		});
		
	}
	
	
	
	//select시작하는부분
	public static List<Cat> selDes(String cat_nm) {
		List<Cat> cats = new ArrayList<Cat>();
		String sql = "select cat_id, cat_des from cat where cat_nm = ?";
		JdbcTemplate.executeQuery(sql, new JdbcPreSelInterface() {
			
			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setString(1, cat_nm);
			}
			
			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {
					Cat cat = new Cat();
					cat.setCat_id(rs.getInt("cat_id"));
					cat.setCat_des(rs.getString("cat_des"));
					cats.add(cat);

				}
				return 0;
			}
		});
		return cats;
	}



	//update 시작부분
	public static int updDesImage(String oldFileNm, String newFileName) {
		String sql = "update img set img_id= ? where img_id= ?";
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setString(1, newFileName);
				ps.setString(2, oldFileNm);
				
			}
		});
		
	}

	
	
	//delete 시작하는부분
	public static void prdDel(PrdBean prd) {
		String sql = "delete from img where prd_id = ?";
		for(int i = 0; i < 2; i++) {
			JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

				@Override
				public void update(PreparedStatement ps) throws SQLException {
					ps.setInt(1, prd.getPrd_id());
				}

			});
			
			sql = "delete from prd where prd_id = ?";
			
		}
		
	}

	public static int prdUpdate(PrdBean prd) {
		String sql = "update prd set title =?, price=?, count=? where prd_id=?";
		return JdbcTemplate.executeUpdate(sql,new JdbcUpdateInterface() {
			
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setString(1, prd.getTitle());
				ps.setInt(2, prd.getPrice());
				ps.setString(3, prd.getCount());
				ps.setInt(4, prd.getPrd_id());
				
			}
		});
	}






}
