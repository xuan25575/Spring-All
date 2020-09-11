package com.springboot.listener;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

public class ShiroSessionListener implements SessionListener{

	private final AtomicInteger sessionCount = new AtomicInteger(0);

	/**
	 *
	 * 会话创建时触发.
	 * @param session
	 */
	@Override
	public void onStart(Session session) {
		//会话创建，在线人数加一
		sessionCount.incrementAndGet();
	}

	/**
	 * 退出会话时触发
	 * @param session
	 */
	@Override
	public void onStop(Session session) {
		//会话退出,在线人数减一
		sessionCount.decrementAndGet();
		
	}

	/**
	 *
	 * 会话过期时触发
	 * @param session
	 */
	@Override
	public void onExpiration(Session session) {
		//会话过期,在线人数减一
		sessionCount.decrementAndGet();
	}

	/**
	 *
	 * 在线人数.
	 * @return
	 */
	public AtomicInteger getSessionCount() {
		return sessionCount;
	}
}
