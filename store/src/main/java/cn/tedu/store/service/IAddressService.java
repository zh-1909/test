package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Address;

/**
 * 处理收货地址数据的业务层接口
 * @author tarena
 *
 */
public interface IAddressService {	
	/**
	 * 新增收货地址
	 * @param uid 用户的id
	 * @param username 用户名
	 * @param address 收货地址数据
	 */
	void addnew(Integer uid,String username,Address address); 

	/**
	 * 查询用户收货地址
	 * @param uid 用户的id
	 * @return 返回查询的收货地址信息
	 */
	List<Address> getByUid(Integer uid);
	
	/**
	 * 设置默认收货地址
	 * @param aid 将要设置为默认收货地址的数据id
	 * @param uid 当前登录的用户id
	 * @param username 当前登录的用户名
	 */
	void setDefault(Integer aid,Integer uid,String username);
		
	/**
	 * 删除收货地址
	 * @param aid 将要删除的收货地址的数据id
	 * @param uid 当前登录的用户id
	 * @param username 当前登录的用户名
	 */
	void delete(Integer aid,Integer uid,String username);
	
	/**
	 * 
	 * @param aid
	 * @param uid
	 * @return
	 */
	Address getByAid(Integer aid,Integer uid);


}

















