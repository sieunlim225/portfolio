package com.portfolio.project.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.portfolio.project.mvc.service.TestService;

@Controller
public class TestController {

	@Autowired
	private TestService testService;
	
	@GetMapping("/test")
	public String test() {
		String a = "test중입니다";
		 testService.testing(a);
		return "home";
	}
}
