package cn.tedu.store.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.vo.CartVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartMapperTests {
	@Autowired
	private CartMapper cartMapper;
	@Test
	public void insert() {
		Cart cart = new Cart();
		cart.setNum(1);
		cart.setPid(10000001);
		cart.setUid(1);
		cart.setPrice((long)666);
		System.err.println(cartMapper.insert(cart));
	}
	@Test
	public void updateNumBycid() {
		Integer num=10;
		Integer cid=1;
		String modifiedUser="modifiedUser";
		Date modifiedTime=new Date();
		System.out.println(cartMapper.updataNumByCid(cid, num, modifiedUser, modifiedTime));
	}
	
	@Test
	public void findByUidAndPid() {
		Integer uid = 1;
		Integer pid = 10000001;
		System.err.println(cartMapper.findByUidAndPid(uid, pid));
	}
	
	@Test
	public void findVOByUid() {
		Integer uid = 16;
		List<CartVO> list = cartMapper.findVOByUid(uid);
		for (CartVO cartVO : list) {
			
			System.err.println(cartVO);
		}
	}
	
	@Test
	public void findVOByCids() {
		Integer[] cids = {16};
		List<CartVO> list = cartMapper.findVOByCids(cids);
		for (CartVO cartVO : list) {
			
			System.err.println(cartVO);
		}
	}
	
	@Test
	public void findByCid() {
		Integer cid =10;
		Cart result = cartMapper.findByCid(cid);
		System.out.println(result);
	}

}
