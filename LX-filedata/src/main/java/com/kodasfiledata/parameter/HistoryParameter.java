package com.kodasfiledata.parameter;

import java.util.List;

public class HistoryParameter {
	private String listName;
	private String userId;
	private List<String> dataName;
	
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<String> getDataName() {
		return dataName;
	}
	public void setDataName(List<String> dataName) {
		this.dataName = dataName;
	}
	@Override
	public String toString() {
		return "HistoryParameter [listName=" + listName + ", userId=" + userId + ", dataName=" + dataName + "]";
	}
}
