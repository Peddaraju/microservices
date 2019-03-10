package com.example.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class MessageRestController {

	
	@Value("${msg:Hello world - Config Server is not working..pelase check}")
    private String msg;
	
	@GetMapping("/msg")
	public String msg() {
		return this.msg;
	}
}
