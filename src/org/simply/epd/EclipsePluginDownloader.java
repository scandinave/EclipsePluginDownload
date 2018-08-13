package org.simply.epd;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Scanner;
import java.util.Set;

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
public class EclipsePluginDownloader implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6974855946687584862L;

	private String fileConfPath;
	
	private ConfLoader conf;
	
	public EclipsePluginDownloader(String fileConfPath) {
		this.fileConfPath = fileConfPath;
	}
	
	public void init() throws IOException {
		downloadPlugins();
	}
	
	private void checkDirExist(String dirPath) throws IOException {
		File dir = new File(dirPath);
		if (!dir.isDirectory() && !dir.mkdirs()) {
	        throw new IOException("Error creating new file: " + dir.getAbsolutePath());
	    }
	}
	
	private int getFileCountInDir(String dirpath) {
		File dir = new File(dirpath);
		int count = 0;
		for(File file : dir.listFiles()) {
			if(file.isFile()) {
				count++;
			} else {
				count += getFileCountInDir(file.getPath());
			}
		}
		
		return  count;
	}
	
	private void loadPluginToDownload() {
		try {
			this.conf = new ConfLoader();
			this.conf.load(fileConfPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void downloadPlugins() throws IOException {
		loadPluginToDownload();
		Set<String> pluginNames = this.conf.getPlugins().keySet();
		String ECLIPSECPATH = this.conf.getEclipseDir();
		for (String pluginName : pluginNames) {
			System.out.println("Downloading plugin " + pluginName);
			Integer fileCount;
			String dirPath = this.conf.getPluginDir()  + File.separator + pluginName.replace(" ", "_");
			checkDirExist(dirPath);
			do {
				fileCount = getFileCountInDir(dirPath);
				String path = this.conf.getPlugins().get(pluginName);
				StringBuilder command = new StringBuilder(ECLIPSECPATH + "eclipse -nosplash -verbose -application org.eclipse.equinox.p2.metadata.repository.mirrorApplication");
				command.append(" -source " + path);
				command.append(" -destination " + this.conf.getPluginDir()  + File.separator + pluginName);
				System.out.println("Command to execute : " + command.toString());
				executCommand(command.toString());
				executCommand(command.toString().replace("metadata", "artifact"));
				System.out.println("NB Fichier avant = " + getFileCountInDir(dirPath));
				System.out.println("NB Fichier Apres = " + fileCount);
				System.out.println("Condition boucle = " + (getFileCountInDir(dirPath) != fileCount));
			} while(getFileCountInDir(dirPath) != fileCount);
		}
		
	}
	
	private void executCommand(String command) {
		System.out.println("Executed commande");
		Scanner sc = null;
		try{
			Process p = Runtime.getRuntime().exec(command);
			if(this.conf.getVerboseMode() == 1) {
				sc = new Scanner(p.getInputStream());    		
    			while (sc.hasNext()) System.out.println(sc.nextLine());
			}
		} catch( IOException e) {
            e.printStackTrace();
		} finally {
			if(sc != null) {
				sc.close();
			}
		}
		
	}
	
}
