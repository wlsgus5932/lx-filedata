package com.kodasfiledata.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kodasfiledata.entity.Area;

@Mapper
public interface AreaDao {
	public List<Area> getArea();

}
