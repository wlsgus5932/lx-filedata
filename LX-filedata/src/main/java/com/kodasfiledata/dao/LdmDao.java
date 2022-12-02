package com.kodasfiledata.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kodasfiledata.entity.Ldm;

@Mapper
public interface LdmDao {
	public List<Ldm> getLdmList();
}
