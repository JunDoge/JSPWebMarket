package usr;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import usrBean.PhoneBean;
import usrBean.UsrBean;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import db.UsrDb;

/**
 * Servlet implementation class ValidateCode
 */
public class ValidateCode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ph_nm = request.getParameter("ph_nm");
		String ph_code = request.getParameter("ph_code");
		
		System.out.println(ph_nm + "     :    " + ph_code);
		
		PhoneBean usrInfo = new PhoneBean();
		usrInfo.setPh_code(ph_code);
		usrInfo.setPh_nm(ph_nm);
		
		int result = UsrDb.codeValidate(usrInfo);
		
	    Gson gson = new Gson();    
	    String json = gson.toJson(result);    
	    System.out.println("실행된..?: "  + json);     
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("application/json");
	    PrintWriter out = response.getWriter();
	    out.print(json);
		
	}

}
