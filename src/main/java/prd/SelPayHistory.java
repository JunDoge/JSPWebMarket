package prd;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import config.Const;
import config.ViewResolver;
import db.PrdDb;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import prdBean.ImgBean;
import prdBean.PayBean;
import prdBean.PayHistoryBean;
import prdBean.PrdBean;
import usrBean.UsrBean;

/**
 * Servlet implementation class SelPayHistory
 */
public class SelPayHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsrBean usr = (UsrBean)request.getSession().getAttribute(Const.LOGIN_USER);
		Map<String, Object> obj  = PrdDb.selPayHis(usr);
		

		request.setAttribute("prds", (List<PrdBean>)obj.get("prds"));
		request.setAttribute("imgs", (List<ImgBean>)obj.get("imgs"));
		request.setAttribute("pays", (List<PayBean>)obj.get("pays"));
		request.setAttribute("payhis", (List<PayHistoryBean>)obj.get("payhis"));
		
		ViewResolver.mainforward("usr/usrPayHistroy", request, response);
		
	}

}
