package it.uniroma3.siw.siwcateringservice.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@GetMapping("/perform_login")
	public String performLogin() {
		return "index.html";
	}

	@GetMapping("/perform_logout")
	public String performLogout() {
		return "index.html";
	}
}
