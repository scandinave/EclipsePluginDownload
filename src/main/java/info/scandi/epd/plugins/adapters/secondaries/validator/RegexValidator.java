package info.scandi.epd.plugins.adapters.secondaries.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import info.scandi.epd.plugins.core.interfaces.Validator;

public class RegexValidator implements Validator {

	@Override
	public boolean isValidUrl(String url) {
		Pattern pattern = Pattern.compile("([https://|http://|ftp://|file://])+([www.])*\\w+\\.\\w+\\D+");
        Matcher matcher = pattern.matcher(url);
		return matcher.matches();
	}

	@Override
	public boolean isValidUuid(String uuid) {
		Pattern pattern = Pattern.compile("[0-9a-f]{8}\\-[0-9a-f]{4}\\-[0-9a-f]{4}\\-[0-9a-f]{4}\\-[0-9a-f]{12}");
        Matcher matcher = pattern.matcher(uuid);
		return matcher.matches();
	}
	
	@Override
	public boolean isValidPluginName(String pluginName) {
		Pattern pattern = Pattern.compile("[A-Z][a-z]+(?:[A-Z][a-z0-9]+)*");
        Matcher matcher = pattern.matcher(pluginName);
		return matcher.matches();
	}

}
