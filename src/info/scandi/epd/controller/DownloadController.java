package info.scandi.epd.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

import info.scandi.epd.service.DownloaderService;
import info.scandi.epd.service.FileService;
import info.scandi.epd.view.DownloadConsoleView;
import info.scandi.epd.view.IView;
import model.DownloadModel;
import model.IModel;
import model.PluginsModel;

public class DownloadController implements IController, Subscriber<String> {

	private DownloadModel model;
	private DownloadConsoleView view;
	private DownloaderService service;

	public DownloadController() {
		this.service = new DownloaderService();
	}

	@Override
	public void setModel(IModel model) {
		if (model instanceof PluginsModel) {
			this.model = (DownloadModel) model;
		} else {
			throw new Error("You must provide an instance of PluginsModel");
		}

	}

	@Override
	public void setView(IView view) {
		if (view instanceof DownloadConsoleView) {
			this.view = (DownloadConsoleView) view;
		} else {
			throw new Error("You mus provide an instance of ViewModel");
		}

	}

	@Override
	public void init() throws FileNotFoundException, IOException {
		PluginsModel pluginsModel = new FileService()
				.load("/Users/ninja/Development/Project/java/EclipsePluginDownload/src/config.json");
		this.service.subscribe(this);
		this.service.process(pluginsModel.getPlugins());

	}

	@Override
	public void onComplete() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onError(Throwable arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNext(String arg0) {
		this.model.
		this.view.render();

	}

	@Override
	public void onSubscribe(Subscription arg0) {
		// TODO Auto-generated method stub

	}

}
