package prd;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import prdBean.CmtBean;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import config.Utils;
import db.PrdDb;

/**
 * Servlet implementation class UpdCmt
 */
public class UpdCmt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cmt_id = Utils.getIntParameter(request, "cmt_id");
		String cmt_des = request.getParameter("cmt_des");
		
		CmtBean cmt = new CmtBean();
		cmt.setC_des(cmt_des);
		cmt.setCmt_id(cmt_id);
		
		int result = PrdDb.updCmt(cmt);
		
		Gson gson = new Gson();

		String json = gson.toJson(result);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(json);
	}

}
