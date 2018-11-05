package info.scandi.epd.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SubmissionPublisher;

public class DownloaderService extends SubmissionPublisher<String> {

	public void process(Map<String, String> plugins) {

		ExecutorService executor = Executors.newFixedThreadPool(8);
		List<Callable<String>> callables = new ArrayList<Callable<String>>();

		plugins.forEach((String key, String value) -> {
			callables.add(this.processP2());
		});

		this.submit("toto");
	}

	private Callable<String> processP2() {
		return () -> {
			return "toto";
		};
	}
}
