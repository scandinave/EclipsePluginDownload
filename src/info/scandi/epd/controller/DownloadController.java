package info.scandi.epd.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import info.scandi.epd.model.AppModel;
import info.scandi.epd.service.FileService;
import info.scandi.epd.view.IDownloadView;

public class DownloadController {

	private AppModel appModel = new AppModel();
	private FileService fileService = new FileService();
	private IDownloadView view = null;

	public DownloadController(String filename, IDownloadView view) throws FileNotFoundException, IOException {
		this.appModel = this.fileService.load(filename);
		this.view = view;
	}

	public void downloadPlugins() {
		ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		this.appModel.getPlugins().forEach(plugin -> {
			Runnable r = () -> {
				plugin.download(this.appModel.getPluginDir(), this.appModel.getEclipseDir(),
						this.appModel.getVerboseMode()).subscribe(next -> {
							this.view.render(next);
						}, error -> {
							this.view.renderError(error);
						});
			};
			service.execute(r);
		});
		service.shutdown();
	}
}
