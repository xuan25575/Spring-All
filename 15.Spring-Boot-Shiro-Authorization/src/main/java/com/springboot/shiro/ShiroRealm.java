package com.springboot.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.springboot.dao.UserMapper;
import com.springboot.dao.UserPermissionMapper;
import com.springboot.dao.UserRoleMapper;
import com.springboot.pojo.Permission;
import com.springboot.pojo.Role;
import com.springboot.pojo.User;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private UserPermissionMapper userPermissionMapper;

	/**
	 * 获取用户角色和权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		String username = user.getUsername();

		System.out.println("用户" + username + "获取权限-----ShiroRealm.doGetAuthorizationInfo");
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

		// 获取用户角色集
		List<Role> roleList = userRoleMapper.findByUserName(username);
		Set<String> roleSet = new HashSet<String>();
		for (Role r : roleList) {
			roleSet.add(r.getName());
		}
		simpleAuthorizationInfo.setRoles(roleSet);

		// 获取用户权限集
		//配合@RequiresPermissions 使用
		List<Permission> permissionList = userPermissionMapper.findByUserName(username);
		Set<String> permissionSet = new HashSet<String>();
		for (Permission p : permissionList) {
			permissionSet.add(p.getUrlDesc());
		}
		// admin 拥有所有的权限 TODO

		simpleAuthorizationInfo.setStringPermissions(permissionSet);
		return simpleAuthorizationInfo;
	}

	/**
	 * 登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());

		System.out.println("用户" + userName + "认证-----ShiroRealm.doGetAuthenticationInfo");
		User user = userMapper.findByUserName(userName);

		if (user == null) {
			throw new UnknownAccountException("用户名或密码错误！");
		}
		if (!password.equals(user.getPassword())) {
			throw new IncorrectCredentialsException("用户名或密码错误！");
		}
		if (user.getStatus().equals("0")) {
			throw new LockedAccountException("账号已被锁定,请联系管理员！");
		}
		//  getName()->  realm name
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
		return info;
	}


//	/**
//	 * 设置 realm的 HashedCredentialsMatcher
//	 */
//	@PostConstruct
//	public void setHashedCredentialsMatcher() {
//		this.setCredentialsMatcher(hashedCredentialsMatcher());
//	}
//
//	/**
//	 * 凭证匹配 : 指定 加密算法,散列次数
//	 */
//	@Bean
//	public HashedCredentialsMatcher hashedCredentialsMatcher() {
//		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//		hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
//		hashedCredentialsMatcher.setHashIterations(1024);//散列的次数，比如散列两次，相当于 md5(md5(""));
//		return hashedCredentialsMatcher;
//	}

}
