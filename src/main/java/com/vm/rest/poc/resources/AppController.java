package com.vm.rest.poc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
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
		
		
		//-------------------Retrieve Single User--------------------------------------------------------
		
		@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<User> getUser(@PathVariable("id") long id) {
			System.out.println("Fetching User with id " + id);
			User user = userService.findById(id);
			if (user == null) {
				System.out.println("User with id " + id + " not found");
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}

		//------------------- Update a User --------------------------------------------------------
		
		@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
		public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
			System.out.println("Updating User " + id);
			
			User currentUser = userService.findById(id);
			
			if (currentUser==null) {
				System.out.println("User with id " + id + " not found");
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}

			currentUser.setName(user.getName());
			currentUser.setAge(user.getAge());
			currentUser.setSalary(user.getSalary());
			
			userService.updateUser(currentUser);
			return new ResponseEntity<User>(currentUser, HttpStatus.OK);
		}
	//------------------- Update a User --------------------------------------------------------
		
		@RequestMapping(value = "/user/{id}", method = RequestMethod.PATCH)
		public ResponseEntity<User> updateUserAttribute(@PathVariable("id") long id, @RequestBody User user) {
			System.out.println("Updating User " + id);
			
			User currentUser = userService.findById(id);
			
			if (currentUser==null) {
				System.out.println("User with id " + id + " not found");
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}

			if(user.getName() != null){
				currentUser.setName(user.getName());
			}
			if(user.getAge() != 0){
				currentUser.setAge(user.getAge());
			}
			if(user.getSalary() != 0 ){
				currentUser.setSalary(user.getSalary());
			}
			
			userService.updateUser(currentUser);
			return new ResponseEntity<User>(currentUser, HttpStatus.OK);
		}
		

	/*	@ExceptionHandler(value = Exception.class)
		public void handleException(Exception e){
			
			System.out.println("Exception Occured :: "+e.getMessage());
		}*/
}
