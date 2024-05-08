package usr;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import db.UsrDb;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import usrBean.PhoneBean;
import usrService.ValidatePhoneService;

/**
 * Servlet implementation class ValidatePhone
 */
public class ValidatePhone extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String ph_nm = request.getParameter("ph_nm");
	    String code = ValidatePhoneService.sendSms(ph_nm) + "";
	    
	    
	    PhoneBean usrInfo = new PhoneBean();
	    usrInfo.setPh_nm(ph_nm);
	    usrInfo.setPh_code(code);
	    
	    UsrDb.codeInsert(usrInfo);
	    
	    Gson gson = new Gson();    
	    String json = gson.toJson(code);    
	    System.out.println("json: "  + json);     
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("application/json");
	    PrintWriter out = response.getWriter();
	    out.print(json);
	}

}
