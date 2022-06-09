package it.uniroma3.siw.siwcateringservice.controller;

import it.uniroma3.siw.siwcateringservice.model.Buffet;
import it.uniroma3.siw.siwcateringservice.model.Chef;
import it.uniroma3.siw.siwcateringservice.service.ChefService;
import it.uniroma3.siw.siwcateringservice.service.DishService;
import it.uniroma3.siw.siwcateringservice.validator.ChefValidator;
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
public class ChefController {
	@Autowired
	private ChefService chefService;

	@Autowired
	private DishService dishService;

	@Autowired
	private ChefValidator chefValidator;

	@GetMapping("/chefs")
	public String getAllChefs(Model model) {
		List<Chef> chefs = chefService.findAll();
		model.addAttribute("chefs", chefs);
		String nextPage = "chefs.html";
		return nextPage;
	}

	@GetMapping("/chef/{id}")
	public String getChef(@PathVariable("id") Long id, Model model) {
		model.addAttribute("chef", this.chefService.findById(id));
		String nextPage = "chef.html";
		return nextPage;
	}

// ADMIN ONLY
	@GetMapping("/admin/chefForm")
	public String getChefForm(Model model) {
		model.addAttribute("chef", new Chef());
		String nextPage = "chefForm.html";
		return nextPage;
	}

	@PostMapping("/admin/chef")
	public String newChef (@Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResult, Model model) {
		this.chefValidator.validate(chef,bindingResult);
		String nextPage;
		if (!bindingResult.hasErrors()) { // se i dati sono corretti
			this.chefService.save(chef); // salvo un oggetto Chef
			model.addAttribute("chef", this.chefService.findById(chef.getId()));
			nextPage = "chef.html";	  // presenta un pagina con la chef appena salvata
		} else
			nextPage = "chefForm.html"; // ci sono errori, torna alla form iniziale
		return nextPage;
	}
	@GetMapping("/admin/editChefForm/{id}")
	public String getChefForm(@PathVariable Long id, Model model) { // NON FUNZIONA
		model.addAttribute("chef", chefService.findById(id));
		String nextPage = "editChefForm.html";
		return nextPage;
	}

	@Transactional
	@PostMapping("/admin/edit/chef/{id}")
	public String editBuffet(@PathVariable Long id, @Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResults, Model model) {
		String nextPage;
		if(!bindingResults.hasErrors()) {
			Chef oldChef = chefService.findById(id);
			oldChef.setId(chef.getId());
			oldChef.setFirstName(chef.getFirstName());
			oldChef.setLastName(chef.getLastName());
			oldChef.setNationality(chef.getNationality());

			this.chefService.save(oldChef);
			model.addAttribute("chef", chef);
			nextPage = "chef.html";
		} else {
			nextPage = "editChefForm.html";
		}
		return nextPage;
	}

	@PostMapping("/admin/remove/ask/chef/{id}")
	public String askRemoveChefById(@PathVariable("id") Long id, Model model) {
		model.addAttribute("chef", this.chefService.findById(id));
		String nextPage = "chefConfirmDelete.html";
		return nextPage;
	}

	@PostMapping("/admin/remove/confirm/chef/{id}")
	public String confirmRemoveChefById(@PathVariable("id") Long id, Model model) {
		String nextPage = "success.html";
		try {
			this.chefService.deleteChefById(id);
		} catch (Exception e) {
			nextPage = "error.html";
		}
		return nextPage;
	}
}
