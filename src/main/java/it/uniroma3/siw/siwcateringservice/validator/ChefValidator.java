package it.uniroma3.siw.siwcateringservice.validator;

import it.uniroma3.siw.siwcateringservice.model.Chef;
import it.uniroma3.siw.siwcateringservice.service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

// https://docs.oracle.com/javaee/7/api/javax/validation/constraints/package-summary.html
@SuppressWarnings("ALL")
@Component
public class ChefValidator implements Validator {

	@Autowired
	private ChefService chefService;

	@Override
	public void validate (Object target, Errors errors) {
		if (this.chefService.hasDuplicate((Chef) target)) {
			errors.reject("chef.duplicate", "duplicate chef");
		}
	}

	@Override
	public boolean supports (Class<?> clazz) {
		return Chef.class.equals(clazz);
	}
}
