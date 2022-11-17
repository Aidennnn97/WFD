package com.dcu.wfd.crawling.module.main;

import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.dcu.wfd.util.HttpUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NaverSportsNewsPopularCrawling {
	
	public ArrayList<HashMap<String, String>> naverSportsNewsPopularCrawling() {
		
		// ArrayList 안에 HashMap이 있는 crawlingDataList생성. 
		ArrayList<HashMap<String, String>> crawlingDataList = new ArrayList<>();

		HttpUtil httpUtil = new HttpUtil();
		
		try {
			
			// HTTpUtil.java 의 httpRequest메서드를 이용하여 해당 url 주소의 데이터들을 한줄 씩 읽어 온다  
			String responseBody = httpUtil.httpRequest("https://sports.news.naver.com/wfootball/news/list?isphoto=N&type=popular", null);
			
			// json 파싱기 생성  jackson ObjectMapper: JSON to Java Object, Java Object to JSON
			ObjectMapper mapper = new ObjectMapper();
			
			// jsonData에 url로 부터 받은 responseBody 값을 json파싱기를 이용해 json타입으로 저장. 
			HashMap<String, Object> jsonData = mapper.readValue(responseBody, new TypeReference<HashMap<String,Object>>() {});
			
			// list에 해당하는 데이터를 newsList에 저장. 
			ArrayList<HashMap<String, Object>> newsList = (ArrayList<HashMap<String, Object>>)jsonData.get("list");
			
			for(int i = 0; i < newsList.size(); i++) {
				
				// newsList 안의 oid, aid, title 값을 가져옴 
				String oid = (String)newsList.get(i).get("oid");
				String aid = (String)newsList.get(i).get("aid");
				String newstitle = (String)newsList.get(i).get("title");
				String newsContent = (String)newsList.get(i).get("subContent");
				String newsViews = String.valueOf(newsList.get(i).get("totalCount"));
				String newsUrl = "https://sports.news.naver.com/news?oid="+oid+"&aid="+aid;
				
				// map 에 전달하면 쿼리스트링으로 바꿔준다 
				HashMap<String, Object> requestParams = new HashMap<>();
				requestParams.put("oid", oid);
				requestParams.put("aid", aid);
				
				// 기사 상세 페이지, reqeustParams에 저장된 oid, aid를 가져와 url주소 뒤에 ?oid=1234&aid=5678 이런식으로 만들어줌  
				String newResponseBody = httpUtil.httpRequest("https://sports.news.naver.com/news", requestParams);
				
				// jsoup 사용 
				Document doc = Jsoup.parse(newResponseBody); // 도큐먼트 오브젝트파일로 바꿈 
//				
//				Element newTitleElement = doc.selectFirst("#content > div > div.content > div > div.news_headline > h4");
//	            Element newContentElement = doc.selectFirst("#newsEndContents");
//	            Element newInsertDateTimeElement = doc.selectFirst("#content > div > div.content > div > div.news_headline > div > span:nth-child(1)");
	            Element newImageUrlElement = doc.selectFirst("span.end_photo_org > img");
//	            
//	            String newsTitle = newTitleElement.text();
//	            String newsContent = newContentElement.text();
//	            String newsInsertDateTime = newInsertDateTimeElement.text();
	            String newsImageUrl = newImageUrlElement.attr("src"); //이미지는 태그안의 속성을 가져와야한다
	            
	            HashMap<String, String> crawlingData = new HashMap<>();
	            
	            crawlingData.put("news_title", newstitle);
	            crawlingData.put("news_content", newsContent);
	            crawlingData.put("news_main_image", newsImageUrl);
	            crawlingData.put("news_url", newsUrl);
	            crawlingData.put("news_views", newsViews);
	            
	            crawlingDataList.add(crawlingData);
	            
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return crawlingDataList;
	}
}
