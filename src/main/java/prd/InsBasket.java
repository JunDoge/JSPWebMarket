package prd;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import prdBean.PrdBean;
import usr.UsrBasket;
import usrBean.BasketBean;
import usrBean.UsrBean;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import config.Const;
import config.Utils;
import db.PrdDb;

/**
 * Servlet implementation class InsBasket
 */
public class InsBasket extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 BasketBean basket = new BasketBean();
		
		int prd_id = Utils.getIntParameter(request, "prd_id");
		
		HttpSession session = request.getSession();
		UsrBean usr = (UsrBean)session.getAttribute(Const.LOGIN_USER);
		
		basket.setPrd_id(prd_id);
		basket.setSize(request.getParameter("size"));
		basket.setUsr_id(usr.getUsr_id());
		
		int result = PrdDb.validateBasket(basket);
		
		if(result == 0) {
			result = PrdDb.insBasket(basket);	
		}else {
			result = 0;
		}
		
		
		 Gson gson = new Gson();    
		 String json = gson.toJson(result);    
		 System.out.println("json: "  + json);     
		 response.setCharacterEncoding("UTF-8");
		 response.setContentType("application/json");
		 PrintWriter out = response.getWriter();
		 out.print(json);
	}

}
