package com.suchi.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value="/com/suchi")
public class FileController {

	@RequestMapping(value="/uploadSingleFile",method=RequestMethod.POST)
	String uploadSingleFile(@RequestParam("name") String name, @RequestParam("file") MultipartFile file){
		
		try {
			byte[] content = file.getBytes();
			File dir = new File("D:\\targetFiles");
			if(!dir.exists()){
				dir.mkdir();
			}
			
			String destFileName = dir.getAbsolutePath()+File.separator+file.getName();
			File serverFile = new File(dir.getAbsolutePath()+ File.separator + name);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(content);
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Uploading file controller");
		return "File uploaded successfully!";
	}
	
	@RequestMapping(value="/download/{fileName}")
	public void download(HttpServletRequest request,HttpServletResponse response,@PathVariable("fileName") String fileName){
		Path file = Paths.get("D:\\study\\"+fileName+".pdf");
		if(Files.exists(file)){
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition","attachment; filename="+fileName);
			try {
				Files.copy(file,response.getOutputStream());
				response.getOutputStream().flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}						
		}
	}
}
