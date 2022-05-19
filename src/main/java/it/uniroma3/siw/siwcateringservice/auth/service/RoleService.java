package it.uniroma3.siw.siwcateringservice.auth.service;

public interface RoleService {
	void addRole(String uid, String role) throws Exception;

	void removeRole(String uid, String role);

}
