package info.scandi.epd.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import info.scandi.epd.model.AppModel;
import info.scandi.epd.service.FileService;

public class DownloadController {

	private AppModel appModel = new AppModel();
	private FileService fileService = new FileService();

	public DownloadController(String filename) throws FileNotFoundException, IOException {
		this.appModel = this.fileService.load(filename);
	}

	public void downloadPlugins() {
		ExecutorService service = Executors.newFixedThreadPool(8);
		this.appModel.getPlugins().forEach(plugin -> {
			Runnable r = () -> {
				plugin.download(this.appModel.getPluginDir(), this.appModel.getEclipseDir(),
						this.appModel.getVerboseMode()).subscribe(next -> {
							System.out.println(next);
						}, error -> {
							System.err.println(error);
						});
			};
			service.execute(r);
		});
		service.shutdown();
	}
}
