package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bean.BlogProperties;
import com.springboot.bean.ConfigBean;
import com.springboot.bean.TestConfigBean;


@RestController
public class IndexController {
	@Autowired
	private BlogProperties blogProperties;
	@Autowired
	private ConfigBean configBean;
	@Autowired
	private TestConfigBean testConfigBean;


	//测试配置文件的读取。
	@RequestMapping("/")
	public String index() {
		System.out.println(configBean.getName()+":"+configBean.getTitle());
		System.out.println(blogProperties.getName()+":" + blogProperties.getTitle());
		return testConfigBean.getName()+"，"+testConfigBean.getAge();
	}
}
