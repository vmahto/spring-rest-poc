package com.vm.rest.poc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.vm.rest.poc.exception.AppException;
import com.vm.rest.poc.model.User;
import com.vm.rest.poc.service.UserService;

@RestController
public class AppController {

	@Autowired
	UserService userService;
	
	@RequestMapping("/")
	public String hello() {

		return "Hello! This is Vikram.";
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUser() {
		
		List<User> users = userService.findAllUsers();
		if(users.isEmpty()){
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}
	
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
	//-------------------Create a User--------------------------------------------------------
	
		@RequestMapping(value = "/user/", method = RequestMethod.POST)
		public ResponseEntity<User> createUser(@RequestBody User user, 	UriComponentsBuilder ucBuilder) throws Exception {
			System.out.println("Creating User " + user.getName());

			if (userService.isUserExist(user)) {
				throw new AppException("user already exist");
//				System.out.println("A User with name " + user.getName() + " already exist");
//				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}

			userService.saveUser(user);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<User>(headers, HttpStatus.CREATED);
		}

	
	/*	@ExceptionHandler(value = Exception.class)
		public void handleException(Exception e){
			
			System.out.println("Exception Occured :: "+e.getMessage());
		}*/
}
