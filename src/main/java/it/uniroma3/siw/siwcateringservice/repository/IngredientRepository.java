package it.uniroma3.siw.siwcateringservice.repository;

import it.uniroma3.siw.siwcateringservice.model.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient,Long> {
	List<Ingredient> findByNameOrOrigin (String name, String origin);

	List<Ingredient> findByName (String name);

	List<Ingredient> findByOrigin (String origin);
	boolean existsByNameAndOrigin (String name, String origin);
}
