package com.dcu.wfd.common.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dcu.wfd.util.HttpUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class EplController {

	@RequestMapping("/epl")
	public String eplJsp(Model model) {

		return "/epl";
	}
	
	
//	오늘 경기 일정 크롤링 
	@RequestMapping("/craw/eplPlan.ajax")
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
	               String gameId = String.valueOf(matchList2.get(i).get("gameId"));
	               
	               
	               HashMap<String, String> matchData = new HashMap<>(); 
	               
	               if(leagueName.equals("프리미어리그") || leagueName.equals("라리가") || leagueName.equals("세리에A") || leagueName.equals("분데스리가")) {
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
	
	
	
	// 경기박스 클릭시 해당경기 상세 정보  크롤링 
	@RequestMapping("/craw/clickMatchData.ajax")
	   @ResponseBody
	   public ArrayList<HashMap<String, String>> craw_select3(String gameId, HttpServletRequest req, HttpServletResponse resp)throws Exception {
	      boolean result = false;
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
	         
	         
	         result = true;
	      } catch (Exception e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	      return GameDetailList;
	   }
	
	
	
	
	// 전체 팀 순위 크롤링 
	@RequestMapping("/craw/eplTeamRank.ajax")
	@ResponseBody
	public ArrayList<HashMap<String, String>> eplTeamRank(HttpServletRequest req, HttpServletResponse resp)throws Exception { 
		boolean result = false;
		ArrayList<HashMap<String, String>> trCrawlingDataList = new ArrayList<>();
		HttpUtil httpUtil = new HttpUtil();
		String url = "https://sports.daum.net/prx/hermes/api/team/rank.json?leagueCode=epl&seasonKey=20222023&page=1&pageSize=100";
		try {
			String repBody = httpUtil.httpRequest(url, null);

			ObjectMapper mapper = new ObjectMapper();
			HashMap<String, Object> jsonTrData = mapper.readValue(repBody, new TypeReference<HashMap<String, Object>>() {});
			ArrayList<HashMap<String, Object>> trList = (ArrayList<HashMap<String, Object>>) jsonTrData.get("list");


			for(int i = 0; i < trList.size(); i++) {


				String teamName = (String) trList.get(i).get("nameMain");
				String teamImg = (String) trList.get(i).get("imageUrl");
				String teamId = String.valueOf(trList.get(i).get("teamId"));

				HashMap<String, Object> teamRankInfo = (HashMap<String, Object>) trList.get(i).get("rank");
				String teamRank = String.valueOf(teamRankInfo.get("rank"));
				String teamGame = String.valueOf(teamRankInfo.get("game"));
				String teamWin = String.valueOf(teamRankInfo.get("win"));
				String teamDraw = String.valueOf(teamRankInfo.get("draw"));
				String teamLoss = String.valueOf(teamRankInfo.get("loss"));
				String teamGf = String.valueOf(teamRankInfo.get("gf"));
				String teamGa = String.valueOf(teamRankInfo.get("ga"));
				String teamGd = String.valueOf(teamRankInfo.get("gd"));
				String teamPts = String.valueOf(teamRankInfo.get("pts"));

				HashMap<String, String> teamRankData = new HashMap<>();

				teamRankData.put("teamId", teamId);
				teamRankData.put("teamName", teamName);
				teamRankData.put("teamImg", teamImg);
				teamRankData.put("teamRank", teamRank);
				teamRankData.put("teamGame", teamGame);
				teamRankData.put("teamWin", teamWin);
				teamRankData.put("teamDraw", teamDraw);
				teamRankData.put("teamLoss", teamLoss);
				teamRankData.put("teamGf", teamGf);
				teamRankData.put("teamGa", teamGa);
				teamRankData.put("teamGd", teamGd);
				teamRankData.put("teamPts", teamPts);
				trCrawlingDataList.add(teamRankData);
				result = true;

			}


		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return trCrawlingDataList;
	}
	
	
	@RequestMapping("/craw/eplPlayerRank.ajax")
	@ResponseBody
	public ArrayList<HashMap<String, String>> eplPlayerRank(HttpServletRequest req, HttpServletResponse resp)throws Exception {
		boolean result = false;
		ArrayList<HashMap<String, String>> trCrawlingDataList2 = new ArrayList<>();
		HttpUtil httpUtil = new HttpUtil();
		String url = "https://sports.daum.net/prx/hermes/api/person/rank.json?leagueCode=epl&seasonKey=20222023&page=1&pageSize=100&sort=gf";

		try {
			String repBody = httpUtil.httpRequest(url, null);

			ObjectMapper mapper = new ObjectMapper();
			HashMap<String, Object> jsonTrData = mapper.readValue(repBody, new TypeReference<HashMap<String, Object>>() {});
			ArrayList<HashMap<String, Object>> trList = (ArrayList<HashMap<String, Object>>) jsonTrData.get("list");

			for(int i = 0; i <20; i++) {
				String name = (String) trList.get(i).get("name");
				String playerImg = (String) trList.get(i).get("imageUrl");

				HashMap<String, Object> PersonRankInfo = (HashMap<String, Object>) trList.get(i).get("stat");
				String personRank = String.valueOf(PersonRankInfo.get("rank"));
				String personGf = String.valueOf(PersonRankInfo.get("gf"));            //득점
				String personAst = String.valueOf(PersonRankInfo.get("ast"));         //어시스트
				String personSht = String.valueOf(PersonRankInfo.get("sht"));         //슈팅

				HashMap<String, String> PlayerRankData = new HashMap<>();
				PlayerRankData.put("personRank", personRank);
				PlayerRankData.put("name", name);
				PlayerRankData.put("playerImg", playerImg);
				PlayerRankData.put("personGf", personGf);
				PlayerRankData.put("personAst", personAst);
				PlayerRankData.put("personSht", personSht);
				trCrawlingDataList2.add(PlayerRankData);
				result = true;

			}


		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return trCrawlingDataList2;
	}

	//팀 내 모든선수 
	@RequestMapping("/craw/innerPlayerRank.ajax")
	@ResponseBody
	public ArrayList<HashMap<String, String>> innerPlayerRank(String Tid, HttpServletRequest req, HttpServletResponse resp)throws Exception{
		
		boolean result = false;
		
		// 모든 팀 내의 선수단 정보를 받아올 그릇 (리그 내 전체 선수 데이터)
		ArrayList<HashMap<String, String>> allPlayerList = new ArrayList<>();
		HttpUtil httpUtil = new HttpUtil();
		
		// 팀 리스트의 데이터 크롤링 주소 
		//String url2 = "https://sports.daum.net/prx/hermes/api/team/rank.json?leagueCode=epl&seasonKey=20222023&page=1&pageSize=100";
		try {
			ObjectMapper mapper = new ObjectMapper();
//			String repBody2 = httpUtil.httpRequest(url2, null);
//			
//			
//			HashMap<String, Object> data2 = mapper.readValue(repBody2, new TypeReference<HashMap<String, Object>>() {});   
//			
//			// 팀 리스트 크롤링 주소안의 list로부터 팀 아이디 값을 받아옴. 
//			ArrayList<HashMap<String, Object>> teamList = (ArrayList<HashMap<String, Object>>) data2.get("list");
			
			// 20개 팀 개수 만큼 반복 
//			for(int j=0; j<teamList.size(); j++) {
//				String playerTeamId = String.valueOf(teamList.get(j).get("teamId"));

				// 팀 상세페이지의 데이터 크롤링 주소 
				String url = "https://sports.daum.net/prx/hermes/api/person/list.json?leagueCode=epl&seasonKey=20222023&teamId="+ Tid +"&detail=true&pageSize=100";
				String repBody = httpUtil.httpRequest(url, null);
				HashMap<String, Object> data = mapper.readValue(repBody, new TypeReference<HashMap<String, Object>>() {});
				
				
				// 팀 상세페이지의 선수단 정보를 담을 그릇 
				ArrayList<HashMap<String, Object>> teamPlayerList = (ArrayList<HashMap<String, Object>>)data.get("list");
				
				
				for(int i=0; i < teamPlayerList.size(); i++) {
					
					// 선수정보: 팀아이디, 팀이름, 선수이름, 선수 골, 선수 경기수, , 선수 슈팅, 선수 어시스트 담을 그릇 
					HashMap<String, String> playerInfo = new HashMap<>();
					
					// 선수정보에 팀아이디 삽입 
//					playerInfo.put("playerTeamId", playerTeamId);
					
					String playerName = (String) teamPlayerList.get(i).get("nameMain");
					
					playerInfo.put("playerName", playerName);

					HashMap<String, Object> playerTeam = (HashMap<String, Object>) teamPlayerList.get(i).get("team");
					
					String teamName = (String) playerTeam.get("name");
					playerInfo.put("teamName", teamName);

					HashMap<String, Object> playerStat = (HashMap<String, Object>) teamPlayerList.get(i).get("stat");
					
					String playerImg = (String)teamPlayerList.get(i).get("imageUrl");
					playerInfo.put("playerImg" , playerImg);
					
					if(playerStat != null) {
						String playerGf = String.valueOf(playerStat.get("gf"));
						String playerMp = String.valueOf(playerStat.get("mp"));
						String playerSht = String.valueOf(playerStat.get("sht"));
						String playerAst = String.valueOf(playerStat.get("ast"));
					
						playerInfo.put("playerGf", playerGf);
						playerInfo.put("playerMp", playerMp);
						playerInfo.put("playerSht", playerSht);
						playerInfo.put("playerAst", playerAst);
					} else {
						playerInfo.put("playerGf", "0");
						playerInfo.put("playerMp", "0");
						playerInfo.put("playerSht", "0");
						playerInfo.put("playerAst", "0");
					}
					allPlayerList.add(playerInfo);
				}
				
//			}



		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return allPlayerList;
	}
}
