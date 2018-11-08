package info.scandi.epd.view;

public interface IDownloadView {
	public void render(String message);

	public void renderError(Throwable error);
}
