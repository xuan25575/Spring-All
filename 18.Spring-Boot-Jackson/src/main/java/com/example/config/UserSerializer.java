package com.example.config;

import java.io.IOException;

import com.example.pojo.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * JsonSerializer 序列化指定的对象
 *
 */
public class UserSerializer extends JsonSerializer<User> {

	@Override
	public void serialize(User user, JsonGenerator generator, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		generator.writeStartObject(); //代表 json 的开头标志 {
		//generator.writeStartObject("hello"); // 设置 generator.getCurrentValue().toString()
		generator.writeStringField("user-name",user.getUserName());
		generator.writeEndObject(); //代表 json 的结束标志 }
	}
}
