package com.kodasfiledata.parameter;

import java.util.ArrayList;
import java.util.List;

public class SpatialParameter {
	private String location;
	private String dataType;
	private String targetType;
	private String indexRatio;
	private List<String> indexValue = new ArrayList<>();
	private int randomSearchCnt;
	private String  strArea;
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getTargetType() {
		return targetType;
	}
	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}
	public String getIndexRatio() {
		return indexRatio;
	}
	public void setIndexRatio(String indexRatio) {
		this.indexRatio = indexRatio;
	}
	public List<String> getIndexValue() {
		return indexValue;
	}
	public void setIndexValue(List<String> indexValue) {
		this.indexValue = indexValue;
	}
	public int getRandomSearchCnt() {
		return randomSearchCnt;
	}
	public void setRandomSearchCnt(int randomSearchCnt) {
		this.randomSearchCnt = randomSearchCnt;
	}
	public String getStrArea() {
		return strArea;
	}
	public void setStrArea(String strArea) {
		this.strArea = strArea;
	}
	@Override
	public String toString() {
		return "SpatialParameter [location=" + location + ", dataType=" + dataType + ", targetType=" + targetType
				+ ", indexRatio=" + indexRatio + ", indexValue=" + indexValue + ", randomSearchCnt=" + randomSearchCnt
				+ ", strArea=" + strArea + "]";
	}
	
	
	
	

}
