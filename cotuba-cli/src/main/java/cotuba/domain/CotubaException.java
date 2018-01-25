package cotuba.domain;

public class CotubaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CotubaException(String message) {
		super(message);
	}

	public CotubaException(String message, Throwable t) {
		super(message, t);
	}

}
