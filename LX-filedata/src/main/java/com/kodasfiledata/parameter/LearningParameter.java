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
	private List<String> environment;
	private int page;
	private List<String> move;
	private List<String> fixed;
	private String location;
	private int startNumber;
	private int endNumber;
	private int randomNumber;
	
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
	public List<String> getEnvironment() {
		return environment;
	}
	public void setEnvironment(List<String> environment) {
		this.environment = environment;
	}
	public int getStartNumber() {
		return startNumber;
	}
	public void setStartNumber(int startNumber) {
		this.startNumber = startNumber;
	}
	public int getEndNumber() {
		return endNumber;
	}
	public void setEndNumber(int endNumber) {
		this.endNumber = endNumber;
	}
	public int getRandomNumber() {
		return randomNumber;
	}
	public void setRandomNumber(int randomNumber) {
		this.randomNumber = randomNumber;
	}
	@Override
	public String toString() {
		return "LearningParameter [startDate=" + startDate + ", endDate=" + endDate + ", learningType=" + learningType
				+ ", learningType2=" + learningType2 + ", lowVelocity=" + lowVelocity + ", highVelocity=" + highVelocity
				+ ", sunlight=" + sunlight + ", weather=" + weather + ", conditions=" + conditions + ", environment="
				+ environment + ", page=" + page + ", move=" + move + ", fixed=" + fixed + ", location=" + location
				+ ", startNumber=" + startNumber + ", endNumber=" + endNumber + ", randomNumber=" + randomNumber + "]";
	}
}
