package db;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.internal.compiler.env.IModuleAwareNameEnvironment.LookupStrategy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import prdBean.Cat;
import prdBean.CmtBean;
import prdBean.ImgBean;
import prdBean.PagingBean;
import prdBean.PayBean;
import prdBean.PayHistoryBean;
import prdBean.PrdBean;
import usrBean.BasketBean;
import usrBean.UsrBean;

public class PrdDb {
	
	public static int insBasket(BasketBean basket) {
		String sql = "insert into basket(usr_id, prd_id, size) values(?,?,?)";
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, basket.getUsr_id());
				ps.setInt(2, basket.getPrd_id());
				ps.setString(3, basket.getSize());
			}
		});
		
	}
	
	
	public static int insCmt(CmtBean cmt) {
		String sql = "insert into cmt(usr_id, prd_id, c_des) values(?,?,?)";
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, cmt.getUsr_id());
				ps.setInt(2, cmt.getPrd_id());
				ps.setString(3, cmt.getC_des());
				
			}
		});
		
	}
	public static void insPay(PayBean pay) {
		String ins = "insert into pay(payment,total_price,usr_id) values(?,?,?)";
		String sel = "select last_insert_id() as pay_id";
		JdbcTemplate.executeUpdate(ins, new JdbcUpdateInterface() {
			
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setString(1, pay.getPayment());
				ps.setString(2, pay.getTotal_price());
				ps.setInt(3, pay.getUsr_id());
				
			}
		});
		
		
		JdbcTemplate.executeQuery(sel, new JdbcSelInterface() {
					
					
					
					@Override
					public int executeQuery(ResultSet rs) throws SQLException {
						if(rs.next()) {
							pay.setPay_id(rs.getInt("pay_id"));
		
						}
						return 0;
					}
				});
		
		
	}
	
	

	public static int insPayHistory(PayHistoryBean payHis) {
		String sql = "insert into payhistory(pay_id, prd_id,size,total_count) values(?,?,?,?)";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, payHis.getPay_id());
				ps.setInt(2, payHis.getPrd_id());
				ps.setString(3, payHis.getSize());
				ps.setString(4, payHis.getTotal_count());
			}
		});
		
	}



	
	
	public static PagingBean prdMaxRecode(String subSQL) {
		PagingBean paging = new PagingBean();
		String sql = "select ceil( count(*)/20) as total_recode FROM prd " + subSQL;
		JdbcTemplate.executeQuery(sql, new JdbcSelInterface() {
		
			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				if(rs.next()) {
					paging.setTotal_recode(rs.getInt("total_recode"));	
				}
				
				return 0;
			}
		});
		
		return paging;
	}
	

	
	public static Map<String, Object> prdSel(PagingBean paging, String subSQL) {
		List<PrdBean> prds = new ArrayList<PrdBean>();
		List<ImgBean> imgs = new ArrayList<ImgBean>();
		Map<String, Object> map = new HashMap<>();
		
		String sql = "SELECT i.img_id, p.title, p.price, p.prd_id FROM img i LEFT JOIN prd p ON i.prd_id = p.prd_id " + subSQL 
					+" GROUP BY i.prd_id HAVING MIN(i.i_dt) order by p.prd_id desc LIMIT ?,?";
		
		JdbcTemplate.executeQuery(sql, new JdbcPreSelInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, paging.getStartRow());
				ps.setInt(2, paging.getPageSize());
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				while (rs.next()) {
					PrdBean prd = new PrdBean();
					ImgBean img = new ImgBean();
					prd.setPrd_id(rs.getInt("prd_id"));
					prd.setTitle(rs.getString("title"));
					prd.setPrice(rs.getInt("price"));
					img.setImg_id(rs.getString("img_id"));
					prds.add(prd);
					imgs.add(img);
				}
				return 0;
			}
		});
		
		map.put("prds", prds);
		map.put("imgs", imgs);
		return map;

	}
	
	


	public static List<Cat> selCat() {
		List<Cat> cats = new ArrayList<Cat>();
		String sql = "select * from cat";
		JdbcTemplate.executeQuery(sql,  new JdbcSelInterface() {
			
			
			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {
					Cat cat = new Cat();
					cat.setCat_id(rs.getInt("cat_id"));
					cat.setCat_nm(rs.getString("cat_nm"));
					cat.setCat_des(rs.getString("cat_des"));
					cats.add(cat);
				}
				return 0;
			}
		});
		return cats;
	}


	
	



	public static void prdDetail(PrdBean prd) {
		String sql = "SELECT * from prd where prd_id = ?";

		JdbcTemplate.executeQuery(sql, new JdbcPreSelInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
					ps.setInt(1, prd.getPrd_id());
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				if (rs.next()) {
					prd.setView(rs.getInt("view"));
					prd.setCount(rs.getString("count"));
					prd.setTitle(rs.getString("title"));
					prd.setPrice(rs.getInt("price"));
				}
				return 0;
			}
		});


		
	}



	public static List<ImgBean> imgDetail(PrdBean prd) {
		List<ImgBean> imgs = new ArrayList<ImgBean>();
		String sql = "SELECT * from img where prd_id = ?";

		JdbcTemplate.executeQuery(sql, new JdbcPreSelInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
					ps.setInt(1, prd.getPrd_id());
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				while (rs.next()) {
					ImgBean img = new ImgBean();
					img.setImg_id(rs.getString("img_id"));
					imgs.add(img);
				}
				return 0;
			}
		});
		return imgs;
	}

	public static List<CmtBean> cmtSel(PrdBean prd) {
		List<CmtBean> cmts = new ArrayList<CmtBean>();

		String sql = "SELECT u.name, u.usr_id, c.c_des, c.cmt_id from usr u left join cmt c on u.usr_id = c.usr_id where prd_id = ?";

		JdbcTemplate.executeQuery(sql, new JdbcPreSelInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, prd.getPrd_id());
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				while (rs.next()) {
					CmtBean cmt = new CmtBean();
					cmt.setCmt_id(rs.getInt("cmt_id"));
					cmt.setC_des(rs.getString("c_des"));
					cmt.setName(rs.getString("name"));
					cmt.setUsr_id(rs.getInt("usr_id"));
					cmts.add(cmt);
				}
				return 0;
			}
		});
		return cmts;

	}
	

	public static void basketImg(ImgBean img) {
		String sql = "select img_id from img where prd_id= ? group by prd_id having min(i_dt);";

		JdbcTemplate.executeQuery(sql, new JdbcPreSelInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
					ps.setInt(1, img.getPrd_id());
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				if(rs.next()) {
					img.setImg_id(rs.getString("img_id"));
				}
				return 0;
			}
		});
	}




	public static int validateBasket(BasketBean basket) {
		String sql = "select * from basket where prd_id=? and usr_id=?";
		return JdbcTemplate.executeQuery(sql, new JdbcPreSelInterface() {
			
			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, basket.getPrd_id());
				ps.setInt(2, basket.getUsr_id());
				
			}
			
			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				if(rs.next()) {
					return 1;
				}
				return 0;
			}
		});
		
	}





	public static int allDelBasket(int usr_id) {
		String sql = "delete from basket where usr_id = ?";
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, usr_id);
			}
		});
	}


	public static int delBasket(int bk_id) {
			String sql = "delete from basket where bk_id = ?";
			return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
				
				@Override
				public void update(PreparedStatement ps) throws SQLException {
					ps.setInt(1, bk_id);
				}
			});
	}


	public static Map<String, Object> selPayHis(UsrBean usr) {
		List<PayBean> pays = new ArrayList<PayBean>();
		List<PayHistoryBean> usr_payhis = new ArrayList<PayHistoryBean>();
		List<PrdBean> prds = new ArrayList<PrdBean>();
		List<ImgBean> imgs = new ArrayList<ImgBean>();
		Map<String, Object> map = new HashMap<>();
		
		String sql = "select d.title, h.prd_id, h.total_count, h.pay_id, p.total_price, p.p_dt, h.size, i.img_id from payhistory h "
				+ "left join pay p on h.pay_id = p.pay_id "
				+ "left join prd d on h.prd_id = d.prd_id "
				+ "left join img i on d.prd_id = i.prd_id "
				+ "where p.usr_id = ? "
				+ "group by i.prd_id having min(i.i_dt)";
		
		
		JdbcTemplate.executeQuery(sql, new JdbcPreSelInterface() {
			
			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, usr.getUsr_id());
				
			}
			
			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {
					PayBean pay = new PayBean();
					PayHistoryBean payhis = new PayHistoryBean();
					PrdBean prd = new PrdBean();
					ImgBean img = new ImgBean();
					
					prd.setTitle(rs.getString("title"));
					prd.setPrd_id(rs.getInt("prd_id"));
				
					payhis.setTotal_count(rs.getString("total_count"));
					payhis.setPay_id(rs.getInt("pay_id"));
					
					pay.setTotal_price(rs.getString("total_price"));
					pay.setP_dt(rs.getString("p_dt"));
					payhis.setSize(rs.getString("size"));
					
					img.setImg_id(rs.getString("img_id"));
					
					
					pays.add(pay);
					usr_payhis.add(payhis);
					prds.add(prd);
					imgs.add(img);			
				}

				
				return 0;
			}
		});
		
		map.put("pays", pays);
		map.put("payhis", usr_payhis);
		map.put("prds", prds);
		map.put("imgs", imgs);
		
		return map;
	}


	public static int delPayHistroy(int pay_id) {
		String sql = "delete from  payhistory where pay_id =  ?";
		
		for(int i = 0; i<2; i++) {
			JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
				
				@Override
				public void update(PreparedStatement ps) throws SQLException {
					ps.setInt(1, pay_id);
					
				}
			});	
			
			sql = "delete from pay where pay_id = ?";
		}
		
		return 1;

	}
	
	public static int delCmt(int cmt_id) {
		String sql = "delete from cmt where cmt_id = ?";
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, cmt_id);
			}
		});
		
	}


	public static int updCmt(CmtBean cmt) {
		String sql = "update cmt set c_des= ? where cmt_id=?";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setString(1, cmt.getC_des());
				ps.setInt(2, cmt.getCmt_id());
				
			}
		});
	}





}
