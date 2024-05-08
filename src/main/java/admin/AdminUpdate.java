package admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import prdBean.PrdBean;

import java.io.IOException;

import config.Utils;
import config.ViewResolver;
import db.AdminPrdDb;


public class AdminUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrdBean prd = new PrdBean();	
		prd.setPrice(Utils.getIntParameter(request, "price"));
		prd.setTitle(request.getParameter("title"));
		prd.setPrd_id(Utils.getIntParameter(request, "prd_id"));
		String count = "S" + request.getParameter("Ssize") + 
				"M" + request.getParameter("Msize") + "L" + request.getParameter("Lsize") ;
		prd.setCount(count);
		
		
		int result = AdminPrdDb.prdUpdate(prd);
		
		if(result==1) {
			response.sendRedirect("manager");
		}
	}

}
