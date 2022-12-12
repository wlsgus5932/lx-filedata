package com.kodasfiledata.parameter;

public class SensorParameter {
	private String startDate;
	private String endDate;
	private int lowVelocity;
	private int highVelocity;
	private String sunlight;
	private String weather;
	private String conditions;
	private String location;
//	private String senTp;
//	private String pstnNo;
	private String sensorType1;
	private String sensorType2;
	private int page;
	private int startNumber;
	private int endNumber;
	private int randomNumber;

	public int getRandomNumber() {
		return randomNumber;
	}

	public void setRandomNumber(int randomNumber) {
		this.randomNumber = randomNumber;
	}

	public String getSensorType1() {
		return sensorType1;
	}

	public void setSensorType1(String sensorType1) {
		this.sensorType1 = sensorType1;
	}

	public String getSensorType2() {
		return sensorType2;
	}

	public void setSensorType2(String sensorType2) {
		this.sensorType2 = sensorType2;
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

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "SensorParameter [startDate=" + startDate + ", endDate=" + endDate + ", lowVelocity=" + lowVelocity
				+ ", highVelocity=" + highVelocity + ", sunlight=" + sunlight + ", weather=" + weather + ", conditions="
				+ conditions + ", location=" + location + ", sensorType1=" + sensorType1 + ", sensorType2="
				+ sensorType2 + ", page=" + page + ", startNumber=" + startNumber + ", endNumber=" + endNumber
				+ ", getSensorType1()=" + getSensorType1() + ", getSensorType2()=" + getSensorType2()
				+ ", getStartNumber()=" + getStartNumber() + ", getEndNumber()=" + getEndNumber() + ", getPage()="
				+ getPage() + ", getStartDate()=" + getStartDate() + ", getEndDate()=" + getEndDate()
				+ ", getLowVelocity()=" + getLowVelocity() + ", getHighVelocity()=" + getHighVelocity()
				+ ", getSunlight()=" + getSunlight() + ", getWeather()=" + getWeather() + ", getConditions()="
				+ getConditions() + ", getLocation()=" + getLocation() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	

}
