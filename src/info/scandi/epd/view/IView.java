package info.scandi.epd.view;

import info.scandi.epd.controller.IController;

public interface IView {
	public void render();

	public void setController(IController controller);
}
