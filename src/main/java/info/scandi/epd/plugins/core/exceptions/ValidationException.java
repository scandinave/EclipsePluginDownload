package info.scandi.epd.plugins.core.exceptions;

import java.util.HashMap;
import java.util.Map;

/**
 * Exception raised when a validation failed. 
 * Failed validation can be access using getErrors() method.
 * @author scandinave
 *
 */
public class ValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1570944669176020370L;

	private Map<String, String> errors = new HashMap<String, String>();

	public ValidationException(Map<String, String> errors) {
		super("Some values are incorrects. Please check getErrors for more informations");
		this.errors = errors;
	}

	/**
	 * Return a list of errors as string indicating a validation failed.
	 * @return the list of errors
	 */
	public Map<String, String> getErrors() {
		return errors;
	}
	
}
