package cn.tedu.store.controller;

import javax.servlet.http.HttpSession;

/**
 * 控制器类的基类
 *
 */
abstract class BaseController {
	/**
	 * 响应正确时使用的状态码
	 */
	public static final int OK=2000;
	
	/**
	 * 从Session中获取当前登录的用户名id
	 */
	protected final Integer getUidFromSession(HttpSession session) {
		return Integer.valueOf(session.getAttribute("uid").toString());
	}
	
	/**
	 * 从Session中获取当前登录的用户的用户名
	 */
	protected final String getUsernameFromSession(HttpSession session) {
		return session.getAttribute("username").toString();
	}
}
