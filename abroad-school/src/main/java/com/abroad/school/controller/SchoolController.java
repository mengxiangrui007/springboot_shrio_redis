package com.abroad.school.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abroad.common.comn.ResponseJson;
import com.abroad.common.controller.BaseController;
import com.abroad.common.exception.AbroadException;
import com.abroad.common.exception.ServiceException;
import com.abroad.common.vo.ShiroUser;
import com.abroad.school.manager.SchoolManager;
import com.abroad.school.service.ISchoolOverviewService;
import com.abroad.school.service.ISchoolService;
import com.abroad.school.service.ISpecialtyService;
import com.abroad.school.vo.PageSchoolVo;

/**
 * 学校信息Controller
 * @author shichangjian
 *
 */
@Controller
//@RestController
@RequestMapping(value="/school")
public class SchoolController{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SchoolManager schoolManager;
	@Autowired
	private ISchoolService iSchoolService;
	@Autowired
	private ISchoolOverviewService iSchoolOverviewService;
	@Autowired
	private ISpecialtyService iSpecialtyService;
	@Autowired
	private MessageSource messageSource;
	/**
	 * 添加学校基本信息
	 * @param pageSchoolVo
	 * @return
	 * @throws JuntaiException
	 */
	@RequestMapping(value="/addSchool",method=RequestMethod.POST)
	public ResponseJson addSchool(@RequestBody PageSchoolVo pageSchoolVo ) throws AbroadException{
		schoolManager.addSchool(pageSchoolVo);
		
		return new ResponseJson();
	}
	/**
	 * 根据id查询学校基本信息
	 * @param id
	 * @return
	 * @throws JuntaiException
	 */
	@RequestMapping(value="/findSchool",method=RequestMethod.GET)
	public ResponseJson findSchool(@RequestParam("id") Long id) throws AbroadException{
		ResponseJson responseJson = new ResponseJson();
		
		responseJson.setData(iSchoolService.findSchool(id));
		
		return responseJson;
	}
	/**
	 * 根据学校id查询学校概述
	 * @param id
	 * @return
	 * @throws JuntaiException
	 */
	@RequestMapping(value="/findSchoolOverview",method=RequestMethod.GET)
	public ResponseJson findSchoolOverview(@RequestParam("schoolId") Long schoolId) throws AbroadException{
		ResponseJson responseJson = new ResponseJson();
		
		responseJson.setData(iSchoolOverviewService.findSchoolOverview(schoolId));
		
		return responseJson;
	}
	/**
	 * 根据学校id查询学校专业
	 * @param id
	 * @return
	 * @throws JuntaiException
	 */
	@RequestMapping(value="/findSpecialty",method=RequestMethod.GET)
	public ResponseJson findSpecialty(@RequestParam("schoolId") Long schoolId) throws AbroadException{
		ResponseJson responseJson = new ResponseJson();
		
		responseJson.setData(iSpecialtyService.findSpecialty(schoolId));
		
		return responseJson;
	}
	/**
	 * 修改学校信息
	 * @param pageSchoolVo
	 * @return
	 * @throws JuntaiException
	 */
	@RequestMapping(value="/updateSchool",method=RequestMethod.POST)
	public ResponseJson updateSchool(@RequestBody PageSchoolVo pageSchoolVo) throws AbroadException{
		
		schoolManager.updateSchool(pageSchoolVo);
		
		return new ResponseJson();
	}
	/**
	 * 删除学校信息
	 * @param id
	 * @return
	 * @throws JuntaiException
	 */
	@RequestMapping(value="/deleteSchool",method=RequestMethod.GET)
	public ResponseJson deleteSchool(@RequestParam("id") Long id) throws AbroadException{
		
		schoolManager.deleteSchool(id);
		
		return new ResponseJson();
	}
    @RequestMapping("/hello")
    public String hello(){

    	Locale locale = LocaleContextHolder.getLocale();
    	String msg = messageSource.getMessage("welcome", null,locale);
    	
    	
    	
    	
    	System.err.println(msg);
    	
           return "/hello";

    }
}
