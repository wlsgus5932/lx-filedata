package com.kodasfiledata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodasfiledata.entity.Learning;
import com.kodasfiledata.parameter.LearningParameter;
import com.kodasfiledata.service.LearningService;

	
@RestController
@RequestMapping("/rest/api/")
public class LearningController {
		@Autowired
		private LearningService service;
		
		@PostMapping("learningList")
		public List<Learning> getLearning(@RequestBody LearningParameter param) {
			System.out.println(param);
			List<Learning> list = service.getLearningList(param);
			if(list == null || list.isEmpty()) {
				return null;
			} else {
				return list;
			}
		}
		
		@PostMapping("learningSectionList")
		public List<Learning> getSection(@RequestBody LearningParameter param) {
			System.out.println(param);
			List<Learning> list = service.getLearningSectionList(param);
			if(list == null || list.isEmpty()) {
				return null;
			} else {
				return list;
			}
		}
		
		@PostMapping("learningRandomList")
		public List<Learning> getRandom(@RequestBody LearningParameter param) {
			System.out.println(param);
			List<Learning> list = service.getLearningRandomList(param);
			if(list == null || list.isEmpty()) {
				return null;
			} else {
				return list;
			}
		}

}
