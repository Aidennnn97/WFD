package com.dcu.wfd.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class MainController {
	
	@RequestMapping("/main")
	public String mainJsp(Model model) {
		
		return "/main";
	}
	
	@RequestMapping("/epl")
	public String eplJsp(Model model) {
		
		return "/epl";
	}
	
	
}
