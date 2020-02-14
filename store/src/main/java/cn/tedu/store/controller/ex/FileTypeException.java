package cn.tedu.store.controller.ex;
/**
 * 上传文件类型异常
 * @author tarena
 *
 */
public class FileTypeException extends FileUploadException {

	private static final long serialVersionUID = 1241017634099485103L;

	public FileTypeException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public FileTypeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public FileTypeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public FileTypeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
