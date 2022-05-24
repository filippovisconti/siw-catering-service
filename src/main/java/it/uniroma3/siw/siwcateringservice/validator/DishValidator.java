package it.uniroma3.siw.siwcateringservice.validator;


import it.uniroma3.siw.siwcateringservice.model.Dish;
import it.uniroma3.siw.siwcateringservice.model.Ingredient;
import it.uniroma3.siw.siwcateringservice.model.Persona;
import it.uniroma3.siw.siwcateringservice.service.DishService;
import it.uniroma3.siw.siwcateringservice.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

// https://docs.oracle.com/javaee/7/api/javax/validation/constraints/package-summary.html
@Component
public class DishValidator implements Validator {

	@Autowired
	private DishService dishService;

	@Override
	public void validate (Object target, Errors errors) {
		if (this.dishService.hasDuplicate((Dish) target)){
			errors.reject("dish.duplicate", "duplicate dish, damn");
		}
		/*if (((Dish) target).getIngredients().isEmpty() ||((Dish) target).getIngredients() == null){
			errors.reject("dish.no_ingredients", "no ingredients");
		}

		 */
	}

	@Override
	public boolean supports (Class<?> clazz) {
		return Dish.class.equals(clazz);
	}
}
