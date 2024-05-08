package prd;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import config.Utils;
import db.PrdDb;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DelCmt
 */
public class DelCmt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cmt_id = Utils.getIntParameter(request, "cmt_id");
		int result = PrdDb.delCmt(cmt_id);
		Gson gson = new Gson();

		String json = gson.toJson(result);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(json);
	}

}
