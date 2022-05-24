package it.uniroma3.siw.siwcateringservice.repository;

import it.uniroma3.siw.siwcateringservice.model.Chef;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChefRepository extends CrudRepository<Chef, Long>
{
	Chef findByFirstNameAndLastName(String firstName, String lastName);

	List<Chef> findByFirstName(String firstName);

	List<Chef> findByLastName(String lastName);
	boolean existsByFirstNameAndLastName(String firstName, String lastName);

	List<Chef> findByFirstNameOrLastName (String firstOrLastName);
}
