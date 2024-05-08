package admin;

import java.io.IOException;
import java.util.List;

import config.Utils;
import config.ViewResolver;
import db.PrdDb;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import prdBean.ImgBean;
import prdBean.PrdBean;


public class AdminPrdDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrdBean prd = new PrdBean();
		prd.setPrd_id(Utils.getIntParameter(request, "prd_id"));
		
		//상품정보 들고오기
		PrdDb.prdDetail(prd);
		
		//상품이미지 정보 들고오기
		List<ImgBean> imgs = PrdDb.imgDetail(prd);

		request.setAttribute("prd", prd);
		request.setAttribute("lstImg", imgs);
		
		ViewResolver.adminforward("/admin/AdminPrdDetail", request, response);
	}

}
