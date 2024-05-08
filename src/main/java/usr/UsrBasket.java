package usr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import config.Const;
import config.ViewResolver;
import db.PrdDb;
import db.UsrDb;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import prdBean.ImgBean;
import prdBean.PrdBean;
import usrBean.BasketBean;
import usrBean.UsrBean;

/**
 * Servlet implementation class UsrBasket
 */
public class UsrBasket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UsrBean usr = (UsrBean)session.getAttribute(Const.LOGIN_USER);
		List<BasketBean> bks =  UsrDb.selBasket(usr);
		
		List<ImgBean> imgs = new ArrayList<>();
		for(BasketBean bk : bks) {
			ImgBean img = new ImgBean();
			img.setPrd_id(bk.getPrd_id());
			PrdDb.basketImg(img);
			imgs.add(img);
		}
		
		request.setAttribute("lstPrd", bks);
		request.setAttribute("lstImg", imgs);
		
		ViewResolver.mainforward("usr/usrBasket", request, response);
	}

}
