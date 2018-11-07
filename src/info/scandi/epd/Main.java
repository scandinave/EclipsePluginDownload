package info.scandi.epd;

import java.io.FileNotFoundException;
import java.io.IOException;

import info.scandi.epd.controller.DownloadController;

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
public class Main {

	public static void main(String[] args) {
		System.out.println("Starting app...");
		// new EclipsePluginDownloader(args[0]).init();;
		try {
			DownloadController controller = new DownloadController(
					"/home/scandinave/Development/Project/JAVA/EclipsePluginDownload/src/config.json");
			controller.downloadPlugins();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//			new EclipsePluginDownloader("/home/scandinave/Development/Project/JAVA/EclipsePluginDownload/src/config.json").init();
	}

}
