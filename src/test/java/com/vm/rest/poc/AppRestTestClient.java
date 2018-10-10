package com.vm.rest.poc;

import org.springframework.web.client.RestTemplate;


public class AppRestTestClient {
	
	public static final String REST_SERVICE_URI = "http://localhost:8080/Springrest/";

	public static void main(String[] args) {
		
		RestTemplate restTemplate = new RestTemplate();
		System.out.println(restTemplate.getForObject(REST_SERVICE_URI, String.class));
	}
}