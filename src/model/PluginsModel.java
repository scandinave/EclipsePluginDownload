package model;

import java.util.HashMap;
import java.util.Map;

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
public class PluginsModel implements IModel {
	private String eclipseDir;
	private String pluginDir;
	private Integer verboseMode;
	private Map<String, String> plugins = new HashMap<String, String>();

	public String getEclipseDir() {
		return this.eclipseDir;
	}

	public void setEclipseDir(String eclipseDir) {
		this.eclipseDir = eclipseDir;
	}

	public String getPluginDir() {
		return this.pluginDir;
	}

	public void setPluginDir(String pluginDir) {
		this.pluginDir = pluginDir;
	}

	public Integer getVerboseMode() {
		return this.verboseMode;
	}

	public void setVerboseMode(Integer verboseMode) {
		this.verboseMode = verboseMode;
	}

	public Map<String, String> getPlugins() {
		return this.plugins;
	}

	public void setPlugins(Map<String, String> plugins) {
		this.plugins = plugins;
	}

	public void addPlugin(String name, String path) {
		this.plugins.put(name, path);
	}
}
