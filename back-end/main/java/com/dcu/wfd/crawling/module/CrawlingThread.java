package com.dcu.wfd.crawling.module;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dcu.wfd.common.vo.DataStorage;
import com.dcu.wfd.util.HttpUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CrawlingThread extends Thread {
	
	private final Object[][] STANDARD_TIMES = {{"naverSportsNews", 10}, {"todayMatch", 5}};
	
	@Override
    public void run() {
        while (true) {
        	try {
        		
        		
        		Crawling crawling = new Crawling();
        		for (Object [] STANDARD_TIME : STANDARD_TIMES) {
        			String crawlingSiteName = (String) STANDARD_TIME[0];
        			int crawlingStandardSeconds = (int) STANDARD_TIME[1];
        			
        			if (crawlingSiteName.equals("naverSportsNews")) {
        				if (DataStorage.getNaverSportsNewsData() == null) {
        					ArrayList<HashMap<String, String>> data = crawling.naverSportsNews();
        					if (data != null && data.size() > 0) {
        						DataStorage.setNaverSportsNewsData(data);
        						DataStorage.setNaverSportsNewsCrawlingTime(new Date());
        					}
        				} else {
        					Date oldNaverSportsNewsCrawlingTime = DataStorage.getNaverSportsNewsCrawlingTime();
        					Date currentNaverSportsNewsCrawlingTime = new Date();
        					long difSeconds = (currentNaverSportsNewsCrawlingTime.getTime() - oldNaverSportsNewsCrawlingTime.getTime()) / 1000; // 초
        					
        					System.out.println("difSeconds : " + difSeconds);
        					if (difSeconds > crawlingStandardSeconds) {
        						ArrayList<HashMap<String, String>> data = crawling.naverSportsNews();
        						if (data != null && data.size() > 0) {
        							DataStorage.setNaverSportsNewsData(data);
        							DataStorage.setNaverSportsNewsCrawlingTime(new Date());
        						}
        					}
        				}
        			} else if (crawlingSiteName.equals("todayMatch")) {
        				if (DataStorage.getTodayMatchData() == null) {
        					ArrayList<HashMap<String, String>> data = crawling.todayMatch();
        					if (data != null && data.size() > 0) {
        						DataStorage.setTodayMatchData(data);
        						DataStorage.setTodayMatchCrawlingTime(new Date());
        					}
        				} else {
        					Date oldTodayMatchCrawlingTime = DataStorage.getTodayMatchCrawlingTime();
        					Date currentTodayMatchCrawlingTime = new Date();
        					long difSeconds = (oldTodayMatchCrawlingTime.getTime() - currentTodayMatchCrawlingTime.getTime()) / 1000; // 초
        					
        					if (difSeconds > crawlingStandardSeconds) {
        						ArrayList<HashMap<String, String>> data = crawling.todayMatch();
        						if (data != null && data.size() > 0) {
        							DataStorage.setTodayMatchData(data);
        							DataStorage.setTodayMatchCrawlingTime(new Date());
        						}
        					}
        				}
        			}
        		}
        		
        		
        		
				Thread.sleep(1000 * 5);// 1000 * 60 * 10 10분
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
    }
}
