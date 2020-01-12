package info.scandi.epd.plugins.core.dto;

public class PluginDTO {

	private final String name;
	private final String url;
	private final boolean enabled;
	private final Long totalSize;
	private final Long downloadedSize;
	private final Float downloadedPourcent;

	public PluginDTO(String name, String url, boolean enabled, Long totalSize, Long downloadedSize,
			Float downloadedPourcent) {
		this.name = name;
		this.url = url;
		this.enabled = enabled;
		this.totalSize = totalSize;
		this.downloadedSize = downloadedSize;
		this.downloadedPourcent = downloadedPourcent;
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

	public Long getTotalSize() {
		return totalSize;
	}

	public Long getDownloadedSize() {
		return downloadedSize;
	}

	public Float getDownloadedPourcent() {
		return downloadedPourcent;
	}
}
