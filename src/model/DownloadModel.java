package model;

import java.util.List;
import java.util.Map;

/**
 * View that display the progression of the download.
 *
 * @author scandinave
 *
 */
public class DownloadModel extends AbstractModel<Map<String, Object>> {

	/**
	 * Number of plugin downloaded
	 */
	private int processed;
	/**
	 * Number of plugin to download
	 */
	private int total;

	/**
	 * The current plugins downloading
	 */
	private List<String> currentPlugins;

	/**
	 * Number of plugin downloaded
	 */
	public int getProcessed() {
		return this.processed;
	}

	public void setProcessed(int processed) {
		this.processed = processed;
	}

	/**
	 * The current plugins downloading
	 */
	public List<String> getCurrentPlugins() {
		return this.currentPlugins;
	}

	public void setCurrentPlugins(List<String> currentPlugins) {
		this.currentPlugins = currentPlugins;
	}

	/**
	 * Number of plugin to download
	 */
	public int getTotal() {
		return this.total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
