package info.scandi.epd.plugins.adapters.secondaries.inmemory;

import java.util.List;

import info.scandi.epd.plugins.domain.entities.Plugin;
import info.scandi.epd.plugins.domain.loaders.PluginLoader;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class InMemoryPluginLoader implements PluginLoader {

	private Observable<List<Plugin>> plugins;
	
	public InMemoryPluginLoader(List<Plugin> plugins) {
		 this.plugins = BehaviorSubject.createDefault(plugins);
	}

	@Override
	public Observable<List<Plugin>> all() {
		return this.plugins;
	}

}
