package com.kodasfiledata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodasfiledata.entity.Area;
import com.kodasfiledata.service.AreaService;

@RestController
@RequestMapping("/rest/api/")
public class AreaController {
	@Autowired
	private AreaService service;
	
	@RequestMapping("areaList")
	public List<Area> getArea() {
		List<Area> list = service.getArea();
		
		return list;
	}

}
