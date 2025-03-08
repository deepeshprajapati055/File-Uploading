package com.fileupload.helper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.InputStream;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {

	public final String UPLOAD_DIR = "C:\\Users\\JyotiRahul\\Documents\\workspace-spring-tool-suite-4-4.27.0.RELEASE\\FileUploading\\src\\main\\resources\\static\\images";
	
	public boolean uploadFile(MultipartFile multipartFile) {
		boolean f = false;
		
		try {
//			//read the data
//			InputStream is = multipartFile.getInputStream();
//			byte data[] = new byte[is.available()];
//			is.read(data);
//			
//			//Write the data
//			FileOutputStream fos = new FileOutputStream(UPLOAD_DIR+File.separator+multipartFile.getOriginalFilename());
//			fos.write(data);
//			fos.flush();
//			fos.close();
			
			//one-line reading the data.
			Files.copy(multipartFile.getInputStream(), Paths.get(UPLOAD_DIR+File.separator+multipartFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			
			f = true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
}
