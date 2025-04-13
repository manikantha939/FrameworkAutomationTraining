package automationtraining.exception;

public class WebElementException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WebElementException(String message) {
		super(message);
	}
	
	public WebElementException(String message, Throwable cause) {
		super(message, cause);
	}

	public WebElementException(Throwable cause) {
		super(cause);
	}
}
