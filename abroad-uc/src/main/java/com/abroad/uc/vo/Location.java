package com.abroad.uc.vo;

import java.util.List;

public class Location {
	private String Name;
	private String Code;
	private List<CountryRegion> countryRegions;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public List<CountryRegion> getCountryRegions() {
		return countryRegions;
	}
	public void setCountryRegions(List<CountryRegion> countryRegions) {
		this.countryRegions = countryRegions;
	}
}
