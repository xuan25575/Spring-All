package com.example.config;

import java.io.IOException;

import com.example.pojo.User;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * JsonDeserializer
 * 反序列化的类
 *
 */
public class UserDeserializer extends JsonDeserializer<User> {

	@Override
	public User deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		//通过解码器反序列化一个树节点  返回一个根节点
		JsonNode node = parser.getCodec().readTree(parser);
		String userName = node.get("user-name").asText();
		User user = new User();
		user.setUserName(userName);
		return user;
	}
}
