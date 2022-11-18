package com.dcu.wfd.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dcu.wfd.common.service.MainService;
import com.dcu.wfd.common.vo.EplDataStorage;
import com.dcu.wfd.common.vo.NewsVO;


@Controller
public class MainController {

	@Autowired
	private MainService mainService;

	@RequestMapping("/")
	public String root(Model model) throws Exception {

		//  url없이 그냥 들어 왔을때 main페이지로 이동.
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

	// 네이버 최신뉴스 크롤링.
	@RequestMapping("/craw/crawSelect.ajax")
	@ResponseBody
	public ArrayList<HashMap<String, String>> naverSportsNewsLatest()throws Exception {

		// DataStorage VO의 변수에 담긴 네이버 최신뉴스 크롤링데이터 리턴.
		return EplDataStorage.getNaverSportsNewsLatestData();

	}

	// 오늘의 경기 크롤링 url 
	@RequestMapping("/craw/matchCrawling.ajax")
	@ResponseBody
	public ArrayList<HashMap<String, String>> todayMatch()throws Exception {

		// DataStorage VO의 변수에 담긴 오늘경기일정 크롤링데이터 리턴.
		return EplDataStorage.getTodayMatchScheduleData();

	}





}
