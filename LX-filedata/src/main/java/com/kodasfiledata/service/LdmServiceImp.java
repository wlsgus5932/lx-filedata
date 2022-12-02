package com.kodasfiledata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodasfiledata.dao.LdmDao;
import com.kodasfiledata.entity.Ldm;

@Service
public class LdmServiceImp implements LdmService {
	
	@Autowired
	private LdmDao dao;
	
	@Override
	public List<Ldm> getLdmList() {
		return dao.getLdmList();
	}

}
