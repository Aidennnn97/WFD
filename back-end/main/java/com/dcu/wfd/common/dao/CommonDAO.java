package com.dcu.wfd.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dcu.wfd.common.vo.Test;

@Mapper
public interface CommonDAO {
	
	public List<Test> testSelectList();
}
