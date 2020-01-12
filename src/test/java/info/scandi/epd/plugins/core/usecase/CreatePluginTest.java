package info.scandi.epd.plugins.core.usecase;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.fasterxml.uuid.Generators;

import info.scandi.epd.plugins.adapters.secondaries.validator.RegexValidator;
import info.scandi.epd.plugins.core.interfaces.Validator;
import info.scandi.epd.plugins.core.usecases.createPlugin.CreatePlugin;
import info.scandi.epd.plugins.core.usecases.createPlugin.CreatePluginDTO;

@DisplayName("Plugin creation")
public class CreatePluginTest {

	private CreatePlugin createPluginUseCase;
	
	@BeforeEach
	public void beforeEach() {
		Validator validator = new RegexValidator();
		this.createPluginUseCase = new CreatePlugin(validator);
	}
	
	
	@DisplayName("Should work if all info are correct")
	@Test
	void createPlugin() {
		try {
			createPluginUseCase.add(new CreatePluginDTO(
						Generators.timeBasedGenerator().generate().toString(), 
						"Checkstyle", "http://marketplace.eclipse.org/checstyle", 
						true
				)
			);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@DisplayName("Should not validate name when ")
	@Nested
	class notValidateName {
		
		@DisplayName("containing whitespace")
		@Test
		void whitespace() {
				
				// white space test
				assertThatThrownBy(() -> createPluginUseCase.add(new CreatePluginDTO(
							Generators.timeBasedGenerator().generate().toString(), 
							"Check style", "http://marketplace.eclipse.org/checstyle", 
							true
				))).hasMessage("Some values are incorrects. Please check getErrors for more informations");
				
		}
				
		@DisplayName("containing non ascci char")
		@Test
		void nonAscci() {		
				//SpeialCaractere
				assertThatThrownBy(() -> createPluginUseCase.add(new CreatePluginDTO(
						Generators.timeBasedGenerator().generate().toString(), 
						"äéàèù", "http://marketplace.eclipse.org/checstyle", 
						true
				))).hasMessage("Some values are incorrects. Please check getErrors for more informations");
				
		}
		
		@DisplayName("is in camelCase form")
		@Test
		void camelCase() {
				//camelCase
				assertThatThrownBy(() -> createPluginUseCase.add(new CreatePluginDTO(
						Generators.timeBasedGenerator().generate().toString(), 
						"camelCase", "http://marketplace.eclipse.org/checstyle", 
						true
				))).hasMessage("Some values are incorrects. Please check getErrors for more informations");
				
		}		
	}
	
	@DisplayName("should validate name when")
	@Nested
	class validateName {
		@DisplayName("is in PascalCase form")
		@Test
		void PascalCase() {
				assertThatCode(() -> createPluginUseCase.add(new CreatePluginDTO(
						Generators.timeBasedGenerator().generate().toString(), 
						"CheckStyle", "http://marketplace.eclipse.org/checstyle", 
						true
						))).doesNotThrowAnyException();
				
				assertThatCode(() -> createPluginUseCase.add(new CreatePluginDTO(
						Generators.timeBasedGenerator().generate().toString(), 
						"Checkstyle", "http://marketplace.eclipse.org/checstyle", 
						true
						))).doesNotThrowAnyException();
		}
	}
	
	@DisplayName("Should not validate url when ")
	@Nested
	class notValidateUrl {
		
		@DisplayName("not containing protocole")
		@Test
		void whitespace() {
				
				// white space test
				assertThatThrownBy(() -> createPluginUseCase.add(new CreatePluginDTO(
							Generators.timeBasedGenerator().generate().toString(), 
							"CheckStyle", "marketplace.eclipse.org/checstyle", 
							true
				))).hasMessage("Some values are incorrects. Please check getErrors for more informations");
				
		}
				
		@DisplayName("when not containing uri")
		@Test
		void nonAscci() {		
				//SpeialCaractere
				assertThatThrownBy(() -> createPluginUseCase.add(new CreatePluginDTO(
						Generators.timeBasedGenerator().generate().toString(), 
						"CheckStyle", "http://.org/checstyle", 
						true
				))).hasMessage("Some values are incorrects. Please check getErrors for more informations");
				
		}
		
		@DisplayName("when containing bad charactere")
		@Test
		void camelCase() {
				//camelCase
				assertThatThrownBy(() -> createPluginUseCase.add(new CreatePluginDTO(
						Generators.timeBasedGenerator().generate().toString(), 
						"CheckStyle", "http://marketplace.[eclipse].org/checstyle", 
						true
				))).hasMessage("Some values are incorrects. Please check getErrors for more informations");
				
		}		
	}
	
	@DisplayName("should validate url when")
	@Nested
	class validateUrl {
		@DisplayName("url is correct")
		@Test
		void PascalCase() {
				assertThatCode(() -> createPluginUseCase.add(new CreatePluginDTO(
						Generators.timeBasedGenerator().generate().toString(), 
						"CheckStyle", "http://marketplace.eclipse.org/checkstyle", 
						true
						))).doesNotThrowAnyException();
				
				assertThatCode(() -> createPluginUseCase.add(new CreatePluginDTO(
						Generators.timeBasedGenerator().generate().toString(), 
						"Checkstyle", "http://marketplace.eclipse.org/checkstyle?test[toto]=tutu", 
						true
						))).doesNotThrowAnyException();
		}
	}
	
	@DisplayName("Should not validate uuid when ")
	@Nested
	class notValidateUuid {
		
		@DisplayName("format is wrong")
		@Test
		void whitespace() {
				
				// white space test
				assertThatThrownBy(() -> createPluginUseCase.add(new CreatePluginDTO(
							"toto", 
							"CheckStyle", "http://marketplace.eclipse.org/checkstyle", 
							true
				))).hasMessage("Some values are incorrects. Please check getErrors for more informations");
				
		}
				
		@DisplayName("when containing characters A-Z")
		@Test
		void nonAscci() {		
				//SpeialCaractere
				assertThatThrownBy(() -> createPluginUseCase.add(new CreatePluginDTO(
						"Ac2ef5a5-123e-cbd3-cfe9-a2fb5cd356cc", 
						"CheckStyle", "http://marketplace.eclipse.org/checstyle", 
						true
				))).hasMessage("Some values are incorrects. Please check getErrors for more informations");
				
		}
		
		@DisplayName("when containing characters other than 0-9 and a-f")
		@Test
		void camelCase() {
				//camelCase
				assertThatThrownBy(() -> createPluginUseCase.add(new CreatePluginDTO(
						"gc2ef5a5-123e-cbd3-cfe9-a2fb5cd356cc", 
						"CheckStyle", "http://marketplace.eclipse.org/checstyle", 
						true
				))).hasMessage("Some values are incorrects. Please check getErrors for more informations");
				
		}		
	}
	
	@DisplayName("should validate uuid when")
	@Nested
	class validateUuid {
		@DisplayName("form is correct")
		@Test
		void PascalCase() {
				assertThatCode(() -> createPluginUseCase.add(new CreatePluginDTO(
						Generators.timeBasedGenerator().generate().toString(), 
						"CheckStyle", "http://marketplace.eclipse.org/checstyle", 
						true
						))).doesNotThrowAnyException();
		}
	}
}
