package com.kodasfiledata.service;

import java.util.List;

import com.kodasfiledata.entity.Sensor;
import com.kodasfiledata.parameter.SensorParameter;

public interface SensorService {
	
	public List<Sensor> getSensorList(SensorParameter param);

	public List<Sensor> getSensorSectionList(SensorParameter param);

	public List<Sensor> getSensorRandomList(SensorParameter param);
}
