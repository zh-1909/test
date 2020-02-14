package cn.tedu.store.service.ex;

/**
 * 查询数据不存在aid
 * @author tarena
 *
 */
public class AddressNotFoundException extends  ServiceException{
	
	private static final long serialVersionUID = 7991875652328476596L;

	public AddressNotFoundException() {
		super();
	}

	public AddressNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AddressNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public AddressNotFoundException(String message) {
		super(message);
	}

	public AddressNotFoundException(Throwable cause) {
		super(cause);
	}

}
