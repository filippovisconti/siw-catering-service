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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

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
		String nextPage = "chefs.html";
		return nextPage;
	}

	@GetMapping("/chef/{id}")
	public String getChef (@PathVariable("id") Long id, Model model) {
		model.addAttribute("chef", this.chefService.findById(id));
		String nextPage = "chef.html";
		return nextPage;
	}

	// ADMIN ONLY
	@GetMapping("/admin/chefForm")
	public String getChefForm (Model model) {
		model.addAttribute("chef", new Chef());
		String nextPage = "chefForm.html";
		return nextPage;
	}

	@PostMapping("/admin/chef")
	public String newChef (@Valid @ModelAttribute("chef") Chef chef,
						   @RequestParam("image") MultipartFile imageFile,
						   BindingResult bindingResult,
						   Model model) throws IOException {
		this.chefValidator.validate(chef, bindingResult);
		String nextPage;
		if (!bindingResult.hasErrors()) { // se i dati sono corretti
			this.chefService.save(chef); // salvo un oggetto Chef
			model.addAttribute("chef", this.chefService.findById(chef.getId()));
			String fileName = chef.getFirstName() + '_' + chef.getLastName() + ".jpeg";

			if (imageFile.isEmpty()) return "redirect:/error";


			File file = fileUploadService.upload(imageFile, fileName);
			if (file == null) return "redirect:/error";

			nextPage = "chef.html"; // presenta un pagina con la chef appena salvata
		} else
			nextPage = "chefForm.html"; // ci sono errori, torna alla form iniziale
		return nextPage;
	}

	@GetMapping("/admin/editChefForm/{id}")
	public String getChefForm (@PathVariable Long id, Model model) { // NON FUNZIONA
		model.addAttribute("chef", chefService.findById(id));
		String nextPage = "editChefForm.html";
		return nextPage;
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
			model.addAttribute("chef", chef);
			nextPage = "chef.html";
		} else {
			nextPage = "editChefForm.html";
		}
		return nextPage;
	}

	@PostMapping("/admin/remove/ask/chef/{id}")
	public String askRemoveChefById (@PathVariable("id") Long id, Model model) {
		model.addAttribute("chef", this.chefService.findById(id));
		String nextPage = "chefConfirmDelete.html";
		return nextPage;
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
