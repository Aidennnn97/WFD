package com.dcu.wfd.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcu.wfd.common.dao.MainDAO;
import com.dcu.wfd.common.vo.NewsVO;
import com.dcu.wfd.crawling.module.NaverSportsNewsPopularCrawling;

@Service
public class MainService {

	@Autowired
	private MainDAO mainDAO;

	public void naverSportsNewsPopularInsert() {

		// 크롤링 모듈 객체 생성 
		NaverSportsNewsPopularCrawling nsnc = new NaverSportsNewsPopularCrawling();

		ArrayList<HashMap<String, String>> crawlingDataList = nsnc.naverSportsNewsPopularCrawling();

		for(int i = 0; i < crawlingDataList.size(); i++) {

			mainDAO.naverSportsNewsPopularInsert(crawlingDataList.get(i));

		}

	}

	public List<NewsVO> newsSelectList() {
		// TODO Auto-generated method stub
		return mainDAO.newsSelectList();
	}

}
