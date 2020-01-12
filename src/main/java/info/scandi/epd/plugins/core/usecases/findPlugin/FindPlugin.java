package info.scandi.epd.plugins.core.usecases.findPlugin;

import java.util.List;

import info.scandi.epd.plugins.core.usecases.gateway.PluginDTO;
import info.scandi.epd.plugins.core.usecases.gateway.PluginLoader;
import io.reactivex.Observable;

public class FindPlugin {
	
	//private PluginLoader pluginSource;
	
	public FindPlugin(PluginLoader pluginSource) {
		//this.pluginSource = pluginSource;
	}

	public Observable<List<PluginDTO>> all() {
		//return this.pluginSource.all();
	}

}
