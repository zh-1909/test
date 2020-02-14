package cn.tedu.store.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cn.tedu.store.controller.ex.FileEmptyException;
import cn.tedu.store.controller.ex.FileSizeException;
import cn.tedu.store.controller.ex.FileTypeException;
import cn.tedu.store.controller.ex.FileUploadException;
import cn.tedu.store.controller.ex.FileUploadIOException;
import cn.tedu.store.controller.ex.FileUploadStateException;
import cn.tedu.store.service.ex.AccessDeniedException;
import cn.tedu.store.service.ex.AddressCountLimitException;
import cn.tedu.store.service.ex.AddressNotFoundException;
import cn.tedu.store.service.ex.DeleteException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.ProductNotFoundException;
import cn.tedu.store.service.ex.ServiceException;
import cn.tedu.store.service.ex.UpdateException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameDuplicateException;
import cn.tedu.store.util.JsonResult;

@RestControllerAdvice//全局异常处理
public class GlobalExceptionHeadler{
	
	@ExceptionHandler({ServiceException.class,FileUploadException.class})//表示处理异常的类型
	
	public JsonResult<Void> handleException(Throwable ex){
		 
		JsonResult<Void> json = new JsonResult<>(ex); 
		if(ex instanceof UsernameDuplicateException) {
			json.setState(4000);
		}else if(ex instanceof UserNotFoundException) {
			json.setState(4001);
		}else if(ex instanceof PasswordNotMatchException) {
			json.setState(4002);
		}else if(ex instanceof AddressCountLimitException) {
			json.setState(4003);
		}else if(ex instanceof AddressNotFoundException) {
			json.setState(4004);
		}else if(ex instanceof AccessDeniedException) {
			json.setState(4005);
		}else if(ex instanceof DeleteException) {
			json.setState(4006);
		}else if(ex instanceof InsertException) {
			json.setState(5000);
		}else if(ex instanceof UpdateException) {
			json.setState(5001);
		}else if(ex instanceof FileEmptyException) {
			json.setState(6000);
		}else if(ex instanceof FileSizeException) {
			json.setState(6001);
		}else if(ex instanceof FileTypeException) {
			json.setState(6002);
		}else if(ex instanceof FileUploadStateException) {
			json.setState(6003);
		}else if(ex instanceof FileUploadIOException) {
			json.setState(6004);
		}else if(ex instanceof ProductNotFoundException) {
			json.setState(6005);
		}
		return json;
		
	}

}
