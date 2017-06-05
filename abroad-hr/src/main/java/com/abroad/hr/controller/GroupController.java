package com.abroad.hr.controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.session.Session;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abroad.common.comn.ResponseJson;
import com.abroad.common.comn.StatusCode;
import com.abroad.common.comn.Tree;
import com.abroad.common.controller.BaseController;
import com.abroad.common.tools.TreeNodeUtil;
import com.abroad.security.shiro.AbroadSession;
import com.abroad.uc.domain.Group;
import com.abroad.uc.service.IGroupService;
import com.abroad.uc.vo.City;
import com.abroad.uc.vo.CountryRegion;
import com.abroad.uc.vo.CountryRegionTest;
import com.abroad.uc.vo.GroupVO;
import com.abroad.uc.vo.Location;
import com.abroad.uc.vo.State;

/**
* @ClassName: GroupController
* @Description: TODO(集团交互接口)
* @author: mengxr
* @date 2017年3月25日 下午3:48:07
*/
@RestController
@RequestMapping("/group")
public class GroupController extends BaseController<GroupVO>{
	

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IGroupService iGroupService;
	/**
	 * 此树只查到下一级
	* @Title: queryGroupTree
	* @Description: TODO(查询集团树结构)
	* @param @param id
	* @param @return    设定文件
	* @return ResponseJson    返回类型
	* @author: mengxr
	* @date 2017年3月27日 上午11:32:04
	* @throws
	*/
	@RequestMapping(value = "/queryGroupTree",method = RequestMethod.GET)
	public ResponseJson queryGroupTree(){
		ResponseJson responseJson = null;
		try {
			List<Group> groups = iGroupService.queryGroupChildren();
			List<Tree> groupTree = TreeNodeUtil
						.getfatherNode(TreeNodeUtil.reverseTree(groups));
			responseJson  = ResponseJson.getSuccessResponse();
			responseJson.setData(groupTree);
		} catch (Exception e) {
			responseJson = ResponseJson.getFailedResponse();
			logger.error("查询集团树结构发生异常:",e);
		}
		return responseJson;
	}
	/**
	* @Title: queryGroup
	* @Description: TODO(查询集团信息)
	* @param @param id
	* @param @return    设定文件
	* @return ResponseJson    返回类型
	* @author: mengxr
	* @date 2017年3月27日 下午3:46:16
	* @throws
	*/
	@RequestMapping(value = "/queryGroup",method = RequestMethod.GET)
	public ResponseJson queryGroup(@RequestParam("id") Long id){
		ResponseJson responseJson = null;
		try {
			Group group = iGroupService.queryGroup(id);
			responseJson  = ResponseJson.getSuccessResponse();
			responseJson.setData(group);
		} catch (Exception e) {
			responseJson  = ResponseJson.getFailedResponse();
			responseJson.setCode(StatusCode.ERROR.getCode());
			logger.error("查询集团ID为[{}]信息发生异常:",id,e);
		}
		return responseJson;
	}
	/**
	* @Title: insertGroup
	* @Description: TODO(添加集团)
	* @param @param group
	* @param @return    设定文件
	* @return ResponseJson    返回类型
	* @author: mengxr
	* @date 2017年3月27日 下午2:53:06
	* @throws
	*/
	@RequestMapping(value = "/insertGroup",method = RequestMethod.PUT)
	public ResponseJson insertGroup(GroupVO group){
		boolean flag = false;
		ResponseJson responseJson = new ResponseJson();
		try {
			group.setOpe(super.getInsertOpeInfo());
			flag = iGroupService.insertGroup(group);
			if(flag){
				responseJson = ResponseJson.getSuccessResponse();
			}else{
				responseJson = ResponseJson.getFailedResponse();
			}
		} catch (Exception e) {
			responseJson = ResponseJson.getFailedResponse();
			logger.error("添加名称为[{}]发生异常:",group.getName(),e);
		}
		return responseJson;
	}
	
