package com.abroad.hr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abroad.common.comn.ResponseJson;
import com.abroad.common.exception.AbroadException;
import com.abroad.hr.domain.CompanyBase;
import com.abroad.hr.service.ICompanyBaseService;

@RestController
@RequestMapping("/companyBase")
public class CompanyBaseController{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ICompanyBaseService iCompanyBaseService;
	/**
	* @Title: addCompanyBase
	* @Description: TODO(添加企业级用户信息)
	* @param @param companyBase
	* @param @return
	* @param @throws AbroadException    设定文件
	* @return ResponseJson    返回类型
	* @author: shicj
	* @date 2017年4月19日 下午5:34:03
	* @throws
	*/
	@RequestMapping(value="/addCompanyBase",method=RequestMethod.POST)
	public ResponseJson addCompanyBase(@RequestBody CompanyBase companyBase) throws AbroadException{
		iCompanyBaseService.addCompanyBase(companyBase);
		
		return new ResponseJson();
	}
	/**
	* @Title: updateCompanuBase
	* @Description: TODO(企业级用户)
	* @param @param companyBase
	* @param @return
	* @param @throws AbroadException    设定文件
	* @return ResponseJson    返回类型
	* @author: shicj
	* @date 2017年4月19日 下午5:36:53
	* @throws
	*/
	@RequestMapping(value="/updateCompanuBase",method=RequestMethod.POST)
	public ResponseJson updateCompanuBase(@RequestBody CompanyBase companyBase) throws AbroadException{
		iCompanyBaseService.updateCompanyBase(companyBase);
		
		return new ResponseJson();
	}
	/**
	* @Title: findCompanyBase
	* @Description: TODO(查询企业级信息)
	* @param @param id
	* @param @return
	* @param @throws AbroadException    设定文件
	* @return ResponseJson    返回类型
	* @author: shicj
	* @date 2017年4月19日 下午5:38:58
	* @throws
	*/
	@RequestMapping(value="/findCompanyBase",method=RequestMethod.GET)
	public ResponseJson findCompanyBase(@RequestParam("id") Long id) throws AbroadException{
		ResponseJson responseJson = new ResponseJson();
		responseJson.setData(iCompanyBaseService.findCompanyBase(id));
		
		return responseJson;
	}
	/**
	* @Title: deleteCompanyBase
	* @Description: TODO(删除企业级信息)
	* @param @param id
	* @param @return
	* @param @throws AbroadException    设定文件
	* @return ResponseJson    返回类型
	* @author: shicj
	* @date 2017年4月19日 下午5:41:45
	* @throws
	*/
	@RequestMapping(value="/deleteCompanyBase",method=RequestMethod.GET)
	public ResponseJson deleteCompanyBase(@RequestParam("id") Long id) throws AbroadException{
		iCompanyBaseService.deleteCompanyBase(id);
		
		return new ResponseJson();
	}
}
