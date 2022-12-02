package com.kodasfiledata.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodasfiledata.entity.Spatial;
import com.kodasfiledata.parameter.SpatialParameter;
import com.kodasfiledata.service.SpatialService;

@RestController
@RequestMapping("/rest/api/")
public class SpatialController {
		@Autowired
		private SpatialService service;
			
		@PostMapping("spatialList")
		public List<Spatial> getSpatialList(@ModelAttribute SpatialParameter param) {
			List<Spatial> list = service.getSpatialList(param);
			return list;
		}
}
