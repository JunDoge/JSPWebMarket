package prd;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import prdBean.CmtBean;
import usrBean.UsrBean;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import config.Const;
import config.Utils;
import db.PrdDb;

public class InsCmt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int prd_id = Utils.getIntParameter(request, "prd_id");
		UsrBean usr = (UsrBean)request.getSession().getAttribute(Const.LOGIN_USER);
		String cmt_des = request.getParameter("cmt_des");
		CmtBean cmt = new CmtBean();
		
		cmt.setPrd_id(prd_id);
		cmt.setUsr_id(usr.getUsr_id());
		cmt.setC_des(cmt_des);
		int result = PrdDb.insCmt(cmt);
		
		
		Gson gson = new Gson();

		String json = gson.toJson(result);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(json);
		
	}

}
