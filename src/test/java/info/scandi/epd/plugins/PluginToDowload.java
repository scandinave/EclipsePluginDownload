package info.scandi.epd.plugins;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import info.scandi.epd.plugins.adapters.secondaries.inmemory.InMemoryPluginLoader;
import info.scandi.epd.plugins.domain.entities.Plugin;
import info.scandi.epd.plugins.domain.loaders.PluginLoader;
import info.scandi.epd.plugins.usecases.PluginHandler;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@DisplayName("Plugin handler fetches")
class PluginToDowload {

	/*@Nested
	@DisplayName("Plugin handler fetches")
	class pluginHandlerFetches {*/
		
		
		@Nested
		@DisplayName("A List")
		class AList {

			Plugin checkstyle;
			
			@BeforeEach
			public void beforeEach() {
				this.checkstyle = new Plugin("Checkstyle");
			}
		
			@Test
			@DisplayName("with zero plugin if there is no plugin in the source")
			void getEmptyListIfNotPlugin() {
				List<Plugin> plugins = new ArrayList<Plugin>();
				PluginHandler pluginHandler = createPluginHandler(plugins);
				
				pluginHandler.all().subscribe(p -> {
					assertThat(p).isEqualTo(new ArrayList<>());
				});
			}
			
			@Test
			@DisplayName("with one plugin if there is one plugin in the source")
			void getOnePluginFromSource() {
				List<Plugin> plugins = new ArrayList<Plugin>();
				plugins.add(this.checkstyle);
				PluginHandler pluginHandler = createPluginHandler(plugins);
				
				pluginHandler.all().subscribe(p -> {
					assertThat(p).isEqualTo(plugins);
				});
			}

			
			@Test
			@DisplayName("with two plugin if there is two plugin in the source")
			void getTwoPluginFromSource() {
				Plugin cucumber = new Plugin("Cucumber");
				List<Plugin> plugins = new ArrayList<Plugin>();
				plugins.add(this.checkstyle);
				plugins.add(cucumber);
				PluginHandler pluginHandler = createPluginHandler(plugins);
				
				pluginHandler.all().subscribe(p -> {
					assertThat(p).isEqualTo(plugins);
				});
			}
			
			
			private PluginHandler createPluginHandler(List<Plugin> plugins) {
				PluginLoader pluginSource = new InMemoryPluginLoader(plugins);
				PluginHandler pluginHandler = new PluginHandler(pluginSource);
				return pluginHandler;
			}
		}
	//}

}
