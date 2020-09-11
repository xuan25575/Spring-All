package com.springboot.config;

import java.util.LinkedHashMap;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springboot.shiro.ShiroRealm;

/**
 * Shiro提供了记住我（RememberMe）的功能，比如访问如淘宝等一些网站时，
 * 关闭了浏览器下次再打开时还是能记住你是谁，下次访问时无需再登录即可访问，基本流程如下：
 *
 * 1、首先在登录页面选中RememberMe然后登录成功；如果是浏览器登录，一般会把RememberMe的Cookie写到客户端并保存下来；
 * 2、关闭浏览器再重新打开；会发现浏览器还是记住你的；
 * 3、访问一般的网页服务器端还是知道你是谁，且能正常访问；
 * 4、但是比如我们访问淘宝时，如果要查看我的订单或进行支付时，此时还是需要再进行身份认证的，以确保当前用户还是你。
 *
 */
@Configuration
public class ShiroConfig {
	
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setSuccessUrl("/index");
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		
		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/fonts/**", "anon");
		filterChainDefinitionMap.put("/img/**", "anon");
		filterChainDefinitionMap.put("/druid/**", "anon");
		filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/", "anon");
		//表示访问该地址的用户是身份验证通过或RememberMe登录的都可以。
		filterChainDefinitionMap.put("/**", "user");
		
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		
		return shiroFilterFactoryBean;
	}
 
	@Bean  
    public SecurityManager securityManager(){  
       DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
       securityManager.setRealm(shiroRealm());
       securityManager.setRememberMeManager(rememberMeManager());
       return securityManager;  
    }  
	
	@Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
	
	@Bean  
    public ShiroRealm shiroRealm(){  
       ShiroRealm shiroRealm = new ShiroRealm();  
       return shiroRealm;  
    }  
	
	/**
	 * cookie对象
	 * @return
	 */
	public SimpleCookie rememberMeCookie() {
		// 设置cookie名称，对应login.html页面的<input type="checkbox" name="rememberMe"/>
		SimpleCookie cookie = new SimpleCookie("rememberMe");
		// 设置cookie的过期时间，单位为秒，这里为一天
		cookie.setMaxAge(86400);
		cookie.setHttpOnly(true);
		return cookie;
	}
	
	/**
	 * cookie管理对象
	 * @return
	 */
	public CookieRememberMeManager rememberMeManager() {
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberMeCookie());
		// rememberMe cookie加密的密钥
		// cipherKey 是加密rememberMe Cookie的密钥 不能瞎改
		// 默认AES算法；
		cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
		return cookieRememberMeManager;
	}
	
}
