package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrederItem;
import cn.tedu.store.mapper.OrderMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.service.IOrderService;
import cn.tedu.store.vo.CartVO;
@Service
public class OrderServiceImpl implements IOrderService {
	@Autowired
	private OrderMapper orderMapper;
	@Autowired private
	IAddressService addressService;
	@Autowired
	private ICartService cartService;
	@Override
	public Order create(Integer aid, Integer[] cids, Integer uid, String username) {
		// 创建当前时间对象now
		Date now = new Date();
		// 根据参数cids调用cartService的List<CartVO> getVOByCids(Integer[] cids, Integer uid)方法查询购物车中的数据，得到List<CartVO>类型的结果
		List<CartVO> list = cartService.getVOByCids(cids, uid);
		// 声明totalPrice表示总价
		Long totalPrice = 0l;
		// 遍历以上查询结果，在遍历过程中，累加商品单价乘以数量的结果
		for (CartVO cartVO : list) {
			totalPrice+=cartVO.getPrice()*cartVO.getNum();
		}
		// 根据参数aid调用addressService的Address getByAid(Integer aid)方法查询收货地址数据
		Address address = addressService.getByAid(aid, uid);
		// 创建Order对象
		Order order = new Order();
		// 补全属性：uid
		order.setUid(address.getUid());
		// 补全属性：收货地址相关的6个属性
//		private String recvName; //"收货人",
		order.setRecvName(address.getReceiver());
//		private String recvPhone;	//"收货电话",
		order.setRecvPhone(address.getPhone());
//		private String recvProvince;	//"收货省",
		order.setRecvProvince(address.getProvinceName());
//		private String recvCity; //"收货市",
		order.setRecvCity(address.getCityName());
//		private String recvArea;//"收货区",
		order.setRecvArea(address.getAreaName());
//		private String recvAddress; //"收货详细地址",
		order.setRecvAddress(address.getAddress());
		// 补全属性：price -> totalPrice
		order.setPrice(totalPrice);
		// 补全属性：order_time -> now
		order.setOrderTime(now);
		// 补全属性：pay_time -> null
		order.setPayTime(null);
		// 补全属性：status -> 0
		order.setStatus(0);
		// 补全属性：4个日志
		order.setModifiedTime(now);
		order.setModifiedUser(username);
		order.setCreatedTime(now);
		order.setCreatedUser(username);
		// 插入订单数据：insertOrder(order);
		insertOrder(order);
		// 遍历查询到的List<CartVO>对象
		for (CartVO cart : list) {
			 
			// -- 创建OrderItem对象
			OrederItem item = new OrederItem();
			// -- 补全属性：oid -> order.getOid();
			item.setOid(order.getOid());
			// -- 补全属性：pid, title, image, price, num -> CartVO中的属性
			item.setPid(cart.getPid());
			item.setTitle(cart.getTitle());
			item.setImage(cart.getImage());
			item.setNum(cart.getNum());
			item.setPrice(cart.getPrice());
			// -- 补全属性：4个日志
			item.setModifiedTime(now);
			item.setModifiedUser(username);
			item.setCreatedTime(now);
			item.setCreatedUser(username);
			// -- 插入若干条订单商品数据：insertOrderItem(orderItem)
			insertOrederItem(item);
		}
		return order;
	}
	/**
	 * 插入订单数据
	 * @param order
	 * @return
	 */
	private Integer insertOrder(Order order) {
		return orderMapper.insertOrder(order);
	}
	
	/**
	 * 插入订单商品数据
	 * @param orderItem
	 * @return
	 */
	private Integer insertOrederItem(OrederItem orderItem) {
		return orderMapper.insertOrderItem(orderItem);
		
	}

}
