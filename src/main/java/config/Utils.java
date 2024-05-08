package config;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import admin.AdminBean;
import db.AdminValidateDb;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


public class Utils {
	
	public static int getIntParameter(HttpServletRequest request, String keyNm) {
		return parseStrToInt(request.getParameter(keyNm));
	}
	
	
	public static int parseStrToInt(String str) {
		int num = 0;
		try {
			num = Integer.parseInt(str);
		} catch (Exception e) {
			
		}
		
		return num;
	}
	
	public static boolean isLogout(HttpServletRequest request){
		HttpSession hs = request.getSession();
		if(null == hs.getAttribute(Const.LOGIN_USER)) {
			return true;
		}
		return false;
	}
	
	
	public static String encryptString(String str) {
		String sha = "";

	       try{
	          MessageDigest sh = MessageDigest.getInstance("SHA-256");
	          sh.update(str.getBytes());
	          byte byteData[] = sh.digest();
	          
	          StringBuffer sb = new StringBuffer();
	          for(int i = 0 ; i < byteData.length ; i++){
	              sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
	          }

	          sha = sb.toString();

	      }catch(NoSuchAlgorithmException e){
	          //e.printStackTrace();
	          System.out.println("Encrypt Error - NoSuchAlgorithmException");
	          sha = null;
	      }

	      return sha;
	}
	
	
	public static boolean isAdmin(HttpServletRequest request){
		String id = request.getParameter("id");
		
		if(id == null) {
			id = (String)request.getSession().getAttribute("id");
		}
		
		String pw = request.getParameter("pw");
		pw = Utils.encryptString(pw);
		
		AdminBean admin = new AdminBean();
		admin.setPwd(pw);
		admin.setId(id);
		
		
		int result = AdminValidateDb.LoginCheck(admin);
		
		if(result == 1) {
			return true;
		}
		
		return false;
		
	}
	
	

}
