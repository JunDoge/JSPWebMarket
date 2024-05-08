package usr;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import prdBean.PayHistoryBean;
import usrBean.UsrBean;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import com.google.gson.Gson;

import config.Const;
import config.Utils;
import config.ViewResolver;
import db.PrdDb;
import db.UsrDb;

/**
 * Servlet implementation class UsrUpdate
 */
public class UsrUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ViewResolver.commonforward("usr/usrUpdate", request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Enumeration<String> e = request.getParameterNames();
		HttpSession session = request.getSession();
		int result = 0;
		UsrBean usr = (UsrBean)request.getSession().getAttribute(Const.LOGIN_USER);
		if(e.hasMoreElements()) {
			String updateColumn = (String) e.nextElement();
			String updateValue = request.getParameter(updateColumn);
			System.out.println(updateColumn + updateValue);
			result = UsrDb.updUsr(updateColumn,updateValue, usr.getUsr_id());

		}
		
		
		if(result == 1) {
			UsrDb.emailValidate(usr);
			session.setAttribute(Const.LOGIN_USER, usr);
		}
		
		Gson gson = new Gson();

		String json = gson.toJson(result);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(json);
	}
}
