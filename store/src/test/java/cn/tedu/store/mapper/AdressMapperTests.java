package cn.tedu.store.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Address;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdressMapperTests {
	@Autowired
	private AddressMapper addressMapper; 
	@Test
	public void insert() {
		Address address = new Address(); 
		address.setReceiver("小熊");
		address.setUid(2);
		System.err.println(address);
		Integer rows = addressMapper.insert(address);
		System.err.println("\trows"+rows);
		
	}
	@Test
	public void countByUid() {
		Integer uid = 2;
		Integer rows=addressMapper.countByUid(uid);
		System.err.println(rows);
	}
	
	@Test
	public void findByUid() {
		Integer uid=16;
		List<Address> list=addressMapper.findByUid(uid);
		for (Address address : list) {
			System.err.println(address);
		}
	}
	
	@Test
	public void updateNonDefaultByUId() {
		
		Integer uid = 16;
		Integer rows = addressMapper.updateNonDefaultByUId(uid);
		System.err.println("rows="+rows);
	}
	@Test
	public void updateDefaultByAid() {
		Integer aid = 39;
		String modifiedUser = "我有空";
		Date modifiedTime = new Date();
		Integer rows = addressMapper.updateDefaultByAid(aid, modifiedUser, modifiedTime);
		System.err.println(rows);
	}
	
	@Test
	public void findByAid() {
		Integer aid = 39;
		Address a = addressMapper.findByAid(aid);
		System.err.println(a);
	}
	@Test
	public void deleteByAid() {
		Integer aid = 39;
		Integer a = addressMapper.deleteByAid(aid);
		System.err.println(a);
		
	}
	
	@Test
	public void findLastModifiedByUid() {
		Integer uid = 16;
		Address a = addressMapper.findLastModifiedByUid(uid);
		System.err.println(a);
	}
}



















