package info.scandi.epd.plugins.core.mappers;

import info.scandi.epd.plugins.core.dto.PluginDTO;
import info.scandi.epd.plugins.core.entities.Plugin;
import info.scandi.epd.plugins.core.usecases.createPlugin.CreatePluginDTO;

/**
 * Mapper to create Plugin entity or extract data from plugin entity
 * @author scandinave
 *
 */
public class PluginMapper {

	/**
	 * Return a PluginDTO from a Plugin entity
	 * @param p the source plugin
	 * @return the pluginDTO extract from plugin entity
	 */
	public static PluginDTO pluginToPluginDTO(Plugin p) {
		return new PluginDTO(p.getId(), p.getUrl(), p.isEnabled(), p.getTotalSize(), p.getDownloadedSize(), p.getPourcentDownloadedSize());
	}
	
	/**
	 * Create a Plugin Entity from a CreatePluginDTO
	 * @param createPluginDTO The DTO to create a Plugin Entity
	 * @return The plugin Entity
	 */
	public static Plugin pluginFromCreatePluginDTO(CreatePluginDTO createPluginDTO) {
		return new Plugin(createPluginDTO.getUuid(), createPluginDTO.getName(),  createPluginDTO.getUrl(), createPluginDTO.isEnabled());
	}
}
