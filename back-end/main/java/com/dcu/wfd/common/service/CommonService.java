package com.dcu.wfd.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dcu.wfd.common.dao.CommonDAO;
import com.dcu.wfd.common.vo.Test;
import com.dcu.wfd.crawling.module.main.NaverSportsNewsPopularCrawling;

// Controller가 Request를 받으면 적절한 Service에 전달하고, 전달 받은 Service는 business logic을 처리한다 
// Service 가 DB에 DAO로 접근하고, 데이터를 DTO(VO)로 전달받은 다음, 데이터를 필요에 맞게 가공하여 반환한다. 

@Service
public class CommonService {
	
	@Autowired
	private CommonDAO commonDAO;
	
	public List<Test> testSelectList() {
		return commonDAO.testSelectList();
	}
	
	public void naverSportsNewsInsert() {
		
		NaverSportsNewsPopularCrawling nsnc = new NaverSportsNewsPopularCrawling();
		
		ArrayList<HashMap<String, String>> crawlingDataList = nsnc.naverSportsNewsPopularCrawling();
		
		for(int i = 0; i < crawlingDataList.size(); i++) {
			
			commonDAO.naverSportsNewsInsert(crawlingDataList.get(i));
			
		}
		
	}
}
