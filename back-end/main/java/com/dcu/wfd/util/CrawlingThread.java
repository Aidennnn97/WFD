package com.dcu.wfd.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.dcu.wfd.common.vo.DataStorage;
import com.dcu.wfd.crawling.module.EplInnerPlayerCrawling;
import com.dcu.wfd.crawling.module.EplMatchDetailCrawling;
import com.dcu.wfd.crawling.module.EplMatchScheduleCrawling;
import com.dcu.wfd.crawling.module.EplPlayerRankCrawling;
import com.dcu.wfd.crawling.module.EplTeamRankCrawling;
import com.dcu.wfd.crawling.module.NaverSportsNewsLatestCrawling;
import com.dcu.wfd.crawling.module.TodayMatchScheduleCrawling;

public class CrawlingThread extends Thread {
	
	// 크롤링 시간담을 상수 선언.
	private final Object[][] STANDARD_TIMES = {{"naverSportsNewsLatest", 10}, 
			{"todayMatchSchedule", 10}, 
			{"todayEplMatchSchedule", 10},
			{"eplTeamRank", 10},
			{"eplPlayerRank", 10},
			{"eplMatchDetail", 10},
			{"eplInnerPlayer", 10}
	}; 
	
	
	@Override
	public void run() {
		
		while(true) {
			try {
				
				NaverSportsNewsLatestCrawling nsnlc = new NaverSportsNewsLatestCrawling();
				TodayMatchScheduleCrawling tmsc = new TodayMatchScheduleCrawling();
				EplMatchScheduleCrawling emsc = new EplMatchScheduleCrawling();
				EplMatchDetailCrawling emdc = new EplMatchDetailCrawling();
				EplTeamRankCrawling etrc = new EplTeamRankCrawling();
				EplPlayerRankCrawling eprc = new EplPlayerRankCrawling();
				EplInnerPlayerCrawling eipc = new EplInnerPlayerCrawling();
				String gameId = null;
				String teamId = null;
				
				for(Object [] STANDARD_TIME : STANDARD_TIMES) {
					
					String crawlingDataName = (String) STANDARD_TIME[0];
					int crawlingDataTimes = (int) STANDARD_TIME[1];
					
					// naverSportsNewsLatest 크롤링 
					if(crawlingDataName.equals("naverSportsNewsLatest")) {
						
						// DataSotrage VO 의 변수에 데이터가 들어있지 않다면...
						if(DataStorage.getNaverSportsNewsLatestData() == null) {
							// data에 크롤링하여 데이터를 넣고...
							ArrayList<HashMap<String, String>> data = nsnlc.naverSportsNewsLatestCrawling();
							// 만약 크롤링한 데이터가 있으면...
							if(data != null && data.size() > 0) {
								// DataStorage VO 안의 변수에 크롤링한 data와 크롤링한 시간을 담아라...
								DataStorage.setNaverSportsNewsLatestData(data);
								DataStorage.setNaverSportsNewsLatestCrawlingTime(new Date());
							}
						} else { // DataStorage VO 변수에 데이터가 들어 있다면...
								// 크롤링된 시간을 변수에 담고...
								Date oldNaverSportsNewsLatestCrawlingTime = DataStorage.getNaverSportsNewsLatestCrawlingTime();
								// 현재크롤링한시간 변수에 담고...
								Date currentNaverSportsNewsLatestCrawlingTime = new Date();
								
								// 현재 크롤링시간과 최근 크롤링시간의 차이를 계산하여 변수에 저장...
								long difTimes = (currentNaverSportsNewsLatestCrawlingTime.getTime() - oldNaverSportsNewsLatestCrawlingTime.getTime()) / 1000; // 초
								
								System.out.println("difTimes: " +difTimes);
								
								// 크롤링 할 
								if(difTimes > crawlingDataTimes) {
									ArrayList<HashMap<String, String>> data = nsnlc.naverSportsNewsLatestCrawling();
									if(data != null && data.size() > 0) {
										DataStorage.setNaverSportsNewsLatestData(data);
										DataStorage.setNaverSportsNewsLatestCrawlingTime(new Date());
									}
								}
						}
						
					} else if(crawlingDataName.equals("todayMatchSchedule")) {
						// DataSotrage VO 의 변수에 데이터가 들어있지 않다면...
						if(DataStorage.getTodayMatchScheduleData() == null) {
							// data에 크롤링하여 데이터를 넣고...
							ArrayList<HashMap<String, String>> data = tmsc.todayMatchScheduleCrawling();
							// 만약 크롤링한 데이터가 있으면...
							if(data != null && data.size() > 0) {
								// DataStorage VO 안의 변수에 크롤링한 data와 크롤링한 시간을 담아라...
								DataStorage.setTodayMatchScheduleData(data);
								DataStorage.setTodayMatchScheduleCrawlingTime(new Date());
							}
						} else { // DataStorage VO 변수에 데이터가 들어 있다면...
								// 크롤링된 시간을 변수에 담고...
								Date oldTodayMatchScheduleCrawlingTime = DataStorage.getTodayMatchScheduleCrawlingTime();
								// 현재크롤링한시간 변수에 담고...
								Date currentTodayMatchScheduleCrawlingTime = new Date();
								
								// 현재 크롤링시간과 최근 크롤링시간의 차이를 계산하여 변수에 저장...
								long difTimes = (currentTodayMatchScheduleCrawlingTime.getTime() - oldTodayMatchScheduleCrawlingTime.getTime()) / 1000; // 초
								
								System.out.println("difTimes: " +difTimes);
								
								// 크롤링 할 
								if(difTimes > crawlingDataTimes) {
									ArrayList<HashMap<String, String>> data = tmsc.todayMatchScheduleCrawling();
									if(data != null && data.size() > 0) {
										DataStorage.setTodayMatchScheduleData(data);
										DataStorage.setTodayMatchScheduleCrawlingTime(new Date());
									}
								}
						}
					} else if(crawlingDataName.equals("todayEplMatchSchedule")) {
						// DataSotrage VO 의 변수에 데이터가 들어있지 않다면...
						if(DataStorage.getTodayEplMatchData() == null) {
							// data에 크롤링하여 데이터를 넣고...
							ArrayList<HashMap<String, String>> data = emsc.eplMatchScheduleCrawling();
							// 만약 크롤링한 데이터가 있으면...
							if(data != null && data.size() > 0) {
								// DataStorage VO 안의 변수에 크롤링한 data와 크롤링한 시간을 담아라...
								DataStorage.setTodayEplMatchData(data);
								DataStorage.setTodayEplMatchCrawlingTime(new Date());
							}
						} else { // DataStorage VO 변수에 데이터가 들어 있다면...
								// 크롤링된 시간을 변수에 담고...
								Date oldTodayEplMatchCrawlingTime = DataStorage.getTodayEplMatchCrawlingTime();
								// 현재크롤링한시간 변수에 담고...
								Date currentTodayEplMatchCrawlingTime = new Date();
								
								// 현재 크롤링시간과 최근 크롤링시간의 차이를 계산하여 변수에 저장...
								long difTimes = (currentTodayEplMatchCrawlingTime.getTime() - oldTodayEplMatchCrawlingTime.getTime()) / 1000; // 초
								
								System.out.println("difTimes: " +difTimes);
								
								// 크롤링 할 
								if(difTimes > crawlingDataTimes) {
									ArrayList<HashMap<String, String>> data = emsc.eplMatchScheduleCrawling();
									if(data != null && data.size() > 0) {
										DataStorage.setTodayEplMatchData(data);
										DataStorage.setTodayEplMatchCrawlingTime(new Date());
									}
								}
						}
					} else if(crawlingDataName.equals("eplMatchDetail")) {
						// DataSotrage VO 의 변수에 데이터가 들어있지 않다면...
						if(DataStorage.getEplMatchDetailData() == null) {
							// data에 크롤링하여 데이터를 넣고...
							ArrayList<HashMap<String, String>> data = emdc.eplMatchDetailCrawling(gameId);
							// 만약 크롤링한 데이터가 있으면...
							if(data != null && data.size() > 0) {
								// DataStorage VO 안의 변수에 크롤링한 data와 크롤링한 시간을 담아라...
								DataStorage.setEplMatchDetailData(data);
								DataStorage.setEplMatchDetailCrawlingTime(new Date());
							}
						} else { // DataStorage VO 변수에 데이터가 들어 있다면...
								// 크롤링된 시간을 변수에 담고...
								Date oldEplMatchDetailCrawlingTime = DataStorage.getEplMatchDetailCrawlingTime();
								// 현재크롤링한시간 변수에 담고...
								Date currentEplMatchDetailCrawlingTime = new Date();
								
								// 현재 크롤링시간과 최근 크롤링시간의 차이를 계산하여 변수에 저장...
								long difTimes = (currentEplMatchDetailCrawlingTime.getTime() - oldEplMatchDetailCrawlingTime.getTime()) / 1000; // 초
								
								System.out.println("difTimes: " +difTimes);
								
								// 크롤링 할 
								if(difTimes > crawlingDataTimes) {
									ArrayList<HashMap<String, String>> data = emdc.eplMatchDetailCrawling(gameId);
									if(data != null && data.size() > 0) {
										DataStorage.setEplMatchDetailData(data);
										DataStorage.setEplMatchDetailCrawlingTime(new Date());
									}
								}
						}
					} else if(crawlingDataName.equals("eplTeamRank")) {
						// DataSotrage VO 의 변수에 데이터가 들어있지 않다면...
						if(DataStorage.getEplTeamRankData() == null) {
							// data에 크롤링하여 데이터를 넣고...
							ArrayList<HashMap<String, String>> data = etrc.eplTeamRankCrawling();
							// 만약 크롤링한 데이터가 있으면...
							if(data != null && data.size() > 0) {
								// DataStorage VO 안의 변수에 크롤링한 data와 크롤링한 시간을 담아라...
								DataStorage.setEplTeamRankData(data);
								DataStorage.setEplTeamRankCrawlingTime(new Date());
							}
						} else { // DataStorage VO 변수에 데이터가 들어 있다면...
								// 크롤링된 시간을 변수에 담고...
								Date oldEplTeamRankCrawlingTime = DataStorage.getEplTeamRankCrawlingTime();
								// 현재크롤링한시간 변수에 담고...
								Date currentEplTeamRankCrawlingTime = new Date();
								
								// 현재 크롤링시간과 최근 크롤링시간의 차이를 계산하여 변수에 저장...
								long difTimes = (currentEplTeamRankCrawlingTime.getTime() - oldEplTeamRankCrawlingTime.getTime()) / 1000; // 초
								
								System.out.println("difTimes: " +difTimes);
								
								// 크롤링 할 
								if(difTimes > crawlingDataTimes) {
									ArrayList<HashMap<String, String>> data = etrc.eplTeamRankCrawling();
									if(data != null && data.size() > 0) {
										DataStorage.setEplTeamRankData(data);
										DataStorage.setEplTeamRankCrawlingTime(new Date());
									}
								}
						}
					} else if(crawlingDataName.equals("eplPlayerRank")) {
						// DataSotrage VO 의 변수에 데이터가 들어있지 않다면...
						if(DataStorage.getEplPlayerRankData() == null) {
							// data에 크롤링하여 데이터를 넣고...
							ArrayList<HashMap<String, String>> data = eprc.eplPlayerRank();
							// 만약 크롤링한 데이터가 있으면...
							if(data != null && data.size() > 0) {
								// DataStorage VO 안의 변수에 크롤링한 data와 크롤링한 시간을 담아라...
								DataStorage.setEplPlayerRankData(data);
								DataStorage.setEplPlayerRankCrawlingTime(new Date());
							}
						} else { // DataStorage VO 변수에 데이터가 들어 있다면...
								// 크롤링된 시간을 변수에 담고...
								Date oldEplPlayerRankCrawlingTime = DataStorage.getEplPlayerRankCrawlingTime();
								// 현재크롤링한시간 변수에 담고...
								Date currentEplPlayerRankCrawlingTime = new Date();
								
								// 현재 크롤링시간과 최근 크롤링시간의 차이를 계산하여 변수에 저장...
								long difTimes = (currentEplPlayerRankCrawlingTime.getTime() - oldEplPlayerRankCrawlingTime.getTime()) / 1000; // 초
								
								System.out.println("difTimes: " +difTimes);
								
								// 크롤링 할 
								if(difTimes > crawlingDataTimes) {
									ArrayList<HashMap<String, String>> data = eprc.eplPlayerRank();
									if(data != null && data.size() > 0) {
										DataStorage.setEplPlayerRankData(data);
										DataStorage.setEplPlayerRankCrawlingTime(new Date());
									}
								}
						}
					} else if(crawlingDataName.equals("eplInnerPlayer")) {
						// DataSotrage VO 의 변수에 데이터가 들어있지 않다면...
						if(DataStorage.getEplInnerPlayerRankData() == null) {
							// data에 크롤링하여 데이터를 넣고...
							ArrayList<HashMap<String, String>> data = eipc.eplInnerPlayerCrawling(teamId);
							// 만약 크롤링한 데이터가 있으면...
							if(data != null && data.size() > 0) {
								// DataStorage VO 안의 변수에 크롤링한 data와 크롤링한 시간을 담아라...
								DataStorage.setEplInnerPlayerRankData(data);
								DataStorage.setEplInnerPlayerRankCrawlingTime(new Date());
							}
						} else { // DataStorage VO 변수에 데이터가 들어 있다면...
								// 크롤링된 시간을 변수에 담고...
								Date oldEplInnerPlayerRankCrawlingTime = DataStorage.getEplInnerPlayerRankCrawlingTime();
								// 현재크롤링한시간 변수에 담고...
								Date currentEplInnerPlayerRankCrawlingTime = new Date();
								
								// 현재 크롤링시간과 최근 크롤링시간의 차이를 계산하여 변수에 저장...
								long difTimes = (currentEplInnerPlayerRankCrawlingTime.getTime() - oldEplInnerPlayerRankCrawlingTime.getTime()) / 1000; // 초
								
								System.out.println("difTimes: " +difTimes);
								
								// 크롤링 할 
								if(difTimes > crawlingDataTimes) {
									ArrayList<HashMap<String, String>> data = eipc.eplInnerPlayerCrawling(teamId);
									if(data != null && data.size() > 0) {
										DataStorage.setEplInnerPlayerRankData(data);
										DataStorage.setEplInnerPlayerRankCrawlingTime(new Date());
									}
								}
						}
					}
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
				} // for finish 
				
				
				Thread.sleep(1000*5);
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
			}
		}
	}
	
	
	
	
	}
