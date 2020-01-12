package info.scandi.epd.plugins.core.usecases.createPlugin;

import java.util.HashMap;
import java.util.Map;

import info.scandi.epd.plugins.core.dto.PluginDTO;
import info.scandi.epd.plugins.core.entities.Plugin;
import info.scandi.epd.plugins.core.exceptions.ValidationException;
import info.scandi.epd.plugins.core.interfaces.Validator;
import info.scandi.epd.plugins.core.mappers.PluginMapper;

/**
 * This use case handle the addition of a plugin to the download list.
 * @author scandinave
 *
 */
public class CreatePlugin {
	
	private Validator validator;
	
	public CreatePlugin(Validator validator) {
		this.validator = validator;
	}

	/**
	 * Add a plugin to the list of plugin to download.
	 * @param createPluginDTO The plugin data
	 * @return A pluginDTO object create from the data passed as parameter
	 */
	public PluginDTO add(CreatePluginDTO createPluginDTO) {
		boolean validUuid = validator.isValidUuid(createPluginDTO.getUuid());
		boolean validUrl = validator.isValidUrl(createPluginDTO.getUrl());
		boolean validName = validator.isValidPluginName(createPluginDTO.getName());
		if(validName && validUuid && validUrl) {
			Plugin p = PluginMapper.pluginFromCreatePluginDTO(createPluginDTO);
			
			return PluginMapper.pluginToPluginDTO(p);
		} else {
			Map<String, String> errors = populateErrors(validName, validUuid, validUrl);
			throw new ValidationException(errors);
		}
	}
	
	private Map<String,String> populateErrors(boolean validName, boolean validUuid, boolean validUrl) {
		Map<String, String> errors = new HashMap<String, String>();
		if(!validName) {
			errors.put("name", "Name must be in PascalCase");
		}
		if(!validUuid) {
			errors.put("uuid", "This is not a UUIDv1");
		}
		if(!validUrl) {
			errors.put("url", "This is not a correct URL");
		}
		
		return errors;
	}

}
