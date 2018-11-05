package info.scandi.epd.view;

import java.util.Map;
import java.util.concurrent.Flow.Subscription;

import info.scandi.epd.controller.IController;

public class DownloadConsoleView extends AbstractView<Map<String, Object>> {

	private IController controller;

	@Override
	public void render() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setController(IController controller) {
		this.controller = controller;
	}

	@Override
	public void onComplete() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onError(Throwable err) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSubscribe(Subscription subscription) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNext(Map<String, Object> data) {
		// TODO Auto-generated method stub

	}

}
