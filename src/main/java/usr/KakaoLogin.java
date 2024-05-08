package usr;

import java.io.IOException;

import config.Const;
import config.ViewResolver;
import db.UsrDb;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import usrBean.UsrBean;
import usrService.KakaoService;

/**
 * Servlet implementation class KakaoLogin
 */

public class KakaoLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String token = KakaoService.getUserToken(request.getParameter("code"));
		UsrBean user = KakaoService.getUserInfo(token);
		
		
		int result = UsrDb.emailValidate(user);
		System.out.println(result);
		if(result == 1) {
			HttpSession session = request.getSession();
			session.setAttribute(Const.LOGIN_USER, user);
			session.setAttribute(Const.LOGIN_TOKEN, token);
			response.sendRedirect("/prd/main");
		}else {
			request.setAttribute("usr", user);
			ViewResolver.commonforward("usr/usrJoin",request, response);
		}
		

		
	}

}
