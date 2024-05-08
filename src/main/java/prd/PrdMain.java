package prd;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import config.Utils;
import config.ViewResolver;
import db.PrdDb;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import prdBean.ImgBean;
import prdBean.PagingBean;
import prdBean.PrdBean;

/**
 * Servlet implementation class PrdList
 */
public class PrdMain extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cat_id = request.getParameter("cat_id");
		List<PrdBean> prds = null;
		List<ImgBean> imgs = null;
		String subSQL = "";

		
		String pageNum = request.getParameter("pageNum");
		String search = request.getParameter("search");
		if(pageNum == null) {
			pageNum = "1";
		}
		

		
		if(cat_id != null) {
			subSQL = "where cat_id=" + cat_id;
	
		}else if(search != null){
			subSQL = "where title LIKE '%" + search + "%'";
		} 
		
		PagingBean paging = PrdDb.prdMaxRecode(subSQL);
		paging.setCat_id(cat_id);
		paging.setSearch(search);
		
		
		if(paging.getTotal_recode() == 0) {
			request.setAttribute("msg", "검색결과가 없습니다.");
			ViewResolver.mainforward("prd/prdMain",request, response);
			
			
		}else {
			int currentPage = Utils.parseStrToInt(pageNum);
			
			if(currentPage > paging.getTotal_recode()){
				currentPage = paging.getTotal_recode();
			}
			
			
			
			int pageSize = paging.getPageSize();
			int startRow = ((currentPage - 1) * pageSize + 1) - 1;
			paging.setStartRow(startRow);
			
			
			Map<String, Object> obj = PrdDb.prdSel(paging, subSQL);
			
			prds = (List<PrdBean>)obj.get("prds");
			imgs = (List<ImgBean>)obj.get("imgs");
			
			
			if((currentPage % 5) == 0) {
				paging.setStartRow((int)(currentPage - 4));
			}else if(currentPage > 5){
				paging.setStartRow((int)(currentPage - (currentPage % 5)) + 1);	
			}else{
				paging.setStartRow(1);	
			}
			

			
			
			request.setAttribute("paging", paging);
			request.setAttribute("lstPrd", prds);
			request.setAttribute("lstImg", imgs);
			ViewResolver.mainforward("prd/prdMain",request, response);
		}
	}

}
