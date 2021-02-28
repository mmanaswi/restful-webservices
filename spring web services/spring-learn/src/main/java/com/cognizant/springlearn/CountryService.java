package com.cognizant.springlearn;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.cognizant.springlearn.model.Country;

@Service
public class CountryService 
{
	public Country getCountry(String code)
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("country.xml");
		List<Country> list=(List<Country>)context.getBean("countryList");
		Country ans=null;
		for(Country c:list)
		{
			if(c.getCode().equals(code))
				ans=c;
		}
		return ans!=null?ans:new Country("Not found","404");
	}

}
