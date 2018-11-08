package info.scandi.epd.view;

public class DownloadConsoleView implements IDownloadView {

	@Override
	public void render(String message) {
		System.out.println(message);
	}

	@Override
	public void renderError(Throwable error) {
		System.err.println(error.getMessage());
	}

}
