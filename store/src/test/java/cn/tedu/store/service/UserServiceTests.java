package cn.tedu.store.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.ex.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {
	@Autowired
	private IUserService service;
	@Test
	public void reg() {
		try {
			User user = new User();
			user.setUsername("白龙马");
			user.setPassword("123");
			user.setAvatar("头像");
			user.setEmail("邮箱");
			user.setGender(1);
			user.setPhone("电话");
			service.reg(user);
			System.out.println(user);
		}catch(ServiceException e) {
			System.out.println(e.getClass().getName());
		}
		
	}
	
	@Test
	public void login() {
		try {
			String username = "白龙马";
			String password = "123";
			User user=service.login(username, password);
			System.err.println(user);
		} catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
			
		}
	}
	
	@Test 
	public void changePassword(){
		try {
			Integer uid = 5;
			String username="小白";
			String oldPassword = "123";
			String newPassword = "1234";
			service.changePassword(uid, oldPassword, newPassword, username);
			System.err.println("OK");
		}catch (ServiceException e) {
			System.out.println(e.getClass().getName());
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void showInfo() {
		try {
			Integer uid = 14;
			User user = service.showInfo(uid);
			System.err.println(user);
		}catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	@Test
	public void  changeInfo() {
		try {
			
		}catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void changeAvatar() {
		try {
			Integer uid = 14;
			String username="小白";
			String avatar= "123";
			service.changeAvatar(uid, avatar, username);
			System.err.println("OK");
		}catch (ServiceException e) {
			System.out.println(e.getClass().getName());
			System.out.println(e.getMessage());
		}
	}
	
}
