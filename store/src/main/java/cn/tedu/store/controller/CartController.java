package cn.tedu.store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.service.ICartService;
import cn.tedu.store.util.JsonResult;
import cn.tedu.store.vo.CartVO;

/**
 * 处理购物车相关请求的控制器类
 */
@RestController
@RequestMapping("carts")
public class CartController extends BaseController{
	@Autowired
	private ICartService cartService;
	@RequestMapping("add_to_cart")
	//localhost/carts/add_to_cart?pid=10000001&amount=1
	public JsonResult<Void> addToCart(Integer pid,Integer amount,HttpSession session){
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		cartService.addToCart(uid, username, pid, amount);
		return new JsonResult<>(OK);
	}
	
	@RequestMapping("VOByUid")
	//localhost/carts/VOByUid
	public JsonResult<List<CartVO>> getVOByUid(HttpSession session){
		Integer uid = getUidFromSession(session);
		List<CartVO> data = cartService.getVOByUid(uid);  
		
		return new JsonResult<>(OK,data);
	}
	
	@GetMapping("get_by_cids")
	public JsonResult<List<CartVO>> getVOByCids(Integer[] cids,HttpSession session){
		Integer uid = getUidFromSession(session);
		List<CartVO> data = cartService.getVOByCids(cids, uid);
		return new JsonResult<>(OK,data);
	}
	
}
