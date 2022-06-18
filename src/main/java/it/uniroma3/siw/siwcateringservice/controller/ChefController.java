package it.uniroma3.siw.siwcateringservice.controller;


import it.uniroma3.siw.siwcateringservice.model.Chef;
import it.uniroma3.siw.siwcateringservice.service.ChefService;
import it.uniroma3.siw.siwcateringservice.service.FileUploadService;
import it.uniroma3.siw.siwcateringservice.service.ImageService;
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

@SuppressWarnings("ALL")
@Controller
public class ChefController {
	@Autowired
	private ChefService chefService;

	@Autowired
	private ChefValidator chefValidator;

	@Autowired
	private FileUploadService fileUploadService;

	@Autowired
	private ImageService imageService;


	@GetMapping("/chefs")
	public String getAllChefs (Model model) {
		List<Chef> chefs = chefService.findAll();
		model.addAttribute("chefs", chefs);
		return "chefs.html";
	}

	@GetMapping("/chef/{id}")
	public String getChef (@PathVariable("id") Long id, Model model) {
		model.addAttribute("chef", this.chefService.findById(id));
		return "chef.html";
	}

	// ADMIN ONLY
	@GetMapping("/admin/chefForm")
	public String getChefForm (Model model) {
		model.addAttribute("chef", new Chef());
		return "chefForm.html";
	}

	@PostMapping("/admin/chef")
	public String newChef (@Valid @ModelAttribute("chef") Chef chef,
						   //@RequestParam("image") MultipartFile imageFile,
						   BindingResult bindingResult,
						   Model model) {
		this.chefValidator.validate(chef, bindingResult);
		String nextPage;
		if (!bindingResult.hasErrors()) { // se i dati sono corretti
			this.chefService.save(chef); // salvo un oggetto Chef
			model.addAttribute("chef", this.chefService.findById(chef.getId()));

			nextPage = "chef.html"; // presenta un pagina con la chef appena salvata
		} else
			nextPage = "chefForm.html"; // ci sono errori, torna alla form iniziale
		return nextPage;
	}

	@GetMapping("/admin/editChefForm/{id}")
	public String getChefForm (@PathVariable Long id, Model model) { // NON FUNZIONA
		model.addAttribute("chef", chefService.findById(id));
		return "editChefForm.html";
	}

	@Transactional
	@PostMapping("/admin/edit/chef/{id}")
	public String editChef (@PathVariable Long id, @Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResults, Model model) {
		Chef oldChef = chefService.findById(id);
		if (!oldChef.equals(chef))
			this.chefValidator.validate(chef, bindingResults);

		String nextPage;
		if (!bindingResults.hasErrors()) {
			oldChef.setId(chef.getId());
			oldChef.setFirstName(chef.getFirstName());
			oldChef.setLastName(chef.getLastName());
			oldChef.setNationality(chef.getNationality());

			this.chefService.save(oldChef);
			model.addAttribute("chef", oldChef);
			nextPage = "chef.html";
		} else {
			nextPage = "editChefForm.html";
		}
		return nextPage;
	}

	@PostMapping("/admin/remove/ask/chef/{id}")
	public String askRemoveChefById (@PathVariable("id") Long id, Model model) {
		model.addAttribute("chef", this.chefService.findById(id));
		return "chefConfirmDelete.html";
	}

	@PostMapping("/admin/remove/confirm/chef/{id}")
	public String confirmRemoveChefById (@PathVariable("id") Long id, Model model) {
		String nextPage = "success.html";
		try {
			this.chefService.deleteChefById(id);
		} catch (Exception e) {
			nextPage = "error.html";
		}
		return nextPage;
	}
}
