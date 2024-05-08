package prdBean;

import usrBean.UsrBean;

public class CmtBean extends UsrBean {
	private int cmt_id;
	private int prd_id;
	private int usr_id;
	private String c_des;
	private String c_dt;
	
	
	public int getCmt_id() {
		return cmt_id;
	}
	public void setCmt_id(int cmt_id) {
		this.cmt_id = cmt_id;
	}
	public int getPrd_id() {
		return prd_id;
	}
	public void setPrd_id(int prd_id) {
		this.prd_id = prd_id;
	}
	public int getUsr_id() {
		return usr_id;
	}
	public void setUsr_id(int usr_id) {
		this.usr_id = usr_id;
	}
	public String getC_des() {
		return c_des;
	}
	public void setC_des(String c_des) {
		this.c_des = c_des;
	}
	public String getC_dt() {
		return c_dt;
	}
	public void setC_dt(String c_dt) {
		this.c_dt = c_dt;
	}
	
}
