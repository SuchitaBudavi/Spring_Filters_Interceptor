package com.suchi.helper;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class Helper {

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
}
