package com.olx.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class LoginServiceDelegateImpl implements LoginServiceDelegate {

	@Autowired
	RestTemplate restTemplate;
	
	
	 @Bean
	 @LoadBalanced
	 public RestTemplate restTemplate() {
	        return new RestTemplate();
	  }
	
	
	@Override
	//@CircuitBreaker(name = "AUTHORIZATION-CIRCUIT-BREAKER", fallbackMethod = "fallBackAuthorization")
	public boolean isTokenValid(String authToken) {
		HttpHeaders httpheader = new HttpHeaders();
		httpheader.set("Authorization", authToken);
		HttpEntity entity =new HttpEntity(httpheader);
		ResponseEntity<Boolean> result = this.restTemplate.exchange("http://auth-service/olx/authentication/user/validate/token",HttpMethod.GET,entity, Boolean.class); 
		// TODO Auto-generated method stub
		return result.getBody();
	}
	
	public List<Map> fallBackAuthorization(Throwable ex){
		System.out.print("Master Call Failed : " + ex);
		return null;
	}
	
	
	   
}
