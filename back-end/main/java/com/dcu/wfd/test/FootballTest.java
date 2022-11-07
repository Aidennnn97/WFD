package com.dcu.wfd.test;

import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dcu.wfd.util.HttpUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FootballTest {
	// selenium은 웹애플리케이션 testing 자동화 도구 
	// 개발자도구 네트워크안에 다 있음 
	// jsoup 은 Html Document를 얻어와 Document의 Element들을 파싱해주는 라이브러리. 
	// jsoup의 주요 요소 5가지 
	// 1. Document : 얻어온 결과 HTML전체 문서 
	// 2. Element : Document의 HTML 요소 
	// 3. Elements : Element가 모인 자료형으로 for나 while 등 반복문 사용이 가능
	// 4. Connection : Jsoup의 connect 혹은 설정 메서드들을 이용해 만들어지는 객체, 연결을 하기 위한 정보를 담고 있음 
	// 5. Response : Jsoup이 URL에 접속해 얻어온 결과, Document 와 다르게 status 코드, status 메세지나 charset과 같은 헤더 메세지와 쿠키 등을 가지고 있음

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Connection connection= Jsoup.connect("https://sports.news.naver.com/wfootball/news/list?isphoto=N&type=latest");
		try {
//			Document doc = connection.get();
//			
//			Elements els = doc.select("#_newsList > ul > li");
//			
//			for(int i = 1; i <= els.size(); i++) {
//				Element elsItem = doc.selectFirst("#_newsList > ul > li:nth-child(" + i + ") > div > a > span");
//				if(elsItem==null) {
//					System.out.println("Null -> Element not Found");
//				}else {
//					System.out.println(elsItem.text());
//				}
//			}
			
			HttpUtil httpUtil = new HttpUtil();
			String responseBody = httpUtil.httpRequest("https://sports.news.naver.com/wfootball/news/list?isphoto=N&type=popular", null);
			
			// json 파싱기 
			ObjectMapper mapper = new ObjectMapper();
			HashMap<String, Object> jsonData = mapper.readValue(responseBody, new TypeReference<HashMap<String,Object>>() {});
			
			ArrayList<HashMap<String, Object>> newsList = (ArrayList<HashMap<String, Object>>)jsonData.get("list");
			
			for(int i = 0; i < newsList.size(); i++) {
				String oid = (String)newsList.get(i).get("oid");
				String aid = (String)newsList.get(i).get("aid");
				String newstitle = (String)newsList.get(i).get("title");
				String newsContent = (String)newsList.get(i).get("subContent");
				String newsThumbnail = (String)newsList.get(i).get("thumbnail");
				String newsViews = String.valueOf(newsList.get(i).get("totalCount"));
				String newsUrl = "https://sports.news.naver.com/news?oid="+oid+"&aid="+aid;
				
				
				// map 에 전달하면 쿼리스트링으로 바꿔준다 
//				HashMap<String, Object> requestParams = new HashMap<>();
//				requestParams.put("oid", oid);
//				requestParams.put("aid", aid);
				
				// 기사 상세 페이지 
//				String newResponseBody = httpUtil.httpRequest("https://sports.news.naver.com/news", requestParams);
				
				// jsoup 사용 
//				Document doc = Jsoup.parse(newResponseBody); // 도큐먼트 오브젝트파일로 바꿈 
				
//				Element newsTitleElement = doc.selectFirst("#content > div > div.content > div > div.news_headline > h4");
//	            Element newsContentElement = doc.selectFirst("#newsEndContents");
//	            Element newsInsertDateTimeElement = doc.selectFirst("#content > div > div.content > div > div.news_headline > div > span:nth-child(1)");
//	            Element newsInsertReporterNameElement = doc.selectFirst("#newsEndContents > p.byline");
//	            Element newsImageUrlElement = doc.selectFirst("span.end_photo_org > img");
	            
//	            String newsTitle = newsTitleElement.text();
//	            String newsContent = newsContentElement.text();
//	            String newsInsertDateTime = newsInsertDateTimeElement.text();
//	            String newsInsertReporterName = newsInsertReporterNameElement.text();
//	            String newsImageUrl = newsImageUrlElement.attr("src"); //이미지는 태그안의 속성을 가져와야한다 
				System.out.println("newsTitle: " + newstitle);
	            System.out.println("newsContent: " + newsContent);
	            System.out.println("newsImg: " + newsThumbnail);
	            System.out.println("newsUrl: " + newsUrl);
	            System.out.println("newsViews: " + newsViews);
	            System.out.println("oid: " + oid);
	            System.out.println("aid: " + aid);
				System.out.println();
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
