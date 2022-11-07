package com.dcu.wfd.common.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MainDAO {
	
	public void naverSportsNewsPopularInsert(HashMap<String, String> crawlingData);
	
}
