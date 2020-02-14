package cn.tedu.store.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.store.controller.ex.FileEmptyException;
import cn.tedu.store.controller.ex.FileSizeException;
import cn.tedu.store.controller.ex.FileTypeException;
import cn.tedu.store.controller.ex.FileUploadIOException;
import cn.tedu.store.controller.ex.FileUploadStateException;
import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.util.JsonResult;

@RestController
@RequestMapping("users")
public class UserController extends BaseController {
	
	@Autowired
	private IUserService service;
	
	static final long MAXSIZE = 101*1024;
	
	static final List<String> AVATAR_TYPES =new ArrayList<String>();
	static {
		AVATAR_TYPES.add("image/jpeg");
		AVATAR_TYPES.add("image/png");
		AVATAR_TYPES.add("image/gif");
		AVATAR_TYPES.add("image/bmp");
	}
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	@RequestMapping("reg")
	public JsonResult<Void> reg(User user){
//		JsonResult<Void> jsonResult = new JsonResult<Void>();
		service.reg(user);
//		jsonResult.setState(1);
//		return jsonResult;
		return new JsonResult<>(OK);
		
	}
	
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping("login")
	public JsonResult<User> login(String username,String password,HttpSession session){
		User data = service.login(username, password);
		session.setAttribute("uid",data.getUid());
		session.setAttribute("username",data.getUsername());
		return new  JsonResult<>(OK,data);
	}
	
	/**
	 * 密码修改
	 * @param uid
	 * @param oldPassword
	 * @param newPassword
	 * @param username
	 * @return
	 */
	@RequestMapping("change/password")
	public JsonResult<Void> changePassword(String oldPassword, String newPassword,HttpSession session){
		//从参数session中调出uid和用户名
		Integer uid =Integer.valueOf(session.getAttribute("uid").toString());
		String username = session.getAttribute("username").toString();
		//调用业务对象的方法执行修改密码
		service.changePassword(uid, oldPassword, newPassword, username);
		//返回
		return new JsonResult<>(OK);
	}
	/**
	 * 将service.showInfo(uid);查询的数据响应用户页面
	 * @param session 
	 * @return 
	 */
	@GetMapping("info/show")
	public JsonResult<User> showInfo(HttpSession session){
		//从session中获取uid
		Integer uid = Integer.parseInt(session.getAttribute("uid").toString());
		//调用业务对象showInfo()方法查询用户数据
		User data = service.showInfo(uid);
		//返回ok与以上调用时的返回结果
		return new JsonResult<>(OK,data);
	}
	
	// http://localhost:8080/users/info/change?phone=13800138888&email=root@tedu.cn&gender=0


	public JsonResult<User> changeInfo(User user,HttpSession session){
		//从session中获取uid和username
		Integer uid = getUidFromSession(session);
		String username  = getUsernameFromSession(session);
		//调用业务对象执行修改
		service.changeInfo(uid, username, user);
		return new JsonResult<>(OK);
	}
	
	/**
	 * 上传头像
	 * @param file
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping("avatar/change")
	public JsonResult<String>  changeAvatar(MultipartFile file,HttpSession session){
		
		//日志
		System.err.println("usrController.changeAvatar()");

		//判断文件是否为空
		boolean isEmpty = file.isEmpty();
		System.err.println("\t文件是否为空isEmpty:"+isEmpty);
		if(isEmpty) {
			//上传的文件为空则抛出异常
			throw new FileEmptyException("上传失败!请选择您要上传的文件!");
		}
		
		//获取文件大小
		long size = file.getSize();
		System.err.println("文件大小size:"+size);
		//判断文件是否超过规定字节
		if(size > MAXSIZE) {
			throw new FileSizeException("上传失败!选择的文件不允许超过"+(MAXSIZE/1024)+"KB!");
		}
		
		//获取文件的MIME类型
		String contentType= file.getContentType();
		System.err.println("获取文件的MIME类型contentType:"+contentType);
		if(!AVATAR_TYPES.contains(contentType)) {
			throw new FileTypeException("上传文件失败!选择的文件只允许"+AVATAR_TYPES+"类型");
		}
		
		//获取输入流,通常用于自定义处理客户提交的文件的数据
		//InputStream in= file.getInputStream();
		//System.err.println("获取文件getInputStream的流"+in);
		
		//获取原始文件名(客户端设备中的文件名)
		String originalFilename = file.getOriginalFilename();
		System.err.println("\toriginalFilename="+originalFilename);
		
		//将文件上传到哪个文件夹
		String parent =session.getServletContext().getRealPath("upload");
		System.err.println("\tupload path="+parent);
		
		//如果文件夹不存在则需要重新创建
		File dir = new File(parent);
		if(!dir.exists()) {//判断是否存在
			dir.mkdir();//建立一个新的目录
		}
		
		//保存上传的文件时使用的文件文
		String filename ="" + System.currentTimeMillis()+System.nanoTime();
		String suffix = "";
		int beginIndex = originalFilename.lastIndexOf(".");
		if(beginIndex >=1 ) {
			suffix = originalFilename.substring(beginIndex);
		}
		String child = filename + suffix;
		
		//将客户端上传的文件保存到服务器端
		//File dest = new File("D:/st/store/img/1.png");
		File dest = new File(parent,child);
		try {
			file.transferTo(dest);
		} catch (IllegalStateException e) {
			throw new FileUploadStateException("文件上传失败!您的文件的状态异常!");
		} catch (IOException e) {
			throw new FileUploadIOException("上传失败！读写文件时出现错误，请重新上传！");
		}
		
		//将保存的文件的路径记录到书库中
		String avatar = "/upload/"+child; 
		System.err.println(avatar);
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		service.changeAvatar(uid, avatar, username);
		
		//返回
		return new JsonResult<>(OK,avatar);
		
	}
	
/*//	
	@ExceptionHandler
	public JsonResult<Void> handleException(Throwable ex){
		//调用public JsonResult(Throwable ex) 构造方法
		JsonResult<Void> jsonResult = new JsonResult<Void>(ex);
		//调用public JsonResult(String message)
		//JsonResult<Void> jsonResult = new JsonResult<Void>(ex.getMessage());
		if(ex instanceof UsernameDuplicateException) {
			jsonResult.setState(2);
			//jsonResult.setMessage("注册失败!尝试注册的用户名已经被占用");
			//jsonResult.setMessage(ex.getMessage());
		}else if(ex instanceof InsertException) {
			jsonResult.setState(3);
			//jsonResult.setMessage("注册失败!执行插入数据时出现未知错误!请系统管理员");
			//jsonResult.setMessage(ex.getMessage());
		}
		return jsonResult;
	}
*/
}











