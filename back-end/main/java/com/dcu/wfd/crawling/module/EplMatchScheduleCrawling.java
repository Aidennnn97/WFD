package com.dcu.wfd.crawling.module;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.dcu.wfd.util.HttpUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EplMatchScheduleCrawling {

	// Epl 경기 일정 크롤링 모듈.
	public ArrayList<HashMap<String, String>> eplMatchScheduleCrawling()throws Exception {

		ArrayList<HashMap<String, String>> matchDataList = new ArrayList<>();

		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
		YearMonth today1 = YearMonth.now();
		DateTimeFormatter date2 = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate start1 = today1.atDay(1);
		LocalDate end1 = today1.atEndOfMonth();
		
		String url = "https://sports.daum.net/prx/hermes/api/game/schedule.json?page=1&leagueCode=epl%2Cprimera%2Cbundesliga%2Cseriea%2Cligue1%2Ceredivisie%2Cuefacl%2Cuefacup%2Cepl_cup%2Cfacup%2Ccopadelrey&fromDate="+start1.format(date2)+"&toDate="+end1.format(date2);


		try {
			HttpUtil httpUtil = new HttpUtil();

			String responseBody = httpUtil.httpRequest(url, null);
			ObjectMapper mapper = new ObjectMapper();
			HashMap<String, Object> jsonData =  mapper.readValue(responseBody, new TypeReference<HashMap<String, Object> >() {});
			HashMap<String, Object> matchList = (HashMap<String, Object>) jsonData.get("schedule");
			ArrayList<HashMap<String,Object>> matchList2 = (ArrayList<HashMap<String, Object>>) matchList.get("20221106");

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
					String gameId = String.valueOf(matchList2.get(i).get("gameId"));


					HashMap<String, String> matchData = new HashMap<>(); 

					if(leagueName.equals("프리미어리그")) {
						matchData.put("gameId", gameId);
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
