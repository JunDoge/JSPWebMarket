package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import db.AdminPrdDb;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import prdBean.Cat;

public class AdminSelDes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cat_nm= request.getParameter("cat_nm");
		List<Cat> des = AdminPrdDb.selDes(cat_nm);
		
		
		Gson gson = new Gson();

		String json = gson.toJson(des);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(json);
		
	}

}
