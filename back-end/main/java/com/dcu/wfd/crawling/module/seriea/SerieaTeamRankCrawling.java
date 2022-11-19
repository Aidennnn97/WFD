package com.dcu.wfd.crawling.module.seriea;

import java.util.ArrayList; 
import java.util.HashMap;
import com.dcu.wfd.util.HttpUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerieaTeamRankCrawling {
   public ArrayList<HashMap<String, String>> serieaTeamRankCrawling() throws Exception { 
      
      ArrayList<HashMap<String, String>> trCrawlingDataList = new ArrayList<>();
      HttpUtil httpUtil = new HttpUtil();
      String url = "https://sports.daum.net/prx/hermes/api/team/rank.json?leagueCode=seriea&seasonKey=20222023&page=1&pageSize=100";
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
            
            HashMap<String, Object> teamRankInfo2 = (HashMap<String, Object>) trList.get(i).get("stat");
               String teamGfPerGame = String.valueOf(teamRankInfo2.get("gfPerGame"));
               String teamGaPerGame = String.valueOf(teamRankInfo2.get("gaPerGame"));

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
            teamRankData.put("teamGfPerGame", teamGfPerGame);
               teamRankData.put("teamGaPerGame", teamGaPerGame);
            trCrawlingDataList.add(teamRankData);

         }


      } catch (Exception e) {
         // TODO: handle exception
         e.printStackTrace();
      }
      return trCrawlingDataList;
   }
}