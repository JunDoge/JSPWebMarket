package config;

import java.io.InputStream;
import java.net.URL;
import java.util.UUID;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class AwsS3 {

	   // Amazon-s3-sdk
	   private AmazonS3 s3Client;
	   final private String accessKey = Const.AWS_ACCESEKEY; // 액세스키
	   final private String secretkey = Const.AWS_SECRITKEY; // 스크릿 엑세스 키

	   private Regions clientRegion = Regions.AP_NORTHEAST_2; // 한국
	   private String bucket = Const.AWS_BUKET; // 버킷 명

	   public AwsS3() {
	      createS3Client();
	   }


	   // aws S3 client 생성
	   private void createS3Client() {
		   s3Client = AmazonS3ClientBuilder.standard()
				    .withRegion(clientRegion)
				    .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretkey)))
				    .build();
	   }


	   public String serverUpload(InputStream fileContent, String type, String folderNm){
		   String fileName = folderNm +UUID.randomUUID() + type;
		   ObjectMetadata metadata = new ObjectMetadata();
		   s3Client.putObject(new PutObjectRequest(bucket, fileName, fileContent, metadata));
		   return fileName;
	   }
	   
	   

	   public void severDelete(String fileNm) {
		   s3Client.deleteObject(this.bucket, fileNm);
	   }




}