package com.kodasfiledata.service;

import java.util.List;


import com.kodasfiledata.entity.Learning;
import com.kodasfiledata.parameter.LearningParameter;

public interface LearningService {
	public List<Learning> getLearningList(LearningParameter param);

	public int getLearningListTotal(LearningParameter param);

}
