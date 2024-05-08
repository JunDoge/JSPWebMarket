package prd;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import prdBean.CmtBean;
import prdBean.ImgBean;
import prdBean.PrdBean;

import java.io.IOException;
import java.util.List;

import config.Utils;
import config.ViewResolver;
import db.PrdDb;

/**
 * Servlet implementation class PrdDetail
 */
public class PrdDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrdBean prd = new PrdBean();
		prd.setPrd_id(Utils.getIntParameter(request, "prd_id"));
		
		//상품정보 들고오기
		PrdDb.prdDetail(prd);
		
		//상품이미지 정보 들고오기
		List<ImgBean> imgs = PrdDb.imgDetail(prd);
		
		//상품 문의글 들고오기
		List<CmtBean> cmts = PrdDb.cmtSel(prd);

		request.setAttribute("prd", prd);
		request.setAttribute("lstImg", imgs);
		request.setAttribute("cmts", cmts);
		ViewResolver.mainforward("prd/prdDetail", request, response);
	}

}
