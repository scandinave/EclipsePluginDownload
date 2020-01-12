package info.scandi.epd.plugins.core.interfaces;

public interface Validator {

	/**
	 * Validate that a string is a url.
	 * @param url The url to validate
	 * @return true if the url is correct, false otherwise
	 */
	boolean isValidUrl(String url);
	/**
	 * Validate that a string is a UUIDv1
	 * @param uuid the uuid to validate
	 * @return true id the uuid is correct, false otherwise
	 */
	boolean isValidUuid(String uuid);
	
	/**
	 * Validate that a a plugin name is in PascalCase
	 * @param pluginName the name of the plugin
	 * @return true id the uuid is correct, false otherwise
	 */
	public boolean isValidPluginName(String pluginName);
}
