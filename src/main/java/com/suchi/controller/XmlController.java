package com.suchi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suchi.helper.Helper;

@Controller
@RequestMapping(value="/com/suchi")
public class XmlController {

	@Autowired
	Helper helper;
	
	@RequestMapping(value="/parse")
	public void parse(){
		//helper.saxParse();
		
		helper.domParser();
		helper.printIndentedXML();
	}
}
