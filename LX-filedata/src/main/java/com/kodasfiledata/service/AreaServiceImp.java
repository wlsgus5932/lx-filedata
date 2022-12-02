package com.kodasfiledata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodasfiledata.dao.AreaDao;
import com.kodasfiledata.entity.Area;

@Service
public class AreaServiceImp implements AreaService {
	
	@Autowired
	private AreaDao dao;
	
	@Override
	public List<Area> getArea() {
		return dao.getArea();
	}

}
