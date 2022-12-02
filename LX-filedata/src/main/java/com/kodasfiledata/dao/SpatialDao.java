package com.kodasfiledata.dao;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;

import com.kodasfiledata.entity.Spatial;
import com.kodasfiledata.parameter.SpatialParameter;

@Mapper
public interface SpatialDao {
	public List<Spatial> getSpatialList(SpatialParameter param);
	
}
