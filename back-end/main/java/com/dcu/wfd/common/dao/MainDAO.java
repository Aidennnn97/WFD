package com.dcu.wfd.common.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dcu.wfd.common.vo.NewsVO;

@Mapper
public interface MainDAO {
	
	public void naverSportsNewsPopularInsert(HashMap<String, String> crawlingData);

	public List<NewsVO> newsSelectList();
	
}
