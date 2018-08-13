package org.simply.epd;

import java.io.IOException;
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
		//new EclipsePluginDownloader(args[0]).init();;
		try {
			new EclipsePluginDownloader("/Users/ninja/Development/Project/java/EclipsePluginDownload/src/config.json").init();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Terminated");
	}

}
