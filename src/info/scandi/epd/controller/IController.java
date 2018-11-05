package info.scandi.epd.controller;

import info.scandi.epd.view.IView;
import model.IModel;

public interface IController {

	public void setModel(IModel model);

	public void setView(IView view);

	public void init() throws Exception;
}
