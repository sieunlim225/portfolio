package com.portfolio.project.mvc.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.portfolio.project.mvc.service.TestService;

@Controller
public class TestController {

	@Autowired
	private TestService testService;
	
	@Autowired
	private MessageSource messages;
	
	 public void setMessages(MessageSource messages) {
	        this.messages = messages;
	    }
	
	@GetMapping("/test")
	public String test() {
		String a = "test중입니다";
		 testService.testing(a);
		 System.out.println(messages.getMessage("errors.test", null, Locale.KOREA));
		return "home";
	}
}
