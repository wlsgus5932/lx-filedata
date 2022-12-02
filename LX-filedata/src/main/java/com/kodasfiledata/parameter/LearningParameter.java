package com.kodasfiledata.parameter;

import java.util.ArrayList;
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
	private List<String> environment2 = new ArrayList<>();
	private String location;
	private String person;
	private String car;
	private String truck;
	private String bus;
	private String van;
	private String motorCycle;
	private String specialCar;
	private String senTp;
	private String pstnNo;
	private int fileId;
	private int randomSearchCnt;
	private Boolean isSector;
	private String stopLine;
	private String crossWalk;
	private String traffic;
	private String construction;
	private String bump;
	private int page;
	private int countNumber;
	
	
	
	@Override
	public String toString() {
		return "LearningParameter [startDate=" + startDate + ", endDate=" + endDate + ", learningType=" + learningType
				+ ", lowVelocity=" + lowVelocity + ", highVelocity=" + highVelocity + ", sunlight=" + sunlight
				+ ", weather=" + weather + ", conditions=" + conditions + ", environment1=" + environment1
				+ ", environment2=" + environment2 + ", location=" + location + ", person=" + person + ", car=" + car
				+ ", truck=" + truck + ", bus=" + bus + ", van=" + van + ", motorCycle=" + motorCycle + ", specialCar="
				+ specialCar + ", senTp=" + senTp + ", pstnNo=" + pstnNo + ", fileId=" + fileId + ", randomSearchCnt="
				+ randomSearchCnt + ", isSector=" + isSector + ", stopLine=" + stopLine + ", crossWalk=" + crossWalk
				+ ", traffic=" + traffic + ", construction=" + construction + ", bump=" + bump + ", page=" + page
				+ ", countNumber=" + countNumber + "]";
	}
	
	
	public String getLearningType2() {
		return learningType2;
	}


	public void setLearningType2(String learningType2) {
		this.learningType2 = learningType2;
	}


	public String getEnvironment1() {
		return environment1;
	}
	public void setEnvironment1(String environment1) {
		this.environment1 = environment1;
	}
	public List<String> getEnvironment2() {
		return environment2;
	}
	public void setEnvironment2(List<String> environment2) {
		this.environment2 = environment2;
	}
	public int getCountNumber() {
		return countNumber;
	}
	public void setCountNumber(int countNumber) {
		this.countNumber = countNumber;
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
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getCar() {
		return car;
	}
	public void setCar(String car) {
		this.car = car;
	}
	public String getTruck() {
		return truck;
	}
	public void setTruck(String truck) {
		this.truck = truck;
	}
	public String getBus() {
		return bus;
	}
	public void setBus(String bus) {
		this.bus = bus;
	}
	public String getVan() {
		return van;
	}
	public void setVan(String van) {
		this.van = van;
	}
	public String getMotorCycle() {
		return motorCycle;
	}
	public void setMotorCycle(String motorCycle) {
		this.motorCycle = motorCycle;
	}
	public String getSpecialCar() {
		return specialCar;
	}
	public void setSpecialCar(String specialCar) {
		this.specialCar = specialCar;
	}
	public String getSenTp() {
		return senTp;
	}
	public void setSenTp(String senTp) {
		this.senTp = senTp;
	}
	public String getPstnNo() {
		return pstnNo;
	}
	public void setPstnNo(String pstnNo) {
		this.pstnNo = pstnNo;
	}
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public int getRandomSearchCnt() {
		return randomSearchCnt;
	}
	public void setRandomSearchCnt(int randomSearchCnt) {
		this.randomSearchCnt = randomSearchCnt;
	}
	public Boolean getIsSector() {
		return isSector;
	}
	public void setIsSector(Boolean isSector) {
		this.isSector = isSector;
	}
	public String getStopLine() {
		return stopLine;
	}
	public void setStopLine(String stopLine) {
		this.stopLine = stopLine;
	}
	public String getCrossWalk() {
		return crossWalk;
	}
	public void setCrossWalk(String crossWalk) {
		this.crossWalk = crossWalk;
	}
	public String getTraffic() {
		return traffic;
	}
	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}
	public String getConstruction() {
		return construction;
	}
	public void setConstruction(String construction) {
		this.construction = construction;
	}
	public String getBump() {
		return bump;
	}
	public void setBump(String bump) {
		this.bump = bump;
	}
	public String getLearningType() {
		return learningType;
	}
	public void setLearningType(String learningType) {
		this.learningType = learningType;
	}

}
