package com.kodasfiledata.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kodasfiledata.entity.Sensor;
import com.kodasfiledata.parameter.SensorParameter;

@Mapper
public interface SensorDao {
	public List<Sensor> getSensorList(SensorParameter param);

	public List<Sensor> getSensorSectionList(SensorParameter param);

	public List<Sensor> getSensorRandomList(SensorParameter param);
}
