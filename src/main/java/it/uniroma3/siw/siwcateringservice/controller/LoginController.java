package it.uniroma3.siw.siwcateringservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

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
