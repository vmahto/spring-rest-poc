package com.vm.rest.poc;

import java.net.URI;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.vm.rest.poc.model.User;


public class AppRestTestClient {
	
	public static final String REST_SERVICE_URI = "http://localhost:8080/Springrest/";
	private static RestTemplate restTemplate = new RestTemplate();
	
	public static void main(String[] args) {
		
		System.out.println(restTemplate.getForObject(REST_SERVICE_URI, String.class));
//		testAllUser();
//		createUser();
		updateUser();
	}
	
	private static void testAllUser(){
		System.out.println(restTemplate.getForObject(REST_SERVICE_URI+"users", List.class));
	}
	
	/* POST */
    private static void createUser() {
		System.out.println("Testing create User API----------");
    	RestTemplate restTemplate = new RestTemplate();
        User user = new User(0,"Sarah",51,134);
        
       Object obj= restTemplate.postForObject(REST_SERVICE_URI+"/user/",user, User.class);
        /*URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/user/", user, User.class);
        if(uri != null)
        System.out.println("Location : "+uri.toASCIIString());*/
       if(obj != null)
       System.out.println(obj.toString());
    }
    
    /* PUT */
    private static void updateUser() {
		System.out.println("Testing update User API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user  = new User();
        user.setId(1);//1,"Tomy",33, 70000
        
        restTemplate.put(REST_SERVICE_URI+"/user/1", user);
        System.out.println(user);
    }
    
    /* PUT */
    private static void updateUserUsingPatch() {
		System.out.println("Testing update User API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user  = new User();
        user.setName("Vikram");//1,"Tomy",33, 70000
        System.out.println(user);
    }

}