package com.shtku.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shtku.dao.ICommonDao;
import com.shtku.service.ICommonService;
@Service("commonService")
public class CommonServiceImpl implements ICommonService {
	
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;

	@Override
	public <T> void saveOrUpdate(T t) {
		commonDao.saveOrUpdate(t);
	}

}
