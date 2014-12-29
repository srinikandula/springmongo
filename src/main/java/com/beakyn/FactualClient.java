package com.beakyn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beakyn.SystemProperties.SysProps;
import com.factual.driver.Factual;
import com.factual.driver.Query;

@Service
public class FactualClient {
	@Autowired 
	private SystemProperties properties;
	
	public void initialize(){
		// Create an authenticated handle to Factual
		String key = properties.getProperty(SysProps.FACTUAL_API_KEY.getPropertyName());
		Factual factual = new Factual(key, 
				properties.getProperty(SysProps.FACTUAL_SECRET.getPropertyName()));
		System.out.println(
				  factual.fetch("places", new Query().limit(3)));
	}
}
