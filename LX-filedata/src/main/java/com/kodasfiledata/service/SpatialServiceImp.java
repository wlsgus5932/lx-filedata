package com.kodasfiledata.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodasfiledata.dao.SpatialDao;
import com.kodasfiledata.entity.Spatial;
import com.kodasfiledata.parameter.SpatialParameter;

@Service
public class SpatialServiceImp implements SpatialService {
	
	@Autowired
	private SpatialDao dao;

	@Override
	public List<Spatial> getSpatialList(SpatialParameter param) {
		return dao.getSpatialList(param);
	}
	

}
