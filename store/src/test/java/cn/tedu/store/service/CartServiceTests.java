package cn.tedu.store.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.service.ex.ServiceException;
import cn.tedu.store.vo.CartVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceTests {
	@Autowired
	private ICartService service;

	@Test
	public void addnew() {
		try{ 
			Integer uid = 1;
			Integer pid = 10000001;
			Integer amount = 1;
			String username = "gfh";
			service.addToCart(uid, username, pid, amount);
			System.err.println("OK");
		}catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void getVOByUid() {
		Integer uid = 1;
		List<CartVO> list = service.getVOByUid(uid); 
		for (CartVO cartVO : list) {
			System.err.println(cartVO);
		}
		System.err.println(list);

	}
	@Test
	public  void getVOByCids(){
		Integer[] cids = {1,3,5,7};
		Integer uid = 1;
		List<CartVO> list = service.getVOByCids(cids,uid);
		for (CartVO cartVO : list) {
			System.err.println(cartVO);
		}
		
	}
	
}
