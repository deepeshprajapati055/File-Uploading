package com.fileupload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fileupload.helper.FileUploadHelper;

@RestController
public class FileController {
	
	@Autowired
	private FileUploadHelper fileUploadHelper;
	
	@PostMapping("/file-uploading")
	public ResponseEntity<?> fileUploading(@RequestParam("file") MultipartFile file){
		
		/*
		 * System.out.println(file.getOriginalFilename());
		 * System.out.println(file.getName());
		 * System.out.println(file.getContentType());
		 * System.out.println(file.getSize());
		 */
		
		try {
			//Validating 
			if(file.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sorry, File not found :(");
			}
			
			if(!file.getContentType().equals("image/png")) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only PNG content type are allowed");
			}
			
			//file-Upload code.
			boolean f = fileUploadHelper.uploadFile(file);
			if(f) {
				//return ResponseEntity.status(HttpStatus.OK).body("File is successfully uploaded");
				
				return ResponseEntity.status(HttpStatus.OK).body(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(file.getOriginalFilename()).toUriString());
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong while uploading the file.");
	}
}
