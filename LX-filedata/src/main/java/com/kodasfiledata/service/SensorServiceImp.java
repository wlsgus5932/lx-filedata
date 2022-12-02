package com.kodasfiledata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodasfiledata.dao.SensorDao;
import com.kodasfiledata.entity.Sensor;
import com.kodasfiledata.parameter.SensorParameter;

@Service
public class SensorServiceImp implements SensorService {
	
	@Autowired
	private SensorDao dao;

	@Override
	public List<Sensor> getSensorList(SensorParameter param) {
		return dao.getSensorList(param);
	}

	@Override
	public List<Sensor> getSensorSectionList(SensorParameter param) {
		return dao.getSensorSectionList(param);
	}

	@Override
	public List<Sensor> getSensorRandomList(SensorParameter param) {
		return dao.getSensorRandomList(param);
	}

}
