package com.springboot.config;

import java.util.LinkedHashMap;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.springboot.shiro.ShiroRealm;

@Configuration
public class ShiroConfig {

	/**
	 * 配置 资源访问策略 . web应用程序 shiro核心过滤器配置
	 *
	 * @param securityManager securityManager
	 * @return
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setSuccessUrl("/index");
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");


		/**
		 * 	配置 拦截过滤器链.  map的键 : 资源地址 ;  map的值 : 所有默认Shiro过滤器实例名
		 * 	默认Shiro过滤器实例 参考 : {@link org.apache.shiro.web.filter.mgt.DefaultFilter}
		 */
		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/fonts/**", "anon");
		filterChainDefinitionMap.put("/img/**", "anon");
		filterChainDefinitionMap.put("/druid/**", "anon");
		//配置登出页,shiro已经帮我们实现了跳转
		filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/", "anon");
		filterChainDefinitionMap.put("/**", "user");
		//filterChainDefinitionMap.put("/**", "authc");          //所有资源都需要经过验证

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		return shiroFilterFactoryBean;
	}

	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(shiroRealm());
		securityManager.setRememberMeManager(rememberMeManager());
		return securityManager;
	}


	@Bean
	public ShiroRealm shiroRealm() {
		ShiroRealm shiroRealm = new ShiroRealm();
		return shiroRealm;
	}

	public SimpleCookie rememberMeCookie() {
		SimpleCookie cookie = new SimpleCookie("rememberMe");
		cookie.setMaxAge(86400);
		cookie.setHttpOnly(true);
		return cookie;
	}

	public CookieRememberMeManager rememberMeManager() {
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberMeCookie());
		cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
		return cookieRememberMeManager;
	}

	/**
	 * 开启shiro 注解支持. 使以下注解能够生效 :
	 * 需要认证 {@link org.apache.shiro.authz.annotation.RequiresAuthentication RequiresAuthentication}
	 * 需要用户 {@link org.apache.shiro.authz.annotation.RequiresUser RequiresUser}
	 * 需要访客 {@link org.apache.shiro.authz.annotation.RequiresGuest RequiresGuest}
	 * 需要角色 {@link org.apache.shiro.authz.annotation.RequiresRoles RequiresRoles}
	 * 需要权限 {@link org.apache.shiro.authz.annotation.RequiresPermissions RequiresPermissions}
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	/**
	 * https://www.jianshu.com/p/3716ee8e3adc
	 * 解决权限注解不生效问题
	 * @return
	 */
	@Bean
	public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		return new DefaultAdvisorAutoProxyCreator();
	}
}