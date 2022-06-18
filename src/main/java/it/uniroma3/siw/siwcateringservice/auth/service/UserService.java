package it.uniroma3.siw.siwcateringservice.auth.service;

import it.uniroma3.siw.siwcateringservice.auth.models.User;
import it.uniroma3.siw.siwcateringservice.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@SuppressWarnings("ALL")
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User getUser (Long id) {
		var p = userRepository.findById(id);
		return p.orElse(null);
	}

	public User saveUser (User user) {
		return userRepository.save(user);
	}

}
