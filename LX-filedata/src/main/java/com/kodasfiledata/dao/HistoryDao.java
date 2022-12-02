package com.kodasfiledata.dao;



import org.apache.ibatis.annotations.Mapper;

import com.kodasfiledata.parameter.HistoryParameter;

@Mapper
public interface HistoryDao {
	public void setHistoryList(HistoryParameter param);

}
