package info.scandi.epd.plugins.adapters.secondary.validator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import info.scandi.epd.plugins.adapters.secondaries.validator.RegexValidator;
import info.scandi.epd.plugins.core.interfaces.Validator;

@DisplayName("Validator")
public class RegexValidatorTest {
	
	private Validator validator = new RegexValidator();

	@DisplayName("PascalCase")
	@Nested
	class PascalCase {

		@DisplayName("should return true if string is in PascalCase")
		@Test
		public void pascalCaseTest() {
			assertThat(validator.isValidPluginName("PascalCase")).isTrue();
			assertThat(validator.isValidPluginName("PascalCase")).isTrue();
			assertThat(validator.isValidPluginName("PascalCaseTest")).isTrue();
			assertThat(validator.isValidPluginName("PascalCase2")).isTrue();
		}
		
		@DisplayName("should return false if string is in camelCase")
		@Test
		public void camelCaseTest() {
			assertThat(validator.isValidPluginName("camelCase")).isFalse();
		}
		
		@DisplayName("should return false if string is in dasheriedCase")
		@Test
		public void dasherizedCaseTest() {
			assertThat(validator.isValidPluginName("dasherized-case")).isFalse();
			
		}
		
		@DisplayName("should return false if string contain white space")
		@Test
		public void whiteSpaceCaseTest() {
			assertThat(validator.isValidPluginName("whitespace case")).isFalse();
			
		}
		
		@DisplayName("should return false if string characters others Ascii char")
		@Test
		public void noAsciiChar() {
			assertThat(validator.isValidPluginName("PâscalCase")).isFalse();
			assertThat(validator.isValidPluginName("PäscalCase")).isFalse();
			assertThat(validator.isValidPluginName("PöscalCase")).isFalse();
			assertThat(validator.isValidPluginName("PèscalCase")).isFalse();
			assertThat(validator.isValidPluginName("PéscalCase")).isFalse();
			assertThat(validator.isValidPluginName("PëscalCase")).isFalse();
			assertThat(validator.isValidPluginName("PêscalCase")).isFalse();
			assertThat(validator.isValidPluginName("PîscalCase")).isFalse();
			assertThat(validator.isValidPluginName("PïscalCase")).isFalse();
			assertThat(validator.isValidPluginName("PùscalCase")).isFalse();
			assertThat(validator.isValidPluginName("PüscalCase")).isFalse();
			
		}
		
	}
	
	@DisplayName("Url")
	@Nested
	class Url {
		@DisplayName("should return true if the string is an url")
		@Test
		public void correctUrl() {
			assertThat(validator.isValidUrl("http://foo.com")).isTrue();
			assertThat(validator.isValidUrl("http://www.foo.bar")).isTrue();
			assertThat(validator.isValidUrl("http://www.foo+.bar")).isTrue();
			assertThat(validator.isValidUrl("http://www.foo.bar/toto?tata=tutu&test=foo")).isTrue();
			assertThat(validator.isValidUrl("http://www.foo.bar/toto?tata[tuto]=tutu")).isTrue();
			assertThat(validator.isValidUrl("https://foo.com")).isTrue();
			assertThat(validator.isValidUrl("ftp://foo.bar")).isTrue();
			
		}
		
		@DisplayName("should return false if the string is not an url")
		@Test
		public void incorrectUrl() {
			assertThat(validator.isValidUrl("http://.com")).isFalse();
			assertThat(validator.isValidUrl("www.foo.bar")).isFalse();
			assertThat(validator.isValidUrl("http://www.[foo]+.bar")).isFalse();
		}
		
	}
	
	@DisplayName("Uuid")
	@Nested
	class Uuid {
		@DisplayName("should return true if the string is an uuid")
		@Test
		public void correctUrl() {
			assertThat(validator.isValidUuid("ac2ef5a5-123e-cbd3-cfe9-a2fb5cd356cc")).isTrue();
		}
		
		@DisplayName("should return false if the string is not an uuid")
		@Test
		public void incorrectUrl() {
			assertThat(validator.isValidUuid("Ac2ef5a5-123e-cbd3-cfe9-a2fb5cd356cc")).isFalse();
			assertThat(validator.isValidUuid("ac2ef5a5-123E-cbd3-cfe9-a2fb5cd356cc")).isFalse();
			assertThat(validator.isValidUuid("ac2ef5a5-123e-Cbd3-cfe9-a2fb5cd356cc")).isFalse();
			assertThat(validator.isValidUuid("ac2ef5a5-123e-cbd3-Cfe9-a2fb5cd356cc")).isFalse();
			assertThat(validator.isValidUuid("ac2ef5a5-123e-cbd3-cfe9-A2fb5cd356cc")).isFalse();
			assertThat(validator.isValidUuid("ac2ef5a5-123e-cbd3-cfe9-a2fb5")).isFalse();
		}
	}
	
}
