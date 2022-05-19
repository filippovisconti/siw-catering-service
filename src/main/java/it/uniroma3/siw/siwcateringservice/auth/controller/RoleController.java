package it.uniroma3.siw.siwcateringservice.auth.controller;
import it.uniroma3.siw.siwcateringservice.auth.roles.IsAdmin;
import it.uniroma3.siw.siwcateringservice.auth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.auth.FirebaseAuth;

@RestController
@RequestMapping("private/role")
public class RoleController {

	@Autowired
	RoleService roleService;

	@PutMapping("add")
	@IsAdmin
	public void addRole(@RequestParam String uid, @RequestParam String role) throws Exception {
		roleService.addRole(uid, role);
	}

	@DeleteMapping("remove")
	@IsAdmin
	public void removeRole(@RequestParam String uid, @RequestParam String role) {
		roleService.removeRole(uid, role);

	}


}