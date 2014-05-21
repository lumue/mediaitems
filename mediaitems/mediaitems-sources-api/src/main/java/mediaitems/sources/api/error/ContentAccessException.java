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

	public static class RuntimeContentAccessException extends RuntimeException {
	
		/**
		 * 
		 */
		private static final long serialVersionUID = 1651466620063864876L;

		/**
		 * 
		 */

		public RuntimeContentAccessException() {
			super();
		}

		public RuntimeContentAccessException(String message, Throwable cause,
				boolean enableSuppression, boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
		}

		public RuntimeContentAccessException(String message, Throwable cause) {
			super(message, cause);
		}

		public RuntimeContentAccessException(String message) {
			super(message);
		}

		public RuntimeContentAccessException(Throwable cause) {
			super(cause);
		}

	}

}
