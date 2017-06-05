package com.abroad.uc.service;

import com.abroad.common.service.IBaseService;
import com.abroad.uc.domain.Dictionary;

public interface IDictionaryService extends IBaseService<Dictionary, Long>{

	/**
	 *	查询所有业务字典 
	 * @return
	 */
	public Dictionary findDictionary();
	/**
	 * 根据code查询字典详情
	 * @param code
	 * @return
	 */
	public Dictionary findDictionary(Long code);
}
