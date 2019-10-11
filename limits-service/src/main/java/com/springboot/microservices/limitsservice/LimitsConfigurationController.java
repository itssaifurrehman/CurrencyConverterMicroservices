package com.springboot.microservices.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springboot.microservices.limitsservice.bean.LimitsConfiguration;

@RestController
public class LimitsConfigurationController {
	
	@Autowired
	private 	Configuration configuration;
	
	@GetMapping("/limits")
	public LimitsConfiguration retrieveLimitsFromConfiguration() {
		return new LimitsConfiguration(configuration.getMaximum(),configuration.getMinimun());
		
		
	}
	
	@GetMapping("/fault-tolerance-example")
	@HystrixCommand(fallbackMethod ="fallbackRetrieveConfiguration" )
	public LimitsConfiguration retrieveConfiguration() {
		throw new RuntimeException("Not Available BRavo");
		
	}
	
	public LimitsConfiguration fallbackRetrieveConfiguration() {
		return new LimitsConfiguration(9,999);
	}

}
