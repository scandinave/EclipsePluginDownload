package info.scandi.epd.plugins.core.entities;

import info.scandi.epd.plugins.core.dto.PluginDTO;
import info.scandi.epd.plugins.core.exceptions.DownloadException;

public class Plugin {

	private String id;
	private String name;
	private String url;
	private boolean enabled;
	private Long totalSize;
	private Long downloadedSize;
	
	public Plugin(String id, String name, String url, boolean isEnabled) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.enabled = isEnabled; 
		this.totalSize = 0L;
		this.downloadedSize = 0L;
	}

	/**
	 * Id as UUIDv1
	 * @return
	 */
	public String getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	/**
	 * Return the state of the plugin (enable or disable)
	 * @return Return true if the plugin is enabled (aka: will be download) false otherwise
	 */
	public boolean isEnabled() {
		return this.enabled;
	}

	public long getTotalSize() {
		return this.totalSize;
	}

	public long getDownloadedSize() {
		return this.downloadedSize;
	}
	
	/**
	 * Return the progress of the plugin download in pourcent
	 * If totalSize = 0 then return 0.
	 */
	public float getPourcentDownloadedSize() {
		if(this.totalSize != 0) {
			return 100 * this.downloadedSize / this.totalSize;
		} else {
			 return 0;
		}
	}

	/**
	 * Set the total size of the plugin in any relevant unit. ( kilo-octet, file count ... )
	 * @param totalSize The total size of the plugin
	 */
	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}

	/**
	 * Add x unit to the downloadSize. Use this method if the plugin size grow by multiple unit.
	 * Use updateDownloadProgress(long progress) otherwise.
	 */
	public void updateDownloadProgress(long l) {
		if(this.totalSize != 0) {
			this.downloadedSize+=l;
		} else {
			throw new DownloadException("Can't start downloading before retrieving the total size");
		}
		
	}
	
	/**
	 * Add one unit to the downloadSize. Use this method if the plugin content is download one by one.
	 * Use updateDownloadProgress(long progress) otherwise.
	 * @see updateDownloadProgress(long progress)
	 */
	public void updateDownloadProgress() {
		if(this.totalSize != 0) {
			this.downloadedSize++;
		} else {
			throw new DownloadException("Can't start downloading before retrieving the total size");
		}
		
	}

}
