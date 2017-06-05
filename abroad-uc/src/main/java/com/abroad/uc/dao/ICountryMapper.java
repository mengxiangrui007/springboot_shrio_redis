package com.abroad.uc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.abroad.common.exception.DaoException;
import com.abroad.uc.vo.CountryRegionTest;

public interface ICountryMapper{
	public void insertCountry(@Param("countryRegionTests") List<CountryRegionTest> countryRegionTests);
	public void updateCountry(@Param("countryRegionTests") List<CountryRegionTest> countryRegionTests);
	public void update(CountryRegionTest countryRegionTest);
}
