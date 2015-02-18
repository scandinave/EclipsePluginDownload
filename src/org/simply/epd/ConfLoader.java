package org.simply.epd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;

/*
 * (C) Copyright 2015 Scandinave www.scandi.info and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     LE BARO Romain
 */
public class ConfLoader {
	private String eclipseDir;
	private String pluginDir;
	private Integer verboseMode;
	private Map<String, String> plugins = new HashMap<String, String>();

	public String getEclipseDir() {
		return eclipseDir;
	}

	public void setEclipseDir(String eclipseDir) {
		this.eclipseDir = eclipseDir;
	}

	public String getPluginDir() {
		return pluginDir;
	}

	public void setPluginDir(String pluginDir) {
		this.pluginDir = pluginDir;
	}

	public Integer getVerboseMode() {
		return verboseMode;
	}

	public void setVerboseMode(Integer verboseMode) {
		this.verboseMode = verboseMode;
	}

	public Map<String, String> getPlugins() {
		return plugins;
	}

	public void setPlugins(Map<String, String> plugins) {
		this.plugins = plugins;
	}

	public void load(String filename) throws IOException, FileNotFoundException {
		try(FileInputStream fis = new FileInputStream(new File(filename))	; JsonReader rdr = Json.createReader(fis)) {
			JsonObject obj = rdr.readObject();
			JsonString pluginDir = obj.getJsonString("pluginDir");
			this.pluginDir = pluginDir.getString();
			JsonString eclipseDir = obj.getJsonString("eclipseDir");
			this.eclipseDir = eclipseDir.getString();
			JsonNumber verboseMode = obj.getJsonNumber("verbose");
			this.verboseMode = verboseMode.intValue();
			JsonArray plugins = obj.getJsonArray("plugins");
			for (JsonObject plugin : plugins.getValuesAs(JsonObject.class)) {
				if(plugin.isNull("enable") || plugin.getJsonNumber("enable").intValue() == 1) {
					String name = plugin.getString("name");
					String path = plugin.getString("path");
					this.plugins.put(name, path);
				}
			}
		}
	}
}
