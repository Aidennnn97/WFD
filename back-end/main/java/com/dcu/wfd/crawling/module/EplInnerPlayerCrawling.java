package com.dcu.wfd.crawling.module;

import java.util.ArrayList;
import java.util.HashMap;
import com.dcu.wfd.util.HttpUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EplInnerPlayerCrawling {
	
	public ArrayList<HashMap<String, String>> eplInnerPlayerCrawling(String teamId)throws Exception{

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
			String url = "https://sports.daum.net/prx/hermes/api/person/list.json?leagueCode=epl&seasonKey=20222023&teamId="+ teamId +"&detail=true&pageSize=100";
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
