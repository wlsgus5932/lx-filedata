package com.kodasfiledata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodasfiledata.entity.Ldm;
import com.kodasfiledata.service.LdmService;

@RestController
@RequestMapping("/rest/api/")
public class LdmController {
	
	@Autowired
	private LdmService service;
	
	@GetMapping("ldmList")
	public List<Ldm> getLearning() {
		List<Ldm> list = service.getLdmList();
		return list;
	}

}
