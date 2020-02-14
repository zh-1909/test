package cn.tedu.store.service.ex;
/**
 * 用户名重名异常
 * @author tarena
 *
 */
public class UsernameDuplicateException extends ServiceException {
	private static final long serialVersionUID = -1224474172375139228L;

	public UsernameDuplicateException() {
		super();
	}

	public UsernameDuplicateException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UsernameDuplicateException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsernameDuplicateException(String message) {
		super(message);
	}

	public UsernameDuplicateException(Throwable cause) {
		super(cause);
	}

}
