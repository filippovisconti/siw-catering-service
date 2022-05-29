package it.uniroma3.siw.siwcateringservice.controller;

import it.uniroma3.siw.siwcateringservice.model.Buffet;
import it.uniroma3.siw.siwcateringservice.service.BuffetService;
import it.uniroma3.siw.siwcateringservice.service.ChefService;
import it.uniroma3.siw.siwcateringservice.service.DishService;
import it.uniroma3.siw.siwcateringservice.validator.BuffetValidator;
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
public class BuffetController {

	@Autowired
	private BuffetService buffetService;

	@Autowired
	private DishService dishService;

	@Autowired
	private ChefService chefService;

	@Autowired
	private BuffetValidator buffetValidator;

	@GetMapping("/buffets")
	public String getAllBuffets(Model model) {
		List<Buffet> buffets = buffetService.findAll();
		model.addAttribute("buffets", buffets);
		String nextPage = "buffets.html";
		return nextPage;
	}

	@GetMapping("/buffetForm")
	public String getBuffetForm(Model model) { // NON FUNZIONA
		model.addAttribute("buffet", new Buffet());
		model.addAttribute("chefsList", chefService.findAll());
		model.addAttribute("dishesList", dishService.findAll());
		String nextPage = "buffetForm.html";
		return nextPage;
	}

	@PostMapping("/buffet")
	public String newBuffet (@Valid @ModelAttribute("buffet") Buffet buffet,
							 BindingResult bindingResult, Model model) {
		this.buffetValidator.validate(buffet,bindingResult);
		String nextPage;
		if (!bindingResult.hasErrors()) { // se i dati sono corretti
			this.buffetService.save(buffet); // salvo un oggetto Buffet
			model.addAttribute("buffet", buffet);
			nextPage = "buffet.html";	  // presenta un pagina con la buffet appena salvata
		} else {
			model.addAttribute("dishesList", dishService.findAll());
			model.addAttribute("chefsList", chefService.findAll());
			nextPage = "buffetForm.html"; // ci sono errori, torna alla form iniziale
		}
		return nextPage;
	}

	@GetMapping("/buffet/{id}")
	public String getBuffet(@PathVariable("id") Long id, Model model) {
		Buffet d = this.buffetService.findById(id);
		model.addAttribute("buffet", d);
		model.addAttribute("buffetDishesList", d.getOfferedDishes());
		String nextPage = "buffet.html";
		return nextPage;
	}

	@GetMapping("/editBuffetForm/{id}")
	public String getBuffetForm(@PathVariable Long id, Model model) {
		model.addAttribute("buffet", buffetService.findById(id));
		model.addAttribute("chefsList", chefService.findAll());
		model.addAttribute("dishesList", dishService.findAll());
		String nextPage = "editBuffetForm.html";
		return nextPage;
	}

	@Transactional
	@PostMapping("/edit/buffet/{id}")
	public String editBuffet(@PathVariable Long id, @Valid @ModelAttribute("buffet") Buffet buffet, BindingResult bindingResults, Model model) {
		String nextPage;
		if(!bindingResults.hasErrors()) {
			Buffet oldBuffet = buffetService.findById(id);
			oldBuffet.setId(buffet.getId());
			oldBuffet.setName(buffet.getName());
			oldBuffet.setDescription(buffet.getDescription());
			oldBuffet.setChef(buffet.getChef());
			oldBuffet.setOfferedDishes(buffet.getOfferedDishes());
			this.buffetService.save(oldBuffet);
			model.addAttribute("buffet", buffet);
			nextPage = "buffet.html";	  // presenta un pagina con la buffet appena salvata
		} else {
			model.addAttribute("dishesList", dishService.findAll());
			model.addAttribute("chefsList", chefService.findAll());
			nextPage = "editBuffetForm.html"; // ci sono errori, torna alla form iniziale
			}
		return nextPage;
	}

	@PostMapping("/remove/ask/buffet/{id}")
	public String askRemoveBuffetById(@PathVariable("id") Long id, Model model) {
		model.addAttribute("buffet", this.buffetService.findById(id));
		String nextPage = "buffetConfirmDelete.html";
		return nextPage;
	}

	@PostMapping("/remove/confirm/buffet/{id}")
	public String confirmRemoveBuffetById(@PathVariable("id") Long id, Model model) {
		this.buffetService.deleteBuffetById(id);
		String nextPage = "success.html";
		return nextPage;
	}
}
