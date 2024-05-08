package admin;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import config.Utils;
import db.AdminValidateDb;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminValidatePwd
 */
public class AdminValidatePwd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		boolean result = Utils.isAdmin(request);

		Gson gson = new Gson();

		String json = gson.toJson(result);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(json);
	}

}
