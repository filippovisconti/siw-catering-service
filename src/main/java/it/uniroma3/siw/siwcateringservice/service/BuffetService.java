package it.uniroma3.siw.siwcateringservice.service;

import it.uniroma3.siw.siwcateringservice.model.Buffet;
import it.uniroma3.siw.siwcateringservice.model.Chef;
import it.uniroma3.siw.siwcateringservice.repository.BuffetRepository;
import it.uniroma3.siw.siwcateringservice.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuffetService {

	@Autowired
	private BuffetRepository buffetRepository;


	@Transactional
	public Buffet inserisci (Buffet buffet) {
		return buffetRepository.save(buffet);
	}

	@Transactional
	public void save (Buffet buffet) {
		buffetRepository.save(buffet);
	}

	public Buffet search (String name) {
		return buffetRepository.findByName(name);
	}

	public List<Buffet> search (Chef chef) {
		return buffetRepository.findByChef(chef);
	}

	public boolean hasDuplicate (Buffet buffet) {
		return buffetRepository.existsByName(buffet.getName());
	}

	public Buffet findById (Long id) {
		var p = buffetRepository.findById(id);
		if (p.isPresent())
			return p.get();
		return null;
	}

	public void deleteBuffetById (Long id) {
		buffetRepository.deleteById(id);
	}

	public List<Buffet> findAll () {
		List<Buffet> l = new ArrayList<>();
		for (Buffet i : buffetRepository.findAll()) l.add(i);
		return l;
	}
}
