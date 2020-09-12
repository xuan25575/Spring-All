package com.example.pojo;

import java.io.Serializable;
import java.util.Date;

import com.example.config.UserDeserializer;
import com.example.config.UserSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


/**
 *
 *
 *
 * @JsonSerialize 自己指定序列化器
 * @JsonDeserialize 自己指定反序列化器
 * 自己处理任意字段
 *
 * @JsonNaming 指定字段命名
 *
 *
 */
//@JsonIgnoreProperties({ "password", "age" })
//指定字段命名 userName -- > user_name
//@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
//@JsonSerialize(using = UserSerializer.class)
//@JsonDeserialize (using = UserDeserializer.class)
public class User implements Serializable {

	private static final long serialVersionUID = 6222176558369919436L;

	public interface UserNameView {
	};

	public interface AllUserFieldView extends UserNameView {
	};


	/**
	 *
	 *
	 * @JsonView使用方法：
	 *
	 * 　　1，使用接口来声明多个视图
	 * 　　2，在pojo的get方法上指定视图
	 * 　　3，在Controller方法上指定视图
	 *
	 *  @JsonView可以过滤pojo的属性，使Controller在返回json时候，
	 *  pojo某些属性不返回，比如User的密码，一般是不返回的，就可以使用这个注解。
	 *
	 *  通过控制视图对象控制是否展示视图
	 *   比如：
	 *   @JsonView(User.UserNameView.class)
	 *    @RequestMapping("get-user")
	 *    @ResponseBody
	 *    public User getUser() {
	 * 		User user = new User();
	 * 		user.setUserName("huang_2");
	 * 		user.setAge(26);
	 * 		user.setPassword("123456");
	 * 		user.setBirthday(new Date());
	 * 		return user;
	 *    }
	 *    只会展示 UserName，因为我指定了UserNameView
	 *    没有加视图注解的字段会展示，其他视图不会展示。
	 *
	 *    子视图会包含所有父子视
	 *    可以注解 描述，
	 *
	 */
	@JsonView(UserNameView.class)
	private String userName;
	
	@JsonView(AllUserFieldView.class)
	private int age;

	// @JsonIgnore
	@JsonView(AllUserFieldView.class)
	private String password;

	// @JsonProperty("bth")
	// @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonView(AllUserFieldView.class)
	private Date birthday;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAge() {
		return age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}
