package it.uniroma3.siw.siwcateringservice.controller;

import it.uniroma3.siw.siwcateringservice.model.Ingredient;
import it.uniroma3.siw.siwcateringservice.service.IngredientService;
import it.uniroma3.siw.siwcateringservice.validator.IngredientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class IngredientController {
	@Autowired
	private IngredientService ingredientService;

	@Autowired
	private IngredientValidator ingredientValidator;

	@GetMapping("/ingredients")
	public String getAllIngredients(Model model) {
		List<Ingredient> ingredients = ingredientService.findAll();
		model.addAttribute("ingredients", ingredients);
		String nextPage = "ingredients.html";
		return nextPage;
	}

	@GetMapping("/ingredientForm")
	public String getIngredientForm(Model model) {
		model.addAttribute("ingredient", new Ingredient());
		String nextPage = "ingredientForm.html";
		return nextPage;
	}

	@PostMapping("/ingredient")
	public String newIngredient (@Valid @ModelAttribute("ingredient") Ingredient ingredient, BindingResult bindingResult, Model model) {
	this.ingredientValidator.validate(ingredient,bindingResult);
		String nextPage;
		if (!bindingResult.hasErrors()) { // se i dati sono corretti
			this.ingredientService.save(ingredient); // salvo un oggetto Ingredient
			model.addAttribute("ingredient", this.ingredientService.findById(ingredient.getId()));
			nextPage = "ingredient.html";	  // presenta un pagina con la ingredient appena salvata
		} else
			nextPage = "ingredientForm.html"; // ci sono errori, torna alla form iniziale
		return nextPage;
	}

	@GetMapping("/ingredient/{id}")
	public String getIngredient(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ingredient", this.ingredientService.findById(id));
		String nextPage = "ingredient.html";
		return nextPage;
	}

	@PostMapping("/remove/ask/ingredient/{id}")
	public String askRemoveIngredientById(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ingredient", this.ingredientService.findById(id));
		String nextPage = "ingredientConfirmDelete.html";
		return nextPage;
	}

	@PostMapping("/remove/confirm/ingredient/{id}")
	public String confirmRemoveIngredientById(@PathVariable("id") Long id, Model model) {
		this.ingredientService.deleteIngredientById(id);
		String nextPage = "success.html";
		return nextPage;
	}
}
