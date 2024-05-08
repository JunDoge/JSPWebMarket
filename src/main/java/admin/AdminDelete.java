package admin;

import java.io.IOException;
import java.util.List;

import config.AwsS3;
import config.Utils;
import db.AdminPrdDb;
import db.PrdDb;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import prdBean.ImgBean;
import prdBean.PrdBean;


public class AdminDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int prd_id = Utils.getIntParameter(request, "prd_id");
		
		PrdBean prd = new PrdBean();
		prd.setPrd_id(prd_id);
		
		List<ImgBean> imgs = PrdDb.imgDetail(prd);
		AwsS3 s3 = new AwsS3();
		for(ImgBean img : imgs) {
			s3.severDelete(img.getImg_id());
		}
		
		
		AdminPrdDb.prdDel(prd);
		
		response.sendRedirect("/admin/manager");
		
	}

}
