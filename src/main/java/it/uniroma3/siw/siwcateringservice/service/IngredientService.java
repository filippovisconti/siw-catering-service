package it.uniroma3.siw.siwcateringservice.service;

import it.uniroma3.siw.siwcateringservice.model.Dish;
import it.uniroma3.siw.siwcateringservice.model.Ingredient;
import it.uniroma3.siw.siwcateringservice.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientService {

	@Autowired
	private IngredientRepository ingredientRepository;

	@Transactional
	public Ingredient inserisci (Ingredient ingredient) {
		return ingredientRepository.save(ingredient);
	}

	@Transactional
	public void save (Ingredient ingredient) {
		ingredientRepository.save(ingredient);
	}

	public List<Ingredient> search (String name, String origin) {
		return ingredientRepository.findByNameOrOrigin(name, origin);
	}

	public boolean hasDuplicate (Ingredient ingredient) {
		return ingredientRepository.existsByNameAndOrigin(ingredient.getName(), ingredient.getOrigin());
	}

	public List<Ingredient> findByIds (List<Long> ids) {
		var i = ingredientRepository.findAllById(ids);
		List<Ingredient> ingredientsList = new ArrayList<>();
		for(Ingredient ing : i)
				ingredientsList.add(ing);
		return ingredientsList;
	}
	public Ingredient findById (Long id) {
		var p = ingredientRepository.findById(id);
		if (p.isPresent())
			return p.get();
		return null;
	}

	@Transactional
	public void deleteIngredientById (Long id) {
		ingredientRepository.deleteById(id);
	}

	public List<Ingredient> findAll () {
		List<Ingredient> l = new ArrayList<>();
		for (Ingredient i : ingredientRepository.findAll()) l.add(i);
		return l;
	}
}
