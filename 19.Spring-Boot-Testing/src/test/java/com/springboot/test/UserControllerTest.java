package com.springboot.test;

import javax.servlet.http.Cookie;

import com.springboot.test.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;




@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestApplication.class})
public class UserControllerTest {

	private MockMvc mockMvc;
	private MockHttpSession session;


	/**
	 * (1)@WebAppConfiguration：测试环境使用，用来表示测试环境使用的ApplicationContext
	 * 将是WebApplicationContext类型的；value指定web应用的根；
	 * (2)通过@Autowired WebApplicationContext wac：注入web环境的ApplicationContext容器；
	 * (3)然后通过MockMvcBuilders.webAppContextSetup(wac).build()创建一个MockMvc进行测试；
	 */
	@Autowired
    private WebApplicationContext wac;
	
	@Autowired
	ObjectMapper mapper;

	
	@Before
    public void setupMockMvc(){
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		session = new MockHttpSession();
		User user =new User();
		user.setUsername("Dopa");
		user.setPassword("ac3af72d9f95161a502fd326865c2f15");
	    session.setAttribute("user",user); 
    }

	/**
	 *
	 * mockMvc的 方法详解：
	 * perform：执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理；
	 * andExpect：添加ResultMatcher验证规则，验证控制器执行完成后结果是否正确；
	 * andDo：添加ResultHandler结果处理器，比如调试时打印结果到控制台；
	 * andReturn：最后返回相应的MvcResult；然后进行自定义验证/进行下一步的异步处理；
	 *
	 * @throws Exception
	 */
	@Test
	@Transactional
	public void test() throws Exception {
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.get("/user/{userName}", "huang2")
						.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.username").value("huang2"))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();

		System.out.println(mvcResult);


//		String jsonStr = "{\"username\":\"Dopa\",\"passwd\":\"ac3af72d9f95161a502fd326865c2f15\",\"status\":\"1\"}";
		
//		User user = new User();
//		user.setUsername("Dopa");
//		user.setPassword("ac3af72d9f95161a502fd326865c2f15");
//		user.setStatus("1");
//
//		String userJson = mapper.writeValueAsString(user);
		
		
//		mockMvc.perform(MockMvcRequestBuilders.post("/user/save").content(jsonStr.getBytes()));
		
//		mockMvc.perform(
//				MockMvcRequestBuilders.post("/user/save")
//				.contentType(MediaType.APPLICATION_JSON_UTF8)
//				.content(userJson.getBytes()))
//		.andExpect(MockMvcResultMatchers.status().isOk())
//		.andDo(MockMvcResultHandlers.print());
		
//		mockMvc.perform(MockMvcRequestBuilders.get("/hello?name={name}","mrbird"));
//		mockMvc.perform(MockMvcRequestBuilders.post("/user/{id}", 1));
//		mockMvc.perform(MockMvcRequestBuilders.fileUpload("/fileupload").file("file", "文件内容".getBytes("utf-8")));
//		mockMvc.perform(MockMvcRequestBuilders.get("/hello").param("message", "hello"));

//		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
//		params.add("name", "xiaohei");
//		params.add("hobby", "sleep");
//		params.add("hobby", "eat");
//		mockMvc.perform(MockMvcRequestBuilders.get("/hobby/save").params(params));
//		mockMvc.perform(MockMvcRequestBuilders.get("/index").sessionAttr(name, value));
//		mockMvc.perform(MockMvcRequestBuilders.get("/index").cookie(new Cookie(name, value)));
//		mockMvc.perform(MockMvcRequestBuilders.get("/index").contentType(MediaType.APPLICATION_JSON_UTF8));
//		mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 1).accept(MediaType.APPLICATION_JSON));
//		mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 1).header(name, values));
		
//		mockMvc.perform(MockMvcRequestBuilders.get("/index"))
//		.andDo(MockMvcResultHandlers.print());
		

	}
}
