package com.dcu.wfd.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.dcu.wfd.common.service.CommonService;
import com.dcu.wfd.common.vo.DataStorage;
import com.dcu.wfd.common.vo.Test;

// Model: Service, DAO, DTO(VO)
// View: 사용자가 볼 수 있음 
// Controller: View와 Model을 이어줌 

// client가 요청을 하면, @Controller에 진입한다. 컨트롤러는 요청에 대한 작업을 수행하고,뷰쪽으로 데이터를 전달한다.

@Controller  // 1. 컨트롤러 지정 
public class CommonController {
	
	@Autowired
	private CommonService commonService;
	
	
	@RequestMapping("/test/jsp") // 2. 뷰의 요청 경로 지정 
	public String testJsp(Model model) {
		
		// 3. 로직 수행 및 객체이용해서, view로 Data 전달 
		List<Test> testList =  commonService.testSelectList();
		model.addAttribute("testList", testList); // model객체 이용해서 view로 data 전달 
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			
			String jsonData = mapper.writeValueAsString(testList); //Java Object to JSON
			System.out.println(jsonData);
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return "/test"; // 4. 뷰 파일 리턴 
	}
	
	@RequestMapping("/test/jsp2")
	public ModelAndView testJsp2() {
		
		List<Test> testList =  commonService.testSelectList();
		ModelAndView mav = new ModelAndView("/test");
		//mav.setViewName("/test"); //뷰의 이름 
		mav.addObject("testList", testList); // 뷰로 보낼 데이터 값 
		
		return mav; // modelandview 객체 반환 
	}
	
	@RequestMapping("/test/json")
	public ModelAndView testJson() {
		
		List<Test> testList =  commonService.testSelectList();
		ModelAndView mav = new ModelAndView("jsonView");
		//mav.setViewName("/test");
		mav.addObject("testList", testList);
		
		return mav;
	}
	
	@RequestMapping("/test/naverSportsNewsInsert") //RequestMappingUrl요청이 오면 실행할 함수
	public ModelAndView naverSportsNewsInsert() { // 데이터와 뷰를 동시에 설정 가능 
		
		ModelAndView mav = new ModelAndView("jsonView"); 
		
		commonService.naverSportsNewsInsert();
		
		return mav;
	}
	
	@RequestMapping("/test2")
	   public String test2Jsp(Model model) {
		ArrayList<HashMap<String, String>> allTeamPlayer =  DataStorage.getEplInnerPlayerRankData();
		
		ObjectMapper mapper = new ObjectMapper(); 
		
		try {
			String jsonData =  mapper.writeValueAsString(allTeamPlayer);
			System.out.println(jsonData);
			model.addAttribute("allTeamPlayer", jsonData);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      return "/test2";
	   }
	
	
	
}
