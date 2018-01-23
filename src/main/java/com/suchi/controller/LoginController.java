package com.suchi.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.suchi.helper.Helper;

@Controller
@RequestMapping(value="/com/suchi")
public class LoginController {

	@Autowired
	Helper helper; 
	
	@RequestMapping(method=RequestMethod.GET,value="/login")
	public void login() throws InterruptedException, ExecutionException{
		//helper.asyncCall();
		//Future<String> asyncCallReturn = helper.asyncCallWithReturnType();
		System.out.println("inside controller " + Thread.currentThread().getName());
		//System.out.println("inside controller: return of async call: "+asyncCallReturn.get());
		
		/*CompletableFuture.supplyAsync(this::sendMsg);
		CompletableFuture<String> receiver = CompletableFuture.supplyAsync(this::sendMsg2);
		
		receiver.thenApplyAsync(this::capitaliseMsg).thenAcceptAsync(this::receiveMsg);*/
		helper.loadProperties();
		System.out.println("End of Controller");
	}
		
	public String sendMsg(){
		System.out.println("Sending message "+Thread.currentThread().getName());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Message";
	}
	
	public String sendMsg2(){
		System.out.println("Sending message from sendMsg2 "+Thread.currentThread().getName());
		return "Message";
	}
	
	public String capitaliseMsg(String Msg){
		System.out.println("Capitalising......"+Thread.currentThread().getName());
		return Msg.toUpperCase();
	}
	public void receiveMsg(String msg){
		System.out.println("receiving message "+msg+" "+Thread.currentThread().getName());		
	}
}
