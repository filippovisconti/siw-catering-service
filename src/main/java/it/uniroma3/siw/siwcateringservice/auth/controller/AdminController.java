package it.uniroma3.siw.siwcateringservice.controller.management;

import it.uniroma3.siw.siwcateringservice.auth.roles.IsAdmin;
import it.uniroma3.siw.siwcateringservice.model.Buffet;
import it.uniroma3.siw.siwcateringservice.model.Chef;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("private")
public class AdminController {

	@GetMapping("/add/chef")
	//@IsAdmin
	public String addChef(Model model) {
		model.addAttribute("chef", new Chef());
		return "addChef";
	}

}