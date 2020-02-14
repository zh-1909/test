package cn.tedu.store.service.impl;

import java.util.Date;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.entity.Product;
import cn.tedu.store.mapper.CartMapper;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.service.IProductService;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.vo.CartVO;
/**
 * 处理购物车数据的业务层实现类
 */
@Service
public class CartServiceImpl implements ICartService{
	
	@Autowired
	private CartMapper cartMapper;	
	@Autowired
	private IProductService productService;
	/**
	 * 添加商品到购物车
	 */
	@Override
	public void addToCart(Integer uid,String username,Integer pid,Integer amount){
		// 创建当前时间对象
		Date date = new Date();
		// 根据uid和pid查询购物车数据
		Cart result = findByUidAndPid(uid, pid);
		// 判断查询结果是否为null
		if(result==null) {
			// 是：表示该用户尚未将该商品添加到购物车，则需要插入购物车数据
			// -- 调用productService.getById()方法查询商品数据
			Product product = productService.getById(pid);
			System.err.println("product="+product);
			// -- 创建Cart对象
			Cart cart = new Cart();
			// -- 补全数据：uid, pid, num, price(从商品数据中获取)
			cart.setUid(uid);
			cart.setPid(pid);
			cart.setNum(amount);
			cart.setPrice(product.getPrice());
			// -- 补全数据：4个日志
			cart.setCreatedUser(username);
			cart.setCreatedTime(date);
			cart.setModifiedTime(date);
			cart.setModifiedUser(username);
			// -- 调用insert(cart)执行插入数据
			insert(cart);
		}else {
		// 否：表示该用户已经将该商品添加到购物车，则需要修改购物车数据中商品的数量
		// -- 从查询结果中获取cid
		Integer cid =  result.getCid();
		// -- 从查询结果中获取num，与参数amount相加，得到新的数量
		Integer num = result.getNum()+amount;
		// -- 调用updateNumByCid(cid, num, modifiedUser, modifiedTime)方法执行修改数量
		updataNumByCid(cid, num,username,date);
	}
	}

	@Override
	public Integer addNum(Integer cid, Integer uid, String username) {
		return null;
	}

	
	/**
	 * 根据用户id查询购物车数据
	 */
	@Override
	public List<CartVO> getVOByUid(Integer uid) {
		return findVOByUid(uid);
	}

	/**
	 * 查询订单列表
	 */
	@Override
	public List<CartVO> getVOByCids(Integer[] cids,Integer uid) {
		List<CartVO> carts=findVOByCids(cids);
		//判断集合中元素是否归属当前用户,如果不是则移除
		Iterator<CartVO> list = carts.iterator();
		while(list.hasNext()) {
			CartVO cart = list.next();
			if(!cart.getUid().equals(uid)) {
				list.remove();
			}
		}
		return carts;
	}
	
	/**
	 * 插入购物车数据
	 * @param cart 购物车数据
	 * @return 受影响的行数
	 */
	private void insert(Cart cart) {
		Integer rows = cartMapper.insert(cart);
		if (rows != 1) {
			throw new InsertException(
				"创建购物车数据失败！插入购物车数据时出现未知错误，请联系系统管理员！");
		}
	}


	
	/**
	 * 修改购物车数据中商品的数量
	 * @param cid 购物车数据id
	 * @param num 新的数量
	 * @param modifiedUser 修改执行人
	 * @param modifiedTime 修改时间
	 * @return 受影响的行数
	 */
	private Integer updataNumByCid(Integer cid,Integer num,String modifiedUser,
			Date modifiedTime) {
		Integer rows = cartMapper.updataNumByCid(cid, num, modifiedUser, modifiedTime);
		if(rows!=1) {
			throw new InsertException("更新商品数量失败！更新购物车数据时出现未知错误，请联系系统管理员！");
		}
		return rows;
	}

	/**
	 * 根据用户id与商品id查询购物车数据
	 * @param uid 用户id
	 * @param pid 商品id
	 * @return 匹配的购物车数据，如果没有匹配的数据，则返回null
	 */
	private Cart findByUidAndPid(Integer uid,Integer pid) {
		Cart result = cartMapper.findByUidAndPid(uid, pid);
		
		return result;
	}

	
	 /* 根据用户id查询购物车数据
	 * @param uid
	 * @return
	 */
	private List<CartVO> findVOByUid(Integer uid){
		
		return cartMapper.findVOByUid(uid);
	}
	

	/**
	 * 根据多个数据id查询购物车数据的列表
	 * @param cids 多个购物车数据id
	 * @return 匹配的购物车数据的列表
	 */
	private List<CartVO> findVOByCids(Integer[] cids){
		
		return cartMapper.findVOByCids(cids);
	}


	


	


	
}
