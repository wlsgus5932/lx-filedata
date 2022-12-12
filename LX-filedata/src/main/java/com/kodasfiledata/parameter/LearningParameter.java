package com.kodasfiledata.parameter;


import java.util.List;

public class LearningParameter {
	private String startDate;
	private String endDate;
	private String learningType;
	private String learningType2;
	private int lowVelocity;
	private int highVelocity;
	private String sunlight;
	private String weather;
	private String conditions;
	private String environment1;
	private String environment2;
	private int page;
	private List<String> move;
	private List<String> fixed;
	private String location;
//	private List<String> environment2 = new ArrayList<>();
//	private String person;
//	private String car;
//	private String truck;
//	private String bus;
//	private String van;
//	private String motorCycle;
//	private String specialCar;
//	private String senTp;
//	private String pstnNo;
//	private int fileId;
//	private int randomSearchCnt;
//	private Boolean isSector;
//	private String stopLine;
//	private String crossWalk;
//	private String traffic;
//	private String construction;
//	private String bump;
//	private int page;
//	private int countNumber;
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getLearningType() {
		return learningType;
	}
	public void setLearningType(String learningType) {
		this.learningType = learningType;
	}
	public String getLearningType2() {
		return learningType2;
	}
	public void setLearningType2(String learningType2) {
		this.learningType2 = learningType2;
	}
	public int getLowVelocity() {
		return lowVelocity;
	}
	public void setLowVelocity(int lowVelocity) {
		this.lowVelocity = lowVelocity;
	}
	public int getHighVelocity() {
		return highVelocity;
	}
	public void setHighVelocity(int highVelocity) {
		this.highVelocity = highVelocity;
	}
	public String getSunlight() {
		return sunlight;
	}
	public void setSunlight(String sunlight) {
		this.sunlight = sunlight;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getConditions() {
		return conditions;
	}
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}
	public String getEnvironment1() {
		return environment1;
	}
	public void setEnvironment1(String environment1) {
		this.environment1 = environment1;
	}
	public String getEnvironment2() {
		return environment2;
	}
	public void setEnvironment2(String environment2) {
		this.environment2 = environment2;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public List<String> getMove() {
		return move;
	}
	public void setMove(List<String> move) {
		this.move = move;
	}
	public List<String> getFixed() {
		return fixed;
	}
	public void setFixed(List<String> fixed) {
		this.fixed = fixed;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Override
	public String toString() {
		return "LearningParameter [startDate=" + startDate + ", endDate=" + endDate + ", learningType=" + learningType
				+ ", learningType2=" + learningType2 + ", lowVelocity=" + lowVelocity + ", highVelocity=" + highVelocity
				+ ", sunlight=" + sunlight + ", weather=" + weather + ", conditions=" + conditions + ", environment1="
				+ environment1 + ", environment2=" + environment2 + ", page=" + page + ", move=" + move + ", fixed="
				+ fixed + ", location=" + location + "]";
	}
	
	
	
	

}
