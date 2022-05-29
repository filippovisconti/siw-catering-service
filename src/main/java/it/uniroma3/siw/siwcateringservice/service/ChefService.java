package it.uniroma3.siw.siwcateringservice.service;

import it.uniroma3.siw.siwcateringservice.model.Chef;
import it.uniroma3.siw.siwcateringservice.repository.ChefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChefService {

	@Autowired
	private ChefRepository chefRepository;

	@Transactional
	public Chef inserisci (Chef chef) {
		return chefRepository.save(chef);
	}

	@Transactional
	public void save (Chef chef) {
		chefRepository.save(chef);
	}

	public Chef search (String firstName, String lastName) {
		return chefRepository.findByFirstNameAndLastName(firstName, lastName);
	}

	public List<Chef> searchByFirstOrLastName (String firstName, String lastName) {
		return chefRepository.findByFirstNameOrLastName(firstName, lastName);
	}

	public boolean hasDuplicate (Chef chef) {
		return chefRepository.existsByFirstNameAndLastName(chef.getFirstName(), chef.getFirstName());
	}

	public Chef findById (Long id) {
		var p = chefRepository.findById(id);
		if (p.isPresent())
			return p.get();
		return null;
	}
	@Transactional
	public void deleteChefById (Long id) {
		chefRepository.deleteById(id);
	}

	public List<Chef> findAll () {
		List<Chef> l = new ArrayList<>();
		for (Chef i : chefRepository.findAll()) l.add(i);
		return l;
	}
}
