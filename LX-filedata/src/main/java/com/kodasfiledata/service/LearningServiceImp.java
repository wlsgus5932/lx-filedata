package com.kodasfiledata.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodasfiledata.dao.LearningDao;
import com.kodasfiledata.entity.Learning;
import com.kodasfiledata.parameter.LearningParameter;

@Service
public class LearningServiceImp implements LearningService {
	@Autowired
	private LearningDao dao;


	@Override
	public List<Learning> getLearningList(LearningParameter param) {
		return dao.getLearningList(param);
	}
	@Override
	public List<Learning> getLearningSectionList(LearningParameter param) {
		return dao.getLearningSectionList(param);
	}
	@Override
	public List<Learning> getLearningRandomList(LearningParameter param) {
		return dao.getLearningRandomList(param);
	}
}
