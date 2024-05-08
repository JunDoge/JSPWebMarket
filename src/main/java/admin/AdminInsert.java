package admin;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Collection;

import config.AwsS3;
import config.Utils;
import config.ViewResolver;
import db.AdminPrdDb;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import prdBean.ImgBean;
import prdBean.PrdBean;

public class AdminInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		ViewResolver.commonforward("admin/fileupload", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {		
		//request객체에 담기
		PrdBean prd = new PrdBean();		
		ImgBean img = new ImgBean();
		
		prd.setPrice(Utils.getIntParameter(request, "price"));
		prd.setTitle(request.getParameter("title"));
		prd.setCat_id(Utils.getIntParameter(request, "cat_id"));
		
		System.out.println("size : "  + request.getParameter("Ssize") + request.getParameter("Msize") + request.getParameter("Lsize"));
		
		String count = "S" + request.getParameter("Ssize") + 
				"M" + request.getParameter("Msize") + "L" + request.getParameter("Lsize") ;
		prd.setCount(count);
		
		
		
		//dbInsert
		AdminPrdDb.prdIns(prd);
		
		//insert해서 return 한 prd_id 값 넣어주기
		img.setPrd_id(prd.getPrd_id());
		
		//File Aws에 저장하기
		AwsS3 s3 = new AwsS3();
		Collection<Part> parts = request.getParts();
		for (Part part : parts) {
			if (part.getContentType() != null) {
				
				//확장자 분리
				String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
				int index = fileName.indexOf(".");
				String type = fileName.substring(index);
				
				//파일의 정보 들고오기
				InputStream fileContent = part.getInputStream();
				
				//어느 AWS 버킷에 폴더에 저장할건지 정하고 업로드!
				String fileNm = "";
				if(part.getName().equals("mainFile")) {
				   fileNm = s3.serverUpload(fileContent,type, "main/");
				}else if(part.getName().equals("desFile")) {
				   fileNm = s3.serverUpload(fileContent,type, "des/");
				}
	
				//업로드가 되면 파일이름이랑 prd_id랑 insert!
				img.setImg_id(fileNm);
				AdminPrdDb.insImg(img);
				
				
			}
		}
			
		response.sendRedirect("/admin/manager");
	}

}
