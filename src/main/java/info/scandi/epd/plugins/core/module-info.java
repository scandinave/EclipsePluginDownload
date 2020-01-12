module info.scandi.epd.plugins.core {
	exports info.scandi.epd.plugins.core.dto;
	exports info.scandi.epd.plugins.core.usecases.createPlugin;
	exports info.scandi.epd.plugins.core.usecases.findPlugin;
	exports info.scandi.epd.plugins.core.exceptions;
	exports info.scandi.epd.plugins.core.interfaces;
	requires io.reactivex.rxjava2;
}