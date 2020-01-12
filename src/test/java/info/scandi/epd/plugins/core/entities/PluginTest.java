package info.scandi.epd.plugins.core.entities;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import info.scandi.epd.plugins.core.exceptions.DownloadException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Entities")
class PluginTest {
		
	@DisplayName("Should be instantiable")
	@Test
	void instantiate() {
		final String pluginUuid = "ac2ef5a5-123e-cbd3-cfe9-a2fb5cd356cc";
		final String pluginName = "Checkstyle";
		Plugin p = new Plugin(pluginUuid, pluginName, "http://marketplace.eclipse.org/checkstyle", true);
		assertThat(p).isInstanceOf(Plugin.class);
		assertThat(p).isNotNull();

	}
	
	@DisplayName("Should set the correct value when create")
	@Test
	void testCreationWithValue() {
		final String pluginUuid = "ac2ef5a5-123e-cbd3-cfe9-a2fb5cd356cc";
		final String pluginName = "Checkstyle";
		final String pluginUrl = "http://marketplace.eclipse.org/checkstyle";
		final boolean pluginIsEnable =  true;
		Plugin p = new Plugin(pluginUuid, pluginName, pluginUrl, pluginIsEnable);
		assertThat(p.getId()).isEqualTo(pluginUuid);
		assertThat(p.getName()).isEqualTo(pluginName);
		assertThat(p.getUrl()).isEqualTo(pluginUrl);
		assertThat(p.isEnabled()).isTrue();
		assertThat(p.getTotalSize()).isEqualTo(0);
		assertThat(p.getDownloadedSize()).isEqualTo(0);
		
		final String pluginUuidCucumber = "bc2ef5a5-123e-cbd3-cfe9-a2fb5cd356cc";
		final String pluginNameCucumber = "Cucumber";
		final String pluginUrlCucumber = "http://marketplace.eclipse.org/cucumber";
		final boolean pluginIsEnableCucumber =  false;
		Plugin p2 = new Plugin(pluginUuidCucumber, pluginNameCucumber, pluginUrlCucumber, pluginIsEnableCucumber);
		assertThat(p2.getId()).isEqualTo(pluginUuidCucumber);
		assertThat(p2.getName()).isEqualTo(pluginNameCucumber);
		assertThat(p2.getUrl()).isEqualTo(pluginUrlCucumber);
		assertThat(p2.isEnabled()).isFalse();
		assertThat(p2.getTotalSize()).isEqualTo(0);
		assertThat(p2.getDownloadedSize()).isEqualTo(0);
	}
	
	@DisplayName("Should return the correct downloaded pourcentage")
	@Test
	void testDownloadedPourcentage() {
		final String pluginUuid = "ac2ef5a5-123e-cbd3-cfe9-a2fb5cd356cc";
		final String pluginName = "Checkstyle";
		final String pluginUrl = "http://marketplace.eclipse.org/checkstyle";
		final boolean pluginIsEnable =  true;
		Plugin p = new Plugin(pluginUuid, pluginName, pluginUrl, pluginIsEnable);
		assertThat(p.getPourcentDownloadedSize()).isEqualTo(0);
		final Long TOTALSIZE = 12L;
		p.setTotalSize(TOTALSIZE);
		assertThat(p.getTotalSize()).isEqualTo(TOTALSIZE);
		assertThat(p.getPourcentDownloadedSize()).isEqualTo(0);
		// update size by one.
		p.updateDownloadProgress();
		assertThat(p.getDownloadedSize()).isEqualTo(1);
		assertThat(p.getPourcentDownloadedSize()).isEqualTo(100 * 1 / TOTALSIZE);
		// update size multiple
		p.updateDownloadProgress(9L);
		assertThat(p.getDownloadedSize()).isEqualTo(10);
		assertThat(p.getPourcentDownloadedSize()).isEqualTo(100 * 10 / TOTALSIZE);
	}
	
	@DisplayName("Should throw exeception when trying to set downloadedSize and totalSize is 0")
	@Test
	void testdownloadSizeWhenTotalSizeIsZero () {
		final String pluginUuid = "ac2ef5a5-123e-cbd3-cfe9-a2fb5cd356cc";
		final String pluginName = "Checkstyle";
		final String pluginUrl = "http://marketplace.eclipse.org/checkstyle";
		final boolean pluginIsEnable =  true;
		Plugin p = new Plugin(pluginUuid, pluginName, pluginUrl, pluginIsEnable);
		assertThatThrownBy(() -> p.updateDownloadProgress()).isInstanceOf(DownloadException.class)
		.hasMessage("Can't start downloading before retrieving the total size");
	}

}
