package usr;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import usrService.KakaoService;

import java.io.IOException;

import config.Const;


public class KakaoLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String token = (String)session.getAttribute(Const.LOGIN_TOKEN);
		
		KakaoService.kakaoLogout(token);
		session.invalidate();
		
		response.sendRedirect("/prd/main");
	}

}
