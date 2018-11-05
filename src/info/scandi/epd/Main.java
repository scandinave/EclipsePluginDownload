package info.scandi.epd;

import info.scandi.epd.controller.DownloadController;
import info.scandi.epd.view.DownloadConsoleView;
import model.DownloadModel;

<<<<<<<HEAD:src/org/simply/epd/Main.java
import java.io.IOException;

=======>>>>>>>Refactor code to fit MVP:src/info/scandi/epd/Main.java

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
		DownloadModel model = new DownloadModel();
		DownloadConsoleView view = new DownloadConsoleView();
		DownloadController controller = new DownloadController();
		controller.setModel(model);
		model.subscribe(view);
//			new EclipsePluginDownloader("/Users/ninja/Development/Project/java/EclipsePluginDownload/src/config.json").init();
	}

}
