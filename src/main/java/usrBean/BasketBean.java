package usrBean;

import prdBean.PrdBean;

public class BasketBean extends PrdBean{
	private int bk_id;
	private int prd_id;
	private int usr_id;
	private String size;
	
	
	
	
	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getBk_id() {
		return bk_id;
	}
	public void setBk_id(int bk_id) {
		this.bk_id = bk_id;
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
	
	
}
