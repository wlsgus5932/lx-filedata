package com.kodasfiledata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodasfiledata.dao.HistoryDao;
import com.kodasfiledata.entity.History;
import com.kodasfiledata.parameter.HistoryParameter;

@Service
public class HistoryServiceImp implements HistoryService{
	@Autowired
	private HistoryDao dao;

	@Override
	public void setHistoryList(HistoryParameter param) {
		// TODO Auto-generated method stub
		dao.setHistoryList(param);
	}

}
