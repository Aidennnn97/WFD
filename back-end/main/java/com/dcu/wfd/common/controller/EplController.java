package com.dcu.wfd.common.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dcu.wfd.common.vo.DataStorage;
import com.dcu.wfd.util.HttpUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class EplController {

	@RequestMapping("/epl")
	public String eplJsp(Model model) {

		return "/epl";
	}


	//	오늘 경기 일정 크롤링 데이터 
	@RequestMapping("/craw/eplPlan.ajax")
	@ResponseBody
	public ArrayList<HashMap<String, String>> craw_select2()throws Exception {

		// DataStorage VO의 변수에 담긴 프리미어리그 경기일정 크롤링 데이터 리턴.
		return DataStorage.getTodayEplMatchData();

	}



	// 경기박스 클릭시 해당경기 상세 정보  크롤링 데이터 
	@RequestMapping("/craw/clickMatchData.ajax")
	@ResponseBody
	public ArrayList<HashMap<String, String>> craw_select3(String gameId)throws Exception {

		return DataStorage.getEplMatchDetailData();

	}



	// 전체 팀 순위 크롤링 데이터 
	@RequestMapping("/craw/eplTeamRank.ajax")
	@ResponseBody
	public ArrayList<HashMap<String, String>> eplTeamRank()throws Exception {

		return DataStorage.getEplTeamRankData();

	}


	// 전체 선수 순위 크롤링데이터 
	@RequestMapping("/craw/eplPlayerRank.ajax")
	@ResponseBody
	public ArrayList<HashMap<String, String>> eplPlayerRank()throws Exception {

		return DataStorage.getEplPlayerRankData();

	}

	//팀 내 모든선수 크롤링데이터 
	@RequestMapping("/craw/innerPlayerRank.ajax")
	@ResponseBody
	public ArrayList<HashMap<String, String>> innerPlayerRank(String teamId)throws Exception{
		return DataStorage.getEplInnerPlayerRankData();
	}
}
