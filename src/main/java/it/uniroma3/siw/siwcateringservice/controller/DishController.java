package it.uniroma3.siw.siwcateringservice.controller;

import it.uniroma3.siw.siwcateringservice.model.Dish;
import it.uniroma3.siw.siwcateringservice.service.DishService;
import it.uniroma3.siw.siwcateringservice.service.IngredientService;
import it.uniroma3.siw.siwcateringservice.validator.DishValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
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

	@GetMapping("/dish/{id}")
	public String getDish(@PathVariable("id") Long id, Model model) {
		Dish d = this.dishService.findById(id);
		model.addAttribute("dish", d);
		model.addAttribute("dishIngredientsList", d.getIngredients());
		String nextPage = "dish.html";
		return nextPage;
	}

	// ADMIN ONLY
	@GetMapping("/admin/dishForm")
	public String getDishForm(Model model) {
		model.addAttribute("dish", new Dish());
		model.addAttribute("ingredientsList", ingredientService.findAll());
		String nextPage = "dishForm.html";
		return nextPage;
	}

	@PostMapping("/admin/dish")
	public String newDish(@Valid @ModelAttribute("dish") Dish dish,
			BindingResult bindingResult, Model model) {
		this.dishValidator.validate(dish, bindingResult);
		String nextPage;
		if (!bindingResult.hasErrors()) { // se i dati sono corretti
			this.dishService.save(dish); // salvo un oggetto Dish
			model.addAttribute("dish", dish);
			nextPage = "dish.html"; // presenta un pagina con la dish appena salvata
		} else {
			model.addAttribute("ingredientsList", ingredientService.findAll());
			nextPage = "dishForm.html"; // ci sono errori, torna alla form iniziale
		}
		return nextPage;
	}

	@GetMapping("/admin/editDishForm/{id}")
	public String getDishForm(@PathVariable Long id, Model model) {
		model.addAttribute("dish", dishService.findById(id));
		model.addAttribute("ingredientsList", ingredientService.findAll());
		String nextPage = "editDishForm.html";
		return nextPage;
	}

	@Transactional
	@PostMapping("/admin/edit/dish/{id}")
	public String editDish(@PathVariable Long id, @Valid @ModelAttribute("dish") Dish dish, BindingResult bindingResults, Model model) {
			Dish oldDish = dishService.findById(id);
			if (! oldDish.equals(dish))
				this.dishValidator.validate(dish, bindingResults);
		String nextPage;
		if(!bindingResults.hasErrors()) {
			oldDish.setId(dish.getId());
			oldDish.setName(dish.getName());
			oldDish.setDescription(dish.getDescription());
			this.dishService.save(oldDish);
			model.addAttribute("dish", dish);
			nextPage = "dish.html";
		} else {
			model.addAttribute("ingredientsList", ingredientService.findAll());
			nextPage = "editDishForm.html";
		}
		return nextPage;
	}

	@PostMapping("/admin/remove/ask/dish/{id}")
	public String askRemoveDishById(@PathVariable("id") Long id, Model model) {
		model.addAttribute("dish", this.dishService.findById(id));
		String nextPage = "dishConfirmDelete.html";
		return nextPage;
	}

	@PostMapping("/admin/remove/confirm/dish/{id}")
	public String confirmRemoveDishById(@PathVariable("id") Long id, Model model) {
		String nextPage = "success.html";
		try {
			this.dishService.deleteDishById(id);
		} catch (Exception e) {
			nextPage = "error.html";
		}
		return nextPage;
	}
}
