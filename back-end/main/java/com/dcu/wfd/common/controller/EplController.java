package com.dcu.wfd.common.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dcu.wfd.util.HttpUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class EplController {

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
	   
	   @RequestMapping("/craw/innerPlayerRank.ajax")
	   @ResponseBody
	   public ArrayList<HashMap<String, String>> innerPlayerRank(HttpServletRequest req, HttpServletResponse resp)throws Exception{
	      boolean result = false;
	      
	      ArrayList<HashMap<String, String>> innerPlayerRankList = new ArrayList<>();
	      HttpUtil httpUtil = new HttpUtil();
	      String url2 = "https://sports.daum.net/prx/hermes/api/team/rank.json?leagueCode=epl&seasonKey=20222023&page=1&pageSize=100";
	      try {
	         ObjectMapper mapper = new ObjectMapper();
	         String repBody2 = httpUtil.httpRequest(url2, null);
	         
	         HashMap<String, Object> data2 = mapper.readValue(repBody2, new TypeReference<HashMap<String, Object>>() {});   
	         
	         ArrayList<HashMap<String, Object>> teamList = (ArrayList<HashMap<String, Object>>) data2.get("list");
	         for(int j=0; j<teamList.size(); j++) {
	            String playerTeamId = String.valueOf(teamList.get(j).get("teamId"));
	            
	              HashMap<String, String> innerPlayerRank1 = new HashMap<>();
	              innerPlayerRank1.put("playerTeamId", playerTeamId);
	                 
	            String url = "https://sports.daum.net/prx/hermes/api/person/list.json?leagueCode=epl&seasonKey=20222023&teamId="+playerTeamId+"&detail=true&pageSize=100";
	            String repBody = httpUtil.httpRequest(url, null);
	            HashMap<String, Object> data = mapper.readValue(repBody, new TypeReference<HashMap<String, Object>>() {});
	            ArrayList<HashMap<String, Object>> playerList = (ArrayList<HashMap<String, Object>>) data.get("list");
	            
	            for(int i=0; i < playerList.size(); i++) {
	               
	               String playerName = (String) playerList.get(i).get("nameMain");
	               innerPlayerRank1.put("playerName", playerName);
	               
	               HashMap<String, Object> playerTeam = (HashMap<String, Object>) playerList.get(i).get("team");
	               String teamName = (String) playerTeam.get("name");
	               innerPlayerRank1.put("teamName", teamName);
	               
	               HashMap<String, Object> playerStat = (HashMap<String, Object>) playerList.get(i).get("stat"); 
	                  String playerGp = String.valueOf(playerStat.get("gp"));
	                  String playerMp = String.valueOf(playerStat.get("mp"));
	                  String playerSht = String.valueOf(playerStat.get("sht"));
	                  String playerAst = String.valueOf(playerStat.get("ast"));
	                  
	                  innerPlayerRank1.put("playerGp", playerGp);
	                  innerPlayerRank1.put("playerMp", playerMp);
	                  innerPlayerRank1.put("playerSht", playerSht);
	                  innerPlayerRank1.put("playerAst", playerAst);
	                  innerPlayerRankList.add(innerPlayerRank1);
	                  
	            }
	         }
	         
	         
	         
	      } catch (Exception e) {
	         // TODO: handle exception
	         e.printStackTrace();
	      }
	      return innerPlayerRankList;
	   }
}
