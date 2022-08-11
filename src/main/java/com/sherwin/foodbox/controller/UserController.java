package com.sherwin.foodbox.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/test")
	public String test() {
		return "test file";
	}
}
