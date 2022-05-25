package it.uniroma3.siw.siwcateringservice.controller;

import it.uniroma3.siw.siwcateringservice.model.Dish;
import it.uniroma3.siw.siwcateringservice.model.DishCreator;
import it.uniroma3.siw.siwcateringservice.model.Ingredient;
import it.uniroma3.siw.siwcateringservice.service.DishService;
import it.uniroma3.siw.siwcateringservice.service.IngredientService;
import it.uniroma3.siw.siwcateringservice.validator.DishValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DishController {
	@Autowired
	private DishService dishService;

	@Autowired
	private IngredientService ingredientService;

	@Autowired
	private DishValidator dishValidator;

	@GetMapping("/dishes")
	public String getAllDishes(Model model) {
		List<Dish> dishes = dishService.findAll();
		model.addAttribute("dishes", dishes);
		String nextPage = "dishes.html";
		return nextPage;
	}

	@GetMapping("/dishForm")
	public String getDishForm(Model model) { // NON FUNZIONA
//		var d = new DishCreator();
		//d.setIngredients(new ArrayList<>(ingredientService.findAll()));
		model.addAttribute("dish", new Dish());
		model.addAttribute("ingredientsList", ingredientService.findAll());
		//model.addAttribute("ingredientList", new ArrayList<IngredientController>());
		String nextPage = "dishForm.html";
		return nextPage;
	}

	@PostMapping("/dish")
	public String newDish (@Valid @ModelAttribute("dish") Dish dish, BindingResult bindingResult, Model model) {
//		var dish = new Dish(dishCreator);
		this.dishValidator.validate(dish,bindingResult);
		String nextPage;
		if (!bindingResult.hasErrors()) { // se i dati sono corretti
			this.dishService.save(dish); // salvo un oggetto Dish
			var d = this.dishService.findById(dish.getId());
			model.addAttribute("dish", d);
			model.addAttribute("dishIngredientsList", ingredientService.findByIds(d.getIngredients()));
			nextPage = "dish.html";	  // presenta un pagina con la dish appena salvata
		} else {
			model.addAttribute("ingredientsList", ingredientService.findAll());
			nextPage = "dishForm.html"; // ci sono errori, torna alla form iniziale
		}
		return nextPage;
	}

	@GetMapping("/dish/{id}")
	public String getDish(@PathVariable("id") Long id, Model model) {
		Dish d = this.dishService.findById(id);
		model.addAttribute("dish", d);
		model.addAttribute("dishIngredientsList", ingredientService.findByIds(d.getIngredients()));
		String nextPage = "dish.html";
		return nextPage;
	}

	@PostMapping("/remove/ask/dish/{id}")
	public String askRemoveDishById(@PathVariable("id") Long id, Model model) {
		model.addAttribute("dish", this.dishService.findById(id));
		String nextPage = "dishConfirmDelete.html";
		return nextPage;
	}

	@PostMapping("/remove/confirm/dish/{id}")
	public String confirmRemoveDishById(@PathVariable("id") Long id, Model model) {
		this.dishService.deleteDishById(id);
		String nextPage = "success.html";
		return nextPage;
	}
}
