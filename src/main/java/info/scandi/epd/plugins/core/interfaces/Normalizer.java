package info.scandi.epd.plugins.core.interfaces;

public interface Normalizer {
	
	/**
	 * Normalize a string by removing whitespace, non assci charactere and returning it as PascalCase
	 * @param name The name to normalize
	 * @return The name after normalize process
	 */
	public String normalizeName(String name);
}
