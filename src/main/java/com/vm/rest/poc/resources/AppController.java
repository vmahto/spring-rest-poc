package com.vm.rest.poc.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

	@RequestMapping("/")
	public String hello() {

		return "Hello! This is Vikram.";
	}
}
