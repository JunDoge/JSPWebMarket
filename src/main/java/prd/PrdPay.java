package prd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import com.google.gson.Gson;

import config.Const;
import config.Utils;
import db.PrdDb;
import db.UsrDb;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import prdBean.PayBean;
import prdBean.PayHistoryBean;
import usrBean.UsrBean;


public class PrdPay extends HttpServlet {
	private static final long serialVersionUID = 1L;

	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Enumeration<String> e = request.getParameterNames();
		PayBean pay = new PayBean();	
		UsrBean usr = (UsrBean)request.getSession().getAttribute(Const.LOGIN_USER);
		int result = 0;
		pay.setPayment(request.getParameter("payment"));
		pay.setTotal_price(request.getParameter("total_price"));
		pay.setUsr_id(usr.getUsr_id());
		
		PrdDb.insPay(pay);
		
		while(e.hasMoreElements()) {
			PayHistoryBean payHis;
			String s_bk_id = (String)e.nextElement();
			String total_count = request.getParameter(s_bk_id);
			
			int bk_id = Utils.parseStrToInt(s_bk_id);
			if(bk_id > 0) {
				payHis = UsrDb.selBasket(bk_id);
				payHis.setTotal_count(total_count);
				payHis.setPay_id(pay.getPay_id());
				
				result = PrdDb.insPayHistory(payHis);		
			}

			
	

		}
		
		if(result == 1) {
			result = PrdDb.allDelBasket(usr.getUsr_id());	
		}
		
		
		Gson gson = new Gson();

		String json = gson.toJson(result);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(json);
		
	}

}
