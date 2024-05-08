package prd;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import config.Utils;
import db.PrdDb;

/**
 * Servlet implementation class DelBasket
 */
public class DelBasket extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bk_id = Utils.getIntParameter(request, "bk_id");
		
		int result = PrdDb.delBasket(bk_id);
		
		Gson gson = new Gson();

		String json = gson.toJson(result);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(json);
	}

}
