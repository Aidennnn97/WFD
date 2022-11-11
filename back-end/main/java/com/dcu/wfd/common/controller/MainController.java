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
import com.dcu.wfd.common.vo.NewsVO;
import com.dcu.wfd.util.HttpUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;



@Controller
public class MainController {

	@Autowired
	private MainService mainService;

	@RequestMapping("/main")
	public String mainJsp(Model model) {

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
		boolean result = false;
		ArrayList<HashMap<String, String>> crawlingDataList = new ArrayList<>();

		HttpUtil httpUtil = new HttpUtil();

		String url = "https://sports.news.naver.com/wfootball/news/list?isphoto=N&type=latest";

		String responseBody = httpUtil.httpRequest(url, null);

		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> jsonData = mapper.readValue(responseBody,new TypeReference<HashMap<String,Object>>() {});

		ArrayList<HashMap<String, Object>> latestNewsList = (ArrayList<HashMap<String, Object>>) jsonData.get("list");

		for(int i=0; i < latestNewsList.size(); i++) {
			HashMap<String, String> crawData = new HashMap<>();

			String tit = (String) latestNewsList.get(i).get("title");
			String img = (String) latestNewsList.get(i).get("thumbnail");
			String oid = (String) latestNewsList.get(i).get("oid");
			String aid = (String) latestNewsList.get(i).get("aid");
			String newsURL = "https://sports.news.naver.com/news?"+"oid="+oid+"&aid="+aid;
			String content = (String) latestNewsList.get(i).get("subContent");
			String officeName = (String) latestNewsList.get(i).get("officeName");
			String date = (String) latestNewsList.get(i).get("datetime");
			crawData.put("tit", tit);
			crawData.put("img", img);
			crawData.put("newsURL", newsURL);
			crawData.put("content", content);
			crawData.put("officeName", officeName);
			crawData.put("date", date);
			crawlingDataList.add(crawData);
			result = true;
		}

		return crawlingDataList;
	}
	
	@RequestMapping("/craw/matchCrawling.ajax")
	   @ResponseBody
	   public ArrayList<HashMap<String, String>> craw_select2(HttpServletRequest req, HttpServletResponse resp)throws Exception {
	      boolean result = false;
	      ArrayList<HashMap<String, String>> matchDataList = new ArrayList<>();
	      Date today = new Date();
	      SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
	      String url = "https://sports.daum.net/prx/hermes/api/game/schedule.json?page=1&leagueCode=epl%2Cprimera%2Cbundesliga%2Cseriea%2Cligue1%2Ceredivisie%2Cuefacl%2Cuefacup%2Cepl_cup%2Cfacup%2Ccopadelrey&fromDate=20221101&toDate=20221130";
	      try {
	         HttpUtil httpUtil = new HttpUtil();
	         
	         String responseBody = httpUtil.httpRequest(url, null);
	         ObjectMapper mapper = new ObjectMapper();
	         HashMap<String, Object> jsonData =  mapper.readValue(responseBody, new TypeReference<HashMap<String, Object> >() {});
	         HashMap<String, Object> matchList = (HashMap<String, Object>) jsonData.get("schedule");
	         ArrayList<HashMap<String,Object>> matchList2 = (ArrayList<HashMap<String, Object>>) matchList.get(date.format(today));
	         if(matchList2 != null) {
		         for(int i = 0; i < matchList2.size(); i++) {
			            String leagueName = (String) matchList2.get(i).get("leagueName");
			            String startDate = (String) matchList2.get(i).get("startDate");
			            String startTime = (String) matchList2.get(i).get("startTime");
			            String awayTN = (String) matchList2.get(i).get("awayTeamName");
			            String homeTN = (String) matchList2.get(i).get("homeTeamName");
			            String homeResult = (String) matchList2.get(i).get("homeResult");
			            String awayResult = (String) matchList2.get(i).get("awayResult");
			            String awayTeamImageUrl = (String) matchList2.get(i).get("awayTeamImageUrl");
			            String homeTeamImageUrl = (String) matchList2.get(i).get("homeTeamImageUrl");
			            
			            
			            HashMap<String, String> matchData = new HashMap<>(); 
			            
			            if(leagueName.equals("프리미어리그") || leagueName.equals("라리가") || leagueName.equals("세리에A") || leagueName.equals("분데스리가")) {
			               matchData.put("leagueName", leagueName);
			               matchData.put("startDate", startDate);
			               matchData.put("homeTN", homeTN);
			               matchData.put("homeResult", homeResult);
			               matchData.put("homeTeamImageUrl", homeTeamImageUrl);
			               matchData.put("awayTN", awayTN);
			               matchData.put("awayResult", awayResult);
			               matchData.put("awayTeamImageUrl", awayTeamImageUrl);
			               matchData.put("startTime", startTime);
			               matchDataList.add(matchData);
			               
			            }

	         }
	         } else {
	        	 matchDataList.add(null);
	         }
	      } catch (Exception e) {
	         // TODO: handle exception
	         e.printStackTrace();
	      }
	      return matchDataList;
	      }

	

	

}
