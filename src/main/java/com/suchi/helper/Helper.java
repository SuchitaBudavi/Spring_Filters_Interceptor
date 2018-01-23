package com.suchi.helper;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service
public class Helper {

	@Autowired
	MessageSource messageSource;
	
	Map<String,String> properties = new HashMap<String,String>();

	public void setMessageSource(MessageSource messageSource) 
	 {
	  this.messageSource = messageSource;
	 }

	
	@Async("threadPoolTaskExecutor")
	public void asyncCall(){
		System.out.println("in ThreadHelper.asyncCall - START "+Thread.currentThread().getName());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("in ThreadHelper.asyncCall - END "+Thread.currentThread().getName());
	}
	
	@Async
	public Future<String> asyncCallWithReturnType(){		
		System.out.println("in ThreadHelper.asyncCallWithReturnType - START "+Thread.currentThread().getName());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		System.out.println("in ThreadHelper.asyncCallWithreturnType - START "+Thread.currentThread().getName());
		return new AsyncResult<String>("Async");
	}
	
	public void loadProperties(){
		System.out.println("ENV: "+messageSource.getMessage("environment",null,null));
		System.out.println("firstName: "+messageSource.getMessage("firstName",null,null));
		
		Properties p = new Properties();
		try {
			p.load(new FileReader("D:/study/test/Spring_Filters_Interceptor/src/main/webapp/english.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("REading using Properties, lastName: "+p.getProperty("lastName"));
		ReloadableResourceBundleMessageSource c;
	}
}
