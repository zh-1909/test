package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UpdateException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameDuplicateException;
/**
 * 实现类是实现IUserService
 * @author tarena
 *
 */
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;
	/**
	 * 注册
	 */
	@Override
	public void reg(User user) {
		//输出日志
		System.err.println("\tUserServiceImp.reg()");
		System.err.println("\t注册数据:"+user);
		//从参数user中获取用户名
		String username = user.getUsername();
		//调用userMapper的findByUsername()方法只执行
		User result = userMapper.findByusername(username);
		//查询,判断查询结果是否不为null
		//UsernameDuplicateException
		if(result !=null) {
			throw new UsernameDuplicateException("注册失败!您尝试注册的"+username+"已经存在,请重新注册");
		}

		//准备注册
		//补全数据:加密后的密码,和盐值
		//补全数据:isDelete:值为0
		user.setIsDelete(0);
		
		//补全数据:4项日志
		Date now = new Date();
		user.setCreatedUser(username);
		user.setCreatedTime(now);
		user.setModifiedTime(now);
		user.setModifiedUser(username);
		
		//获取加密密码
		String salt = UUID.randomUUID().toString();
		user.setSalt(salt);
		String md5Password = getMd5Password(user.getPassword(),salt);
		user.setPassword(md5Password);
		
		//调用userMapper的insert()方法执行注册,并获取返回的受影响行数
		System.err.println("\t插入数据:"+user);
		Integer rows = userMapper.insert(user);
		
		//判断受影响的行数是否为不1
		if(rows != 1 ) {
			//是则抛出异常
			throw new InsertException("注册失败！执行插入数据时出现未知错误！请联系系统管理员！");
		}
	}

	/**
	 * 登录
	 */
	@Override
	public User login(String username,String password) {
		
		//输出日志
		System.err.println("UserServiceImpl.login()");
		System.err.println("\t用户输入的密码:"+password);
		System.err.println("\t用户输入用户名:"+username);
		
		// 根据参数username，调用userMapper.findByUsername()方法执行查询
		User result = userMapper.findByusername(username);
		if(result==null) {
			throw new UserNotFoundException("登录失败！用户数据不存在！");
		}if(result.getIsDelete()==1) {
			throw new UserNotFoundException("登录失败！用户数据不存在！");
		}

		//从查询结果中取出盐值
		String salt = result.getSalt();
		// 基于参数password与盐值执行加密，得到密文
		String md5Password=getMd5Password(password, salt);

		//判断得到的密文与查询结果中的password是否不一致
		System.err.println("\t数据库中的密码：" + result.getPassword());
		if(!md5Password.equals(result.getPassword())) {
			throw new PasswordNotMatchException("登录失败！密码错误！");
		}
		// 创建新的User对象
		User user = new User();
		// 将查询结果中的uid、username、avatar的值封装到新创建的对象中
		user.setUid(result.getUid());
		user.setUsername(result.getUsername());
		user.setAvatar(result.getAvatar());
		// 返回新创建的对象
		return user;
	}

	/**
	 * 修改密码
	 */
	@Override
	public void changePassword(Integer uid, String oldPassword, String newPassword, String username) {
		
		// 日志：输出原密码，新密码
		System.err.println("UserServiceImpl.changePassword()");
		System.err.println("\t输出原密码:"+oldPassword);
		System.err.println("\t输出新密码:"+newPassword);
		
		// 基于参数uid调用userMapper的findByUid()查询用户数据
		User user = userMapper.findByUid(uid);
		
		// 判断查询结果是否为null
		if(user==null) {
			// 是：UserNotFoundException
			throw new UserNotFoundException("修改密码失败！用户数据不存在！");
		}
		
		// 判断查询结果中的isDelete是否为1
		if(user.getIsDelete()==1){
			// 是：UserNotFoundException
			throw new UserNotFoundException("修改密码失败！用户数据不存在！");
		}
		// 从查询结果中取出盐值
		String salt = user.getSalt();
		
		// 日志：“将原密码执行加密”
		System.err.println("\t将原密码执行加密");
		
		// 将参数oldPassword结合盐值执行加密，得到oldMd5Password
		String oldMd5Password = getMd5Password(oldPassword, salt);
		
		// 日志：输出查询结果中的password
		String password = user.getPassword();
		
		// 判断oldMd5Password与查询结果中的password是否不一致
		if(!password.equals(oldMd5Password)) {
			// 是：PasswordNotMatchException
			throw new PasswordNotMatchException("修改密码失败！原密码错误！");
		}
		
		// 日志：“将新密码执行加密”
		System.err.println("\t将新密码执行加密");
		// 将参数newPassword结合盐值执行加密，得到newMd5Password
		String newMd5Password = getMd5Password(newPassword, salt);

		// 调用userMapper的updatePasswordByUid()执行更新密码，并获取返回的受影响的行数;
		Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password,username,new Date());
		// 判断受影响的行数是否不为1
		if(rows!=1) {
			// 是：UpdateException
			throw new UpdateException("修改密码失败！执行更新密码时出现未知错误！请联系系统管理员！");
		}

	}

	/**
	 * 根据uid查询用户信息
	 * 封装查询到的username,phone,eamil,gender
	 * 返回值为username,phone,eamil,gender
	 */
	@Override
	public User showInfo(Integer uid) {
		//根据参数查询uid数据
		User result= userMapper.findByUid(uid);
		//判断是否为null
		if(result==null) {
			throw new UserNotFoundException("获取用户数据失败!用户不存在!");
		}
		
		//判断查询结果中的isDelete是否为1
		if(result.getIsDelete()==1) {
			throw new UserNotFoundException("获取用户数据失败!用户不存在!");
		}
		
		//创建User对象
		User user= new User();
		//查询结构中的username,phone,eamil,gender
		user.setUsername(result.getUsername());
		user.setPhone(result.getPhone());
		user.setEmail(result.getEmail());
		user.setGender(result.getGender());
		// 返回新User对象
		return user;
	}

	@Override
	public void changeInfo(Integer uid, String username, User user) {
		// 基于参数uid调用userMapper的findByUid()查询用户数据
		User result = userMapper.findByUid(uid);
		// 判断查询结果是否为null
		if(result==null) {
			// 是：抛出UserNotFoundException
			throw new UserNotFoundException("用户不存在!....");
		}
		
		// 判断查询结果中的isDelete是否为1
		if(result.getIsDelete()==1) {
			// 是：抛出UserNotFoundException
			throw new UserNotFoundException("用户不存在!....");
		}
		
		// 向参数user中封装uid：
		user.setUid(uid);
		// 向参数user中封装username到modifiedUser属性：
		user.setModifiedUser(username);
		// 向参数user中封装当前时间到modifiedTime属性：user.setModifiedTime(new Date());
		user.setModifiedTime(new Date());
		System.out.println(user);
		// 调用持久层userMapper的updateInfoByUid(user)执行更新，获取返回的受影响的行数
		Integer rows = userMapper.updateInfoByUid(user);
		// 判断受影响的行数是否不为1
		if(rows!=1) {
			// 是：抛出UpdateException
			throw new UpdateException("修改用户资料失败！执行更新用户资料时出现未知错误！请联系系统管理员！");
		}

	}

	/**
	 * 上传头像
	 */
	@Override
	public void changeAvatar(Integer uid, String avatar, String username) {
		// 基于参数uid调用userMapper的findByUid()查询用户数据
		User user = userMapper.findByUid(uid);
		// 判断查询结果是否为null
		if(user==null) {
		// 是：UserNotFoundException
			throw new UserNotFoundException("修改用户头像失败！用户数据不存在！");
		// 判断查询结果中的isDelete是否为1
		}else if(user.getIsDelete()==1){
		// 是：UserNotFoundException
			throw new UserNotFoundException("修改用户头像失败！用户数据不存在！");
		}
		// 调用userMapper的updateAvatarByUid()执行更新密码，并获取返回的受影响的行数;
		Integer rows = userMapper.updateAvatarByUid(uid, avatar,username, new Date());
		// 判断受影响的行数是否不为1
		if(rows!=1) {
		// 是：UpdateException
			throw new UpdateException("修改用户头像失败！执行更新用户头像时出现未知错误！请联系系统管理员！");
		}

	}

	/**
	 * 执行密码加密,获取加密后的密码
	 * @param password 密码
	 * @param salt 盐值
	 * @return
	 */
	private String getMd5Password(String password,String salt) {
		//加密规则:
		//1.使用"盐+密码+盐"作为原文
		//2.三重加密
		System.err.println("\t加密-原始密:"+password);
		System.err.println("\t加密-盐值"+salt);
		for(int i=0;i<3;i++) {
			password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes());
		}
		System.err.println("\t加密-密文:"+password);
		return password;
	}









}

















