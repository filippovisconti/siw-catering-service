package it.uniroma3.siw.siwcateringservice.controller.management;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicController {

	@GetMapping("/")
	//@IsStdUser
	public String getHomePage () {
		return "index.html";
	}

	@GetMapping("/public")
	//@IsStdUser
	public String getPublicHomePage () {
		return "public_index.html";
	}

}
