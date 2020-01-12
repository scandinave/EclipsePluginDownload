package info.scandi.epd.plugins.core.usecases.createPlugin;

/**
 * A DTO used to pass data need for Plugin creation via the CreatePluginUseCase
 * @author scandinave
 *
 */
public class CreatePluginDTO {

	private final String uuid;
	private final String name;
	private final String url;
	private final boolean enabled;
	
	/**
	 * Date for plugin creation
	 * @param name The name of the plugin in camelCase
	 * @param url The url of the plugin repository
	 * @param enabled If the plugin must be add to the download list
	 */
	public CreatePluginDTO(String uuid, String name, String url, boolean enabled) {
		super();
		this.uuid = uuid;
		this.name = name;
		this.url = url;
		this.enabled = enabled;
	}
	
	public String getUuid() {
		return this.uuid;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public boolean isEnabled() {
		return enabled;
	}	
}
