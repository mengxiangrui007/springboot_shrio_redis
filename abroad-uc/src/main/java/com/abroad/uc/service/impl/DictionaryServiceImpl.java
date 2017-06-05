package com.abroad.uc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abroad.common.service.impl.AbstractService;
import com.abroad.common.tools.Cacheable;
import com.abroad.uc.dao.IDictionaryDao;
import com.abroad.uc.domain.Dictionary;
import com.abroad.uc.service.IDictionaryService;

@Service
public class DictionaryServiceImpl extends AbstractService<Dictionary, Long> implements IDictionaryService{

	@Autowired
	private IDictionaryDao iDictionaryDao;
	
	
	@Override
	public void setBaseDao() {
		super.baseDao = iDictionaryDao;
	}
	/**
	 * 查询所有业务字典
	 */
	@Override
	public Dictionary findDictionary() {
		
		return null;
	}
	@Override
	@Cacheable(key="getDictionary",fieldKey="#code")
	public Dictionary findDictionary(Long code) {
		
		return iDictionaryDao.findById(code);
	}

	
}
