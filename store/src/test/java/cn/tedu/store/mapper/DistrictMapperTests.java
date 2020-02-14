package cn.tedu.store.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.District;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictMapperTests {
	@Autowired
	private DistrictMapper mapper; 
	@Test
	public void findByParent() {
		String parent = "110000";
		List<District> list = mapper.findByParent(parent);
		System.err.println(list);
		
	}
	
	@Test
	public void findNameByCode() {
		String code = "110101";
		String str=mapper.findNameByCode(code);
		System.err.println(str);
	}
	
}
