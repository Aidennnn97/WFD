package com.dcu.wfd.crawling.module.epl;

import java.util.ArrayList;
import java.util.HashMap;

import com.dcu.wfd.common.vo.DataStorage;
import com.dcu.wfd.util.HttpUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EplMatchDetailCrawling {
	// 경기 상세 정보 크롤링 모듈.
	public ArrayList<HashMap<String, String>> eplMatchDetailCrawling(String gameId)throws Exception {
		
		ArrayList<HashMap<String, String>> GameDetailList = new ArrayList<>();

		String url = "https://sports.daum.net/prx/hermes/api/game/get.json?gameId="+gameId+"&detail=liveData";

		try {
			HttpUtil httpUtil = new HttpUtil();
			String responseBody = httpUtil.httpRequest(url, null);

			HashMap<String, String> Detail1 = new HashMap<>();

			ObjectMapper mapper = new ObjectMapper();         //json 파싱기
			HashMap<String, Object> jsonData =  mapper.readValue(responseBody, new TypeReference<HashMap<String, Object> >() {});
			HashMap<String, Object> homeList = (HashMap<String, Object>) jsonData.get("home");
			HashMap<String, Object> homeTeamList= (HashMap<String, Object>) homeList.get("team");
			HashMap<String, Object> homeFormationList = (HashMap<String, Object>) homeList.get("formation");
			ArrayList<HashMap<String, Object>> homeTeamPlayer = (ArrayList<HashMap<String, Object>>) jsonData.get("homePerson");

			String homeTeamName = (String)homeTeamList.get("name");
			String homeTeamImg = (String) homeTeamList.get("imageUrl");
			String homeTeamResult = (String) homeList.get("result");
			Detail1.put("homeTeamName", homeTeamName);
			Detail1.put("homeTeamImg", homeTeamImg);
			Detail1.put("homeTeamResult", homeTeamResult);

			if(homeFormationList != null) {

				String homeTeamFormation = (String) homeFormationList.get("name");
				Detail1.put("homeTeamFormation", homeTeamFormation);

			} else {

				Detail1.put("homeTeamFormation", null);
			}

			for(int i=0; i < homeTeamPlayer.size(); i++) {
				HashMap<String, String> Detail2 = new HashMap<>();

				String homePlayerName = (String) homeTeamPlayer.get(i).get("name");
				String homePlayerImg = (String) homeTeamPlayer.get(i).get("imageUrl");
				String homePlayerFormation = String.valueOf(homeTeamPlayer.get(i).get("formationPlace"));
				String homePlayerChanged = String.valueOf(homeTeamPlayer.get(i).get("isChanged"));
				String homePlayerStarted = String.valueOf(homeTeamPlayer.get(i).get("isStarted"));
				String homePlayerBackNumber = String.valueOf(homeTeamPlayer.get(i).get("backNumber"));

				Detail2.put("homePlayerName", homePlayerName);
				Detail2.put("homePlayerImg", homePlayerImg);
				Detail2.put("homePlayerFormation", homePlayerFormation);
				Detail2.put("homePlayerChanged", homePlayerChanged);
				Detail2.put("homePlayerStarted", homePlayerStarted);
				Detail2.put("homePlayerBackNumber", homePlayerBackNumber);
				GameDetailList.add(Detail2);

			}

			HashMap<String, Object> awayList = (HashMap<String, Object>) jsonData.get("away");
			HashMap<String, Object> awayTeamList = (HashMap<String, Object>) awayList.get("team");
			HashMap<String, Object> awayFormationList = (HashMap<String, Object>) awayList.get("formation");
			ArrayList<HashMap<String, Object>> awayTeamPlayer = (ArrayList<HashMap<String, Object>>) jsonData.get("awayPerson");


			String awayTeamName = (String) awayTeamList.get("name");
			String awayTeamImg = (String) awayTeamList.get("imageUrl");
			String awayTeamResult = (String) awayList.get("result");

			Detail1.put("awayTeamName", awayTeamName);
			Detail1.put("awayTeamImg", awayTeamImg);
			Detail1.put("awayTeamResult", awayTeamResult);

			if(awayFormationList != null) {
				String awayTeamFormation = (String) awayFormationList.get("name");
				Detail1.put("awayTeamFormation", awayTeamFormation);
			} else {
				Detail1.put("awayTeamFormation", null);

			}
			for(int i=0; i < awayTeamPlayer.size(); i++) {
				HashMap<String, String> Detail3 = new HashMap<>();

				String awayPlayerName = (String) awayTeamPlayer.get(i).get("name");
				String awayPlayerImg = (String) awayTeamPlayer.get(i).get("imageUrl");
				String awayPlayerFormation = String.valueOf(awayTeamPlayer.get(i).get("formationPlace"));
				String awayPlayerChanged = String.valueOf(awayTeamPlayer.get(i).get("isChanged"));
				String awayPlayerStarted = String.valueOf(awayTeamPlayer.get(i).get("isStarted"));
				String awayPlayerBackNumber = String.valueOf(awayTeamPlayer.get(i).get("backNumber"));

				Detail3.put("awayPlayerName", awayPlayerName);
				Detail3.put("awayPlayerImg", awayPlayerImg);
				Detail3.put("awayPlayerFormation", awayPlayerFormation);
				Detail3.put("awayPlayerChanged", awayPlayerChanged);
				Detail3.put("awayPlayerStarted", awayPlayerStarted);
				Detail3.put("awayPlayerBackNumber", awayPlayerBackNumber);
				GameDetailList.add(Detail3);


			}
			HashMap<String, Object> homeStatList = (HashMap<String, Object>) jsonData.get("homeTeamStat");
			String HomePossession = String.valueOf(homeStatList.get("possession"));      //점유율
			String HomeShooting = String.valueOf(homeStatList.get("sht"));      //점유율
			String HomeSog = String.valueOf(homeStatList.get("sog"));      //유효슈팅
			String HomeCk = String.valueOf(homeStatList.get("ck"));      //코너킥
			String HomeFo = String.valueOf(homeStatList.get("fo"));      //파울
			String HomeOff = String.valueOf(homeStatList.get("off"));      //오프사이드
			String HomeYel = String.valueOf(homeStatList.get("yel"));      //경고
			String HomeRed = String.valueOf(homeStatList.get("red"));      //퇴장


			Detail1.put("HomePossession", HomePossession);
			Detail1.put("HomeShooting", HomeShooting);
			Detail1.put("HomeSog", HomeSog);
			Detail1.put("HomeCk", HomeCk);
			Detail1.put("HomeFo", HomeFo);
			Detail1.put("HomeOff", HomeOff);
			Detail1.put("HomeYel", HomeYel);
			Detail1.put("HomeRed", HomeRed);

			HashMap<String, Object> awayStatList = (HashMap<String, Object>) jsonData.get("awayTeamStat");
			String AwayPossession = String.valueOf(awayStatList.get("possession"));      //점유율
			String AwayShooting = String.valueOf(awayStatList.get("sht"));      //점유율
			String AwaySog = String.valueOf(awayStatList.get("sog"));      //유효슈팅
			String AwayCk = String.valueOf(awayStatList.get("ck"));      //코너킥
			String AwayFo = String.valueOf(awayStatList.get("fo"));      //파울
			String AwayOff = String.valueOf(awayStatList.get("off"));      //오프사이드
			String AwayYel = String.valueOf(awayStatList.get("yel"));      //경고
			String AwayRed = String.valueOf(awayStatList.get("red"));      //퇴장

			Detail1.put("AwayPossession", AwayPossession);
			Detail1.put("AwayShooting", AwayShooting);
			Detail1.put("AwaySog", AwaySog);
			Detail1.put("AwayCk", AwayCk);
			Detail1.put("AwayFo", AwayFo);
			Detail1.put("AwayOff", AwayOff);
			Detail1.put("AwayYel", AwayYel);
			Detail1.put("AwayRed", AwayRed);
			GameDetailList.add(Detail1);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return GameDetailList;
	}
}
