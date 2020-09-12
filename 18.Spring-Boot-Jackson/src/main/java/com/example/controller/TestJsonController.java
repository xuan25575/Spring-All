package com.example.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.pojo.User;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class TestJsonController {

	@Autowired
	ObjectMapper mapper;


	@JsonView(User.UserNameView.class)
	@RequestMapping("get-user")
	@ResponseBody
	public User getUser() {
		User user = new User();
		user.setUserName("huang_2");
		user.setAge(26);
		user.setPassword("123456");
		user.setBirthday(new Date());
		return user;
	}

	@RequestMapping("serialization")
	@ResponseBody
	public String serialization() {
		try {
			User user = new User();
			user.setUserName("huang_2");
			user.setBirthday(new Date());
			String str = mapper.writeValueAsString(user);
			return str;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("deserialization")
	@ResponseBody
	public User deserialization(@RequestBody  User user) {
		System.out.println(user.getUserName());
		System.out.println(user.getAge());
		System.out.println(user.getPassword());
		return user;
	}

	@RequestMapping("read-json-string")
	@ResponseBody
	public String readJsonString() {
		try {
			String json = "{\"name\":\"xiao hei\",\"age\":26}";
			JsonNode node = this.mapper.readTree(json);
			String name = node.get("name").asText();
			int age = node.get("age").asInt();
			return name + " " + age;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("read-to-object")
	@ResponseBody
	public String readJsonAsObject() {
		try {
			String json = "{\"userName\":\"mrbird\"}";
			User user = mapper.readValue(json, User.class);
			String name = user.getUserName();
			return name;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("format-to-json")
	@ResponseBody
	public String formatObjectToJsonString() {
		try {
			User user = new User();
			user.setUserName("huang_2");
			user.setAge(24);
			user.setPassword("123456");
			user.setBirthday(new Date());
			String jsonStr = mapper.writeValueAsString(user);
			return jsonStr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("updateuser")
	@ResponseBody
	public int updateUser(@RequestBody List<User> list) {
		return list.size();
	}

	@RequestMapping("customize")
	@ResponseBody
	public String customize() throws JsonParseException, JsonMappingException, IOException {
		String jsonStr = "[{\"userName\":\"huang_2\",\"age\":24},{\"userName\":\"scott\",\"age\":29}]";
		JavaType type = mapper.getTypeFactory().constructParametricType(List.class, User.class);
		List<User> list = mapper.readValue(jsonStr, type);
		String msg = "";
		for (User user : list) {
			msg += user.getUserName();
		}
		return msg;
	}
}
