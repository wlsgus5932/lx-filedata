package com.kodasfiledata.dao;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;

import com.kodasfiledata.entity.Learning;
import com.kodasfiledata.parameter.LearningParameter;

@Mapper
public interface LearningDao {
	public List<Learning> getLearningList(LearningParameter param);

	public int getLearningListTotal(LearningParameter param);

}
