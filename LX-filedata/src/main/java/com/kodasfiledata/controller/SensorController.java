package com.kodasfiledata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodasfiledata.entity.Sensor;
import com.kodasfiledata.parameter.SensorParameter;
import com.kodasfiledata.service.SensorService;

@RestController
@RequestMapping("/rest/api/")
public class SensorController {
	@Autowired
	private SensorService service;
	
	
	@PostMapping("sensorList")
	public List<Sensor> getSensor(@RequestBody SensorParameter param) {
		System.out.println(param.toString());
		List<Sensor> list = service.getSensorList(param);
		System.out.println(list.toString());
		System.out.println("springboot test");
		if(list == null || list.isEmpty()) {
			return null;
		} else {
			return list;
		}
	}
	
	@PostMapping("sensorSectionList")
	public List<Sensor> getSection(@RequestBody SensorParameter param) {
		System.out.println(param.toString());
		List<Sensor> list = service.getSensorSectionList(param);
		System.out.println(list.toString());
		if(list == null || list.isEmpty()) {
			return null;
		} else {
			return list;
		}
	}
	
	@PostMapping("sensorRandomList")
	public List<Sensor> getRandom(@RequestBody SensorParameter param) {
		System.out.println(param.toString());
		List<Sensor> list = service.getSensorRandomList(param);
		System.out.println(list.toString());
		if(list == null || list.isEmpty()) {
			return null;
		} else {
			return list;
		}
	}
	
}
