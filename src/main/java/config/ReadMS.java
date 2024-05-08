package config;

import db.AdminPrdDb;
import java.io.*;

public class ReadMS {
	   public static void main(String[] args) {
	       BufferedReader reader;
	           try {
	              for(int i = 1; i < 52; i++) {
	                 if(i == 7) {
	                    continue;
	                 }
	                  reader = new BufferedReader(new FileReader(
	                           "프로젝트 경로"));
	                   String line = reader.readLine();
	                   while (line != null) {
	                       System.out.println(line);
	                       AdminPrdDb.prdIns(line);
	                       line = reader.readLine();
	                   }
	                   
	                  reader.close();
	              }
	              

	           } catch (IOException e) {
	               e.printStackTrace();
	           }
	   }
}
