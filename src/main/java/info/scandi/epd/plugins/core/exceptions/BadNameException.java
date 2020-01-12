package info.scandi.epd.plugins.core.exceptions;

/**
 * Exception raised when a plugin name is not camelCase or PascalCase
 * @author scandinave
 *
 */
public class BadNameException extends RuntimeException {

	private static final long serialVersionUID = -3371842398904168367L;

	
	public BadNameException(String message) {
		super(message);
	}
}
