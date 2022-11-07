package com.dcu.wfd.common.dao;

import java.util.List;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.dcu.wfd.common.vo.Test;

// 데이터 요청이 들어올 때 마다 DAO 에서 Connection 객체를 생성하면, 너무 많은 커넥션으로 인해 오류가 생긴다. 
// 그렇기 때문에 싱글톤 패턴으로 하나의 객체만 생성되도록 구성한다. 
// domain logic을 persistence mechanism 과 분리하기 위해 사용

// domain logic: business logic이나 DB와 관련 없는 로직
// persistence layer: DB에 data를 CRUD 하는 계층
// Connection 객체: DB에 접근해 Query를 수행하는 객체 
// Singleton: 클래스가 메모리에 할당 될 때 고정된 하나의 객체를 할당하고, 이후 그것만 참조하는 디자인 패턴.

@Mapper
public interface CommonDAO {
	
	public List<Test> testSelectList();
	
	public void naverSportsNewsInsert(HashMap<String, String> crawlingData);
	
}
