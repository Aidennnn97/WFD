package com.dcu.wfd.common.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dcu.wfd.common.service.MainService;
import com.dcu.wfd.common.vo.DataStorage;
import com.dcu.wfd.common.vo.NewsVO;
import com.dcu.wfd.crawling.module.Crawling;
import com.dcu.wfd.util.HttpUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;



@Controller
public class MainController {

	@Autowired
	private MainService mainService;

	@RequestMapping("/")
	public String root (Model model) throws Exception {
		return "redirect:/main";
	}
	
	@RequestMapping("/main")
	public String mainJsp(Model model) throws Exception {

		// 뉴스정보에대한 변수객체생성
		List<NewsVO> newsList = mainService.newsSelectList();

		model.addAttribute("newsList", newsList);
		

		return "/main";
	}
	
	// 네이버 인기순 뉴스 크롤링하여 데이터베이스에 저장. 
		@RequestMapping("/naverSportsNewsPopularInsert") //RequestMappingUrl요청이 오면 실행할 함수
		public ModelAndView naverSportsNewsPopularInsert() { // 데이터와 뷰를 동시에 설정 가능 

			ModelAndView mav = new ModelAndView("jsonView"); 

			mainService.naverSportsNewsPopularInsert();

			return mav;
		}
		

	@RequestMapping("/craw/crawSelect.ajax")
	@ResponseBody
	public ArrayList<HashMap<String, String>> craw_select(HttpServletRequest req, HttpServletResponse resp)throws Exception {
		return DataStorage.getNaverSportsNewsData();
	}
	
	@RequestMapping("/craw/matchCrawling.ajax")
   @ResponseBody
   public ArrayList<HashMap<String, String>> craw_select2(HttpServletRequest req, HttpServletResponse resp)throws Exception {
      return DataStorage.getTodayMatchData();
  }

	

	

}
