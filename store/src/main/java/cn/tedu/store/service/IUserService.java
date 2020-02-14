package cn.tedu.store.service;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.User;
/**
 * 用户业务持久层接口
 * @author tarena
 *
 */
public interface IUserService {
	/**
	 * 用户注册
	 * @param user 用户数据
	 */
	void reg(User user);
	
	/**
	 * 用户登录
	 * @param username 用户名
	 * @return
	 */
	User login(String username,String password);
	
	/**
	 * 用户密码更新
	 * @param uid 用户id
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @param username 修改用户名
	 */
	void changePassword(Integer uid, String oldPassword, String newPassword, String username);
	
	/**
	 * 显示当前用户的基本信息
	 * 
	 * @param uid 用户名uid
	 * @return 显示当前用户的基本信息
	 */
	User showInfo(Integer uid);
	
	/**
	 * 更新用户信息
	 * @param uid
	 * @param username
	 * @param user
	 */
	void changeInfo(Integer uid,String username,User user);
	
	/**
	 * 修改头像
	 * @param uid 用户id
	 * @param avatar 头像
	 * @param username 修改用户名
	 */
	void changeAvatar(Integer uid, String avatar, String username);
	
}






















