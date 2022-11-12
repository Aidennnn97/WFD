package com.dcu.wfd.crawling.module;

import java.util.ArrayList;
import java.util.HashMap;
import com.dcu.wfd.util.HttpUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NaverSportsNewsLatestCrawling {
	
	// 네이버 스포츠 최신 뉴스기사 크롤링 모듈.
	public ArrayList<HashMap<String, String>> naverSportsNewsLatestCrawling()throws Exception {
		
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
		}

		return crawlingDataList;
		
	}
	
}
