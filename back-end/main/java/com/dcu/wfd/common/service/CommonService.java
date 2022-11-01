package com.dcu.wfd.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcu.wfd.common.dao.CommonDAO;
import com.dcu.wfd.common.vo.Test;

@Service
public class CommonService {
	
	@Autowired
	private CommonDAO commonDAO;
	
	public List<Test> testSelectList() {
		return commonDAO.testSelectList();
	}
}
