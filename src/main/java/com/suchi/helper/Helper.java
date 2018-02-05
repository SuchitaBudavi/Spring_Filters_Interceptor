package com.suchi.helper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Future;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

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
	
	public void saxParse(){
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler(){
				
				boolean bName = false;
				boolean bPrice = false;
				boolean bDesc = false;
				boolean bCal = false;
				
				@Override
				public void startElement(String uri, String localName, String qName, Attributes attributes)
			            throws SAXException {
					switch (qName) {
					case "name":
						bName = true;
						break;
					case "price":
						bPrice = true;
						break;
					case "description":
						bDesc = true;
						break;
					case "calories":
						bCal = true;					
						break;
					default:
						break;
					}									
				}
				
				@Override
			    public void endElement(String uri, String localName, String qName) throws SAXException {
					
					switch (qName) {
					case "name":
						System.out.println("Name:");
						bName = false;
						break;
					case "price":
						System.out.println("Price:");
						bPrice = false;
						break;
					case "description":
						System.out.println("Description:");
						bDesc = false;
						break;
					case "calories":
						System.out.println("Calories:");
						bCal = false;					
						break;
					default:
						break;
					}
					
				}
				
				 @Override
				    public void characters(char ch[], int start, int length) throws SAXException {
					 
					 System.out.println(String.valueOf(ch).substring(start, start+length));
					 
				 }
			};
			
			parser.parse(new File("D:\\study\\test\\Spring_Filters_Interceptor\\src\\main\\webapp\\WEB-INF\\config\\sample.xml"), handler);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

	public void domParser(){
		try {
			DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = parser.parse(new File("D:\\study\\test\\Spring_Filters_Interceptor\\src\\main\\webapp\\WEB-INF\\config\\sample.xml"));
			
			System.out.println("Root Element->"+doc.getDocumentElement().getNodeName());
			if(doc.hasChildNodes()){
				printElement(doc.getChildNodes());
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void printElement(NodeList nodeList){
		for(int i =0; i<nodeList.getLength();i++){
			Node node = nodeList.item(i);
			
			if(node.getNodeType() == Node.ELEMENT_NODE){
				System.out.print("<"+node.getNodeName());
			}else if(node.getNodeType() == Node.TEXT_NODE){
				System.out.print(node.getNodeValue());
			}
			
			
			//print all attributes
			if(node.hasAttributes()){
				NamedNodeMap nodeAttributeList = node.getAttributes();
				for(int j=0;j<nodeAttributeList.getLength();j++){
					Node n = nodeAttributeList.item(j);
					System.out.print(" "+n.getNodeName()+" =\""+n.getNodeValue()+"\"");
				}				
			}
						
			
			if(node.hasChildNodes()){
				if(node.getFirstChild().getNodeType() == Node.TEXT_NODE)
					System.out.print(">");
				else
					System.out.println(">");
				
				printElement(node.getChildNodes());
				System.out.println("</"+node.getNodeName()+">");
			}
						
		}			
	}
}