	@RequestMapping(value = "/test",method = RequestMethod.GET)
	public ResponseJson test() throws IllegalAccessException, InvocationTargetException{
			  String xmlPath =  "D:/aa.xml";
			  Location xmlToBean = xmlToBean(xmlPath);
		 	  List<CountryRegionTest> countryRegionTests = new ArrayList<CountryRegionTest>();
		 	 List<CountryRegion> countryRegions = xmlToBean.getCountryRegions();
		 	 for(CountryRegion countryRegion: countryRegions){
		 		CountryRegionTest countryRegionTest =  new CountryRegionTest();
				countryRegionTest.setCountrycode(countryRegion.getCode());
				countryRegionTest.setNameTW(countryRegion.getName());
				countryRegionTests.add(countryRegionTest);
		 		List<State> states = countryRegion.getStates();
		 		for(State state : states){
		 			CountryRegionTest countryRegionTest1 =  new CountryRegionTest();
		 			BeanUtils.copyProperties(countryRegionTest1,countryRegionTest);
		 			countryRegionTest1.setStatecode(state.getCode());
		 			countryRegionTest1.setNameTW(state.getName());
		 			countryRegionTests.add(countryRegionTest1);
		 			List<City> citys = state.getCitys();
		 			for(City city : citys){
		 				CountryRegionTest countryRegionTest2 =  new CountryRegionTest();
		 				BeanUtils.copyProperties(countryRegionTest2,countryRegionTest1);
		 				countryRegionTest2.setCitycode(city.getCode());
		 				countryRegionTest2.setNameTW(city.getName());
		 				countryRegionTests.add(countryRegionTest2);
		 			}
		 		}
		 	 }
		 /*	List<CountryRegionTest> subList = countryRegionTests.subList(0, 2000);
		 	iCountryMapper.updateCountry(subList);
		 	countryRegionTests.subList(0, 2000).clear();
		 	iCountryMapper.updateCountry(countryRegionTests);*/
		 	//iCountryMapper.insertCountry(countryRegionTests);
		 /*	 for(CountryRegionTest tmp: countryRegionTests){
		 		 iCountryMapper.update(tmp);
		 	 }*/
		 	try {
		 		iGroupService.test(countryRegionTests);
			} catch (Exception e) {
				logger.error("捕获异常"+"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^",e);
				System.out.println("-------------------------------------------------");
			}
		return null;
	}
	 /**
     * xml文件配置转换为对象
     * @param xmlPath  xml文件路径
     * @param load    java对象.Class
     * @return    java对象
     * @throws JAXBException    
     * @throws IOException
     */
    public  Location xmlToBean(String xmlPath) {  //创建一个新的字符串
    	Location location = new Location();
    	 try {
			SAXReader reader = new SAXReader();
			  File file = new File(xmlPath);
			  Document document = reader.read(file);
			  Element root = document.getRootElement();	
			  List<Element> countryRegionEles = root.elements();
			  List<CountryRegion> countryRegions =  new ArrayList<CountryRegion>();
			  for(Element countryRegionEle : countryRegionEles){
				  CountryRegion countryRegion = new CountryRegion();
				  countryRegion.setName(getAttribute(countryRegionEle, "Name"));
				  countryRegion.setCode(getAttribute(countryRegionEle, "Code"));
				  List<Element> stateEles = countryRegionEle.elements();
				  List<State> states = new ArrayList<State>();
				  for(Element stateEle : stateEles){
					  State state = new State();
					  state.setName(getAttribute(stateEle, "Name"));
					  state.setCode(getAttribute(stateEle, "Code"));
					  List<Element> cityEles = stateEle.elements();
					  List<City> citys =  new ArrayList<City>();
					  for(Element cityEle : cityEles){
						  City city = new City();
						  city.setName(getAttribute(cityEle, "Name"));;
						  city.setCode(getAttribute(cityEle, "Code"));
						  citys.add(city);
					  }
					  state.setCitys(citys);
					  states.add(state);
				  }
				  countryRegion.setStates(states);
				  countryRegions.add(countryRegion);
			  }
			  location.setCountryRegions(countryRegions);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return location;
        }
    public static String getAttribute(Element ele,String name){
    	Attribute attribute = ele.attribute(name);
    	return attribute != null ? attribute.getValue() : null;
    }
	@RequestMapping(value = "/bbb",method = RequestMethod.GET)
	public ResponseJson bbb(){
		ResponseJson responseJson = new ResponseJson();
		AbroadSession abroadSession = new AbroadSession();
		//ShiroUser loginUserInfo = abroadSession.getLoginUserInfo();
		//String account = loginUserInfo.getAccount();
		Session session = abroadSession.getSession();
		Serializable id = session.getId();
		System.out.println("---------------------1--------------"+id);
		return responseJson;
	}
}
