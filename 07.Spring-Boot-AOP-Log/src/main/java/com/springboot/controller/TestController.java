package com.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.annotation.Log;

@RestController
public class TestController {

	@Log("方法1")
	@GetMapping("/test1")
	public void test1(String name) {
		
	}

	@Log("方法2")
	@GetMapping("/test2")
	public void test2() throws InterruptedException {
		Thread.sleep(2000);
	}

	@Log("方法3")
	@GetMapping("/test3")
	public void test3(String name, String age) {
		
	}
}
