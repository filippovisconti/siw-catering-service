package it.uniroma3.siw.siwcateringservice.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	@GetMapping("/admin")
	//@IsStdUser
	public String getHomePage () {
		return "admin_index.html";
	}

}