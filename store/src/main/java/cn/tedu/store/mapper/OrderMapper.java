package cn.tedu.store.mapper;

import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrederItem;

/**
 * 处理订单数据及订单商品数据持久层接口
 * @author tarena
 *
 */
public interface OrderMapper {
	/**
	 * 插入订单数据
	 * @param order
	 * @return
	 */
	Integer insertOrder(Order order);
	
	/**
	 * 插入订单商品数据
	 * @param orderItem
	 * @return
	 */
	Integer insertOrderItem(OrederItem orderItem);
}
