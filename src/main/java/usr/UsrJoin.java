package usr;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import usrBean.UsrBean;

import java.io.IOException;

import config.Const;
import db.UsrDb;


public class UsrJoin extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(request.getParameter("name"));
		System.out.println("rd_addr");
		
		UsrBean usrInfo = new UsrBean();
		usrInfo.setAddr(request.getParameter("rd_addr"));
		usrInfo.setEmail(request.getParameter("email"));
		usrInfo.setName(request.getParameter("name"));
		usrInfo.setPh_nm(request.getParameter("ph_nm"));
		
		int result = UsrDb.UsrInsert(usrInfo);
		if(result == 1) {
			HttpSession session = request.getSession();
			session.setAttribute(Const.LOGIN_USER, usrInfo);
			
	
		}
		
		response.sendRedirect("/prd/main");
	}

}
