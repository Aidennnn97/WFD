package com.dcu.wfd.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dcu.wfd.common.service.MainService;



@Controller
public class MainController {
	
	private MainService mainService;
	
	@RequestMapping("/main")
	public String mainJsp(HttpServletRequest request, Model model) {
		// 뉴스정보에대한 변수객체생성 
		
		return "/main";
	}
	
	@RequestMapping("/epl")
	public String eplJsp(Model model) {
		
		return "/epl";
	}
	
	// 네이버 인기순 뉴스 크롤링하여 데이터베이스에 저장. 
	@RequestMapping("/naverSportsNewsPopularInsert") //RequestMappingUrl요청이 오면 실행할 함수
	public ModelAndView naverSportsNewsPopularInsert() { // 데이터와 뷰를 동시에 설정 가능 
		
		ModelAndView mav = new ModelAndView("jsonView"); 
		
		mainService.naverSportsNewsPopularInsert();
		
		return mav;
	}
	
}
