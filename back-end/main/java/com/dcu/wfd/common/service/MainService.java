package com.dcu.wfd.common.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcu.wfd.common.dao.MainDAO;
import com.dcu.wfd.crawling.module.NaverSportsNewsPopularCrawling;

@Service
public class MainService {

	@Autowired
	private MainDAO mainDAO;

	public void naverSportsNewsPopularInsert() {

		NaverSportsNewsPopularCrawling nsnc = new NaverSportsNewsPopularCrawling();

		ArrayList<HashMap<String, String>> crawlingDataList = nsnc.naverSportsNewsPopularCrawling();

		for(int i = 0; i < crawlingDataList.size(); i++) {

			mainDAO.naverSportsNewsPopularInsert(crawlingDataList.get(i));

		}

	}

}
