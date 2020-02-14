package cn.tedu.store.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrederItem;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMapperTests {
	@Autowired
	private OrderMapper orderMapper;
	@Test
	public void insertOrder() {
		Order oder =new Order();
		oder.setOid(1);
		oder.setUid(1);
		oder.setRecvName("小可可");
		Integer rows =  orderMapper.insertOrder(oder);
		System.err.println(rows);
	}
	
	@Test
	public void insertOrderItem() {
		OrederItem oder =new OrederItem();
		oder.setId(1);
		oder.setOid(1);
		oder.setTitle("title");
		Integer rows =  orderMapper.insertOrderItem(oder);
		System.err.println(rows);
	}
	 

}
