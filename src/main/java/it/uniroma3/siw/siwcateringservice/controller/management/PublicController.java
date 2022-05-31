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
		String nextPage = "index.html";
		return nextPage;
	}

	@GetMapping("/public")
	//@IsStdUser
	public String getPublicHomePage () {
		String nextPage = "public_index.html";
		return nextPage;
	}

}
