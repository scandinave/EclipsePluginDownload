package info.scandi.epd.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;

import info.scandi.epd.model.AppModel;
import info.scandi.epd.model.PluginModel;

public class FileService {

	/**
	 * Create directory recursively.
	 *
	 * @param dirPath The path of the directory to create
	 * @throws IOException
	 */
	public void createDir(String dirPath) throws IOException {
		File dir = new File(dirPath);
		if (!dir.isDirectory() && !dir.mkdirs()) {
			throw new IOException("Error creating new file: " + dir.getAbsolutePath());
		}
	}

	/**
	 * Return the number of file into a directory
	 *
	 * @param dirpath The path of the directory
	 * @return
	 */
	public int getFileCountInDir(String dirpath) {
		File dir = new File(dirpath);
		int count = 0;
		for (File file : dir.listFiles()) {
			if (file.isFile()) {
				count++;
			} else {
				count += this.getFileCountInDir(file.getPath());
			}
		}

		return count;
	}

	public AppModel load(String filename) throws IOException, FileNotFoundException {
		AppModel model = new AppModel();
		try (FileInputStream fis = new FileInputStream(new File(filename)); JsonReader rdr = Json.createReader(fis)) {
			JsonObject obj = rdr.readObject();
			JsonString pluginDir = obj.getJsonString("pluginDir");
			model.setPluginDir(pluginDir.getString());
			JsonString eclipseDir = obj.getJsonString("eclipseDir");
			model.setEclipseDir(eclipseDir.getString());
			JsonNumber verboseMode = obj.getJsonNumber("verbose");
			model.setVerboseMode(verboseMode.intValue());
			JsonArray plugins = obj.getJsonArray("plugins");
			for (JsonObject plugin : plugins.getValuesAs(JsonObject.class)) {
				if (plugin.isNull("enable") || plugin.getJsonNumber("enable").intValue() == 1) {
					String name = plugin.getString("name");
					String path = plugin.getString("path");
					model.addPlugin(new PluginModel(name, path));
				}
			}
		}

		return model;
	}
}
