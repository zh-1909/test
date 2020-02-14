package cn.tedu.store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.util.JsonResult;
/**
 * 处理收货地址数据相关请求的控制器类
 * @author tarena
 *
 */
@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController {
	@Autowired
	private IAddressService addressService;
	// http://localhost:8080/addresses/addnew?receiver=Jackson
	@RequestMapping("addnew")
	public JsonResult<Void> addnew(Address address,HttpSession session){
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		addressService.addnew(uid, username, address);
		return new JsonResult<>(OK);
	}
	
	/**
	 * 查询用户收货地址
	 * @param session
	 * @return
	 */
	@GetMapping("")
	public JsonResult<List<Address>> getByUid(HttpSession session){
		Integer uid = getUidFromSession(session);
		List<Address> data = addressService.getByUid(uid);
		return new JsonResult<>(OK,data);
	}
	
	/**
	 * 修改地址默认
	 * @param aid
	 * @param session
	 * @return
	 */
	@RequestMapping("{aid}/set_default")
	public JsonResult<Void> setDefault(
			@PathVariable("aid") Integer aid,
			HttpSession session){
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session) ;
		addressService.setDefault(aid, uid, username);
		return new JsonResult<>(OK);
	}
	
	public JsonResult<Void> Delete(HttpSession session){
		return new JsonResult<>(OK);
	}
	
	// http://localhost:8080/addresses/21/delete
		@RequestMapping("{aid}/delete")
		public JsonResult<Void> delete(
				@PathVariable("aid") Integer aid,
				HttpSession session) {
			Integer uid = getUidFromSession(session);
			String username = getUsernameFromSession(session);
			addressService.delete(aid, uid, username);
			return new JsonResult<>(OK);
		}
	
}
