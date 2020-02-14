package cn.tedu.store.service.ex;

/**
 * 超出限制异常
 * @author tarena
 *
 */
public class AddressCountLimitException extends  ServiceException{
	
	private static final long serialVersionUID = 7991875652328476596L;

	public AddressCountLimitException() {
		super();
	}

	public AddressCountLimitException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AddressCountLimitException(String message, Throwable cause) {
		super(message, cause);
	}

	public AddressCountLimitException(String message) {
		super(message);
	}

	public AddressCountLimitException(Throwable cause) {
		super(cause);
	}

}
