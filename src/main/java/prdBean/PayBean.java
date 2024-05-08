package prdBean;

public class PayBean {
	private int pay_id;
	private int usr_id;
	private String payment;
	private String total_price;
	private String p_dt;
	
	
	
	
	
	
	public String getP_dt() {
		return p_dt;
	}
	public void setP_dt(String p_dt) {
		this.p_dt = p_dt;
	}
	public int getPay_id() {
		return pay_id;
	}
	public void setPay_id(int pay_id) {
		this.pay_id = pay_id;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getTotal_price() {
		return total_price;
	}
	public void setTotal_price(String total_price) {
		this.total_price = total_price;
	}
	public int getUsr_id() {
		return usr_id;
	}
	public void setUsr_id(int usr_id) {
		this.usr_id = usr_id;
	}

	
}
