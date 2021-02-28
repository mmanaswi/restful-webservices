package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Country 
{
	private String name;
	private String code;
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

	
	public Country(String name, String code) {
		super();
		this.name = name;
		this.code = code;
		LOGGER.info("START");
		LOGGER.debug("Message  : ","Inside Country Constructor");
		LOGGER.info("END");
	}
	
	@Override
	public String toString() {
		return "Country [name=" + name + ", code=" + code + "]";
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	

}
