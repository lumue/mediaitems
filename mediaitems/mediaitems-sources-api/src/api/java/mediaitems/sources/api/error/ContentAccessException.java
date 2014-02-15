package mediaitems.sources.api.error;

public class ContentAccessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5213712902759618972L;

	public ContentAccessException() {
		super();
	}

	public ContentAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public ContentAccessException(String message) {
		super(message);
	}

	public ContentAccessException(Throwable cause) {
		super(cause);
	}

}
