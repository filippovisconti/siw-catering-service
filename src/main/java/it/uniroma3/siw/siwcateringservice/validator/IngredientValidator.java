package it.uniroma3.siw.siwcateringservice.validator;


import it.uniroma3.siw.siwcateringservice.model.Persona;
import it.uniroma3.siw.siwcateringservice.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

// https://docs.oracle.com/javaee/7/api/javax/validation/constraints/package-summary.html
@Component
public class PersonaValidator implements Validator {

	@Autowired
	private PersonaService personaService;

	@Override
	public void validate (Object target, Errors errors) {
		if (this.personaService.alreadyExists((Persona) target)){
			errors.reject("persona.duplicato");
		}
	}

	@Override
	public boolean supports (Class<?> clazz) {
		return Persona.class.equals(clazz);
	}
}
