package it.uniroma3.siw.siwcateringservice.controller.management;

import it.uniroma3.siw.siwcateringservice.model.Buffet;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class PublicController {

	@GetMapping("/")
	//@IsStdUser
	public String getHomePage () {
		String nextPage = "public_index.html";
		return nextPage;
	}

	/*
	TODO remove what's below
	 */
	@GetMapping("/test-login")
	//@IsStdUser
	public String getCurrentUser (Principal principal) {
		return principal.getName();
	}

	@GetMapping("/public/add/buffet")
	//@IsStdUser
	public String addBuffet (Model model, Principal principal) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = null;
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			currentUserName = authentication.getName();

		}
		model.addAttribute("buffet", new Buffet());
		if (principal != null) model.addAttribute("uName", getCurrentUser(principal));
		model.addAttribute("uName2", currentUserName);
		return "addBuffet";
	}
}
