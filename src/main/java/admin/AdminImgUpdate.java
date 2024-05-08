package admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;

import com.google.gson.Gson;

import config.AwsS3;
import db.AdminPrdDb;


public class AdminImgUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
System.out.println("dddd");
		
		// File Aws에 저장하기
		AwsS3 s3 = new AwsS3();
		String oldFileNm = request.getParameter("img_id");
		String folderNm = request.getParameter("folderNm");
		s3.severDelete(oldFileNm);
		
		Part part = request.getPart("file");
		if (part.getContentType() != null) {

			// 확장자 분리
			String newFileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
			int index = newFileName.indexOf(".");
			String type = newFileName.substring(index);

			// 파일의 정보 들고오기
			InputStream fileContent = part.getInputStream();
			
			newFileName = s3.serverUpload(fileContent,type, folderNm);
			
			int result = AdminPrdDb.updDesImage(oldFileNm , newFileName);
			
			System.out.println(newFileName);
			
			Gson gson = new Gson();

			String json = gson.toJson(result);

			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.print(json);

		}
	}

}
