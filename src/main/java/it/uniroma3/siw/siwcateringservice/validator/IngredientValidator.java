package it.uniroma3.siw.siwcateringservice.validator;


import it.uniroma3.siw.siwcateringservice.model.Ingredient;
import it.uniroma3.siw.siwcateringservice.model.Persona;
import it.uniroma3.siw.siwcateringservice.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

// https://docs.oracle.com/javaee/7/api/javax/validation/constraints/package-summary.html
@Component
public class IngredientValidator implements Validator {

	@Autowired
	private IngredientService ingredientService;

	@Override
	public void validate (Object target, Errors errors) {
		if (this.ingredientService.hasDuplicate((Ingredient) target)){
			errors.reject("ingredient.duplicate");
		}
	}

	@Override
	public boolean supports (Class<?> clazz) {
		return Ingredient.class.equals(clazz);
	}
}
