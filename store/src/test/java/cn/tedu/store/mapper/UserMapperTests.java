package cn.tedu.store.mapper;

import java.util.Date;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import cn.tedu.store.entity.User;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTests {
	@Autowired
	private UserMapper mapper;
	@Test
	/**
	 * <!--插入用户数据 测试-->
	 * <!-- Integer insert(User user) -->
	 */
	public void insert() {
		User user = new User();
		user.setUsername("孙悟空");
		user.setPassword("123123");
		user.setSalt("");
		user.setGender(0);
		user.setPhone("");
		user.setEmail("");
		user.setAvatar("");
		user.setIsDelete(1);
		user.setCreatedUser("");
		Integer rows = mapper.insert(user);
		System.out.println(rows);
		System.out.println(user);
	}

	@Test
	/**
	 * <!-- 根据用户名查询查询用户数据详情 -->
	 *  User findByusername(String username)
	 */
	public void findByusername() {
		String username="";
		User user = mapper.findByusername(username);
		System.out.println(user);
	}
	
	@Test
	public void md5() {
		String username = "孙悟空";
		String md5 = DigestUtils.md5DigestAsHex(username.getBytes());
		System.err.println(md5);
	}
	@Test
	public void updatePasswordByUid() {
		Integer uid = 12;
		String password = "1234";
		String modifiedUser = "密码管理员";
		Date modifiedTime = new Date();
		Integer rows = mapper.updatePasswordByUid(uid, password, modifiedUser, modifiedTime);
		System.err.println("rows=" + rows);
	}
	@Test
	public void updateAvatarByUid() {
		Integer uid = 14;
		String avatar = "1234";
		String modifiedUser = "密码管理员";
		Date modifiedTime = new Date();
		Integer rows = mapper.updateAvatarByUid(uid, avatar, modifiedUser, modifiedTime);
		System.err.println("rows=" + rows);
	}
	/**
	 * 测试
	 * 根据用户的id更新用户基本信息
	 * @param user 封装了用户的id和新的信息对象
	 * @return 受影响的行数
	 */
	@Test
	public void updateInfoByUid() {
		User user = new User();
		user.setUid(14);
		user.setPhone("1324654564");
		user.setEmail("132@.com");
		user.setGender(1);
		Integer rows = mapper.updateInfoByUid(user);
		System.out.println("rows="+rows);
	}
	
	@Test
	public void findByUid() {
		Integer uid = 16000;
		User result = mapper.findByUid(uid);
		System.err.println(result);
	}

}









