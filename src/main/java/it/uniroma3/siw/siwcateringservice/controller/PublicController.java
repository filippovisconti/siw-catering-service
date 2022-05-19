package it.uniroma3.siw.siwcateringservice.controller;

import it.uniroma3.siw.siwcateringservice.auth.roles.IsStdUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/public")
public class PublicController {

	@GetMapping("/test-login")
	//@IsStdUser
	public String getCurrentUser(Principal principal) {
		return principal.getName();
	}

	@GetMapping("/")
	public String getHomePage() {
		return "index.html";
	}
}
