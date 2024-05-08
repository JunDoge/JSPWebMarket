package prdBean;

public class PagingBean {
	private int startRow;
	private int pageSize = 20;
	private int total_recode;
	private int page_num;
	private String cat_id;
	private String search;
	
	
	
	
	public String getCat_id() {
		return cat_id;
	}
	public void setCat_id(String cat_id) {
		this.cat_id = cat_id;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getPageSize() {
		return pageSize;
	}

	public int getTotal_recode() {
		return total_recode;
	}
	public void setTotal_recode(int total_recode) {
		this.total_recode = total_recode;
	}
	public int getPage_num() {
		return page_num;
	}

	public void setPage_num(int page_num) {
		this.page_num = page_num;
	}
	
	

	
	
}
