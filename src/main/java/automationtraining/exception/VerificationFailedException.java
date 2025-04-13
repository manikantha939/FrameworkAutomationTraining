package automationtraining.exception;

public class VerificationFailedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VerificationFailedException(String message) {
		super(message);
	}
	
	public VerificationFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public VerificationFailedException(Throwable cause) {
		super(cause);
	}
}
