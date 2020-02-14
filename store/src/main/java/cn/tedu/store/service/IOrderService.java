package cn.tedu.store.service;

import cn.tedu.store.entity.Order;

/**
 * 处理订单及订单商品数据的业务层接口
 */
public interface IOrderService {
	/**
	 * 创建订单
	 * @param aid 收货地址id
	 * @param cids 需要购买的商品在购物车中的数据id 
	 * @param uid 当前登录的用户的id
	 * @param username 成功创建的订单对象
	 * @return 返回成功创建的订单对象
	 */
	Order create(Integer aid,Integer[] cids,Integer uid,String username);

}
