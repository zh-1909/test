package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.vo.CartVO;

/**
 *  处理购物车数据的业务层接口
 * @author tarena
 *
 */
public interface ICartService {
	/**
	 * 将商品添加到购物车
	 * @param uid 当前登录的用户的id
	 * @param username 当前登录的用户名
	 * @param pid 商品id
	 * @param amount 将商品添加到购物车中的数量
	 */
	void addToCart(Integer uid, String username, Integer pid, Integer amount);

	/**
	 * 查询某用户的购物车数据的列表
	 * @param uid 用户id
	 * @return 该用户的购物车数据的列表
	 */
	List<CartVO> getVOByUid(Integer uid);
	

	/**
	 * 根据多个数据id查询购物车数据的列表
	 * @param cids 多个购物车数据id
	 * @param uid 当前登录的用户的id
	 * @return 匹配的购物车数据的列表
	 */
	List<CartVO> getVOByCids(Integer[] cids,Integer uid);
	
	/**
	 * 根据购物车数据id查询购物车数据详情
	 * @param cid 购物车数据id
	 * @param uid 
	 * @param username
	 * @return
	 */
	Integer addNum(Integer cid,Integer uid, String username);
}