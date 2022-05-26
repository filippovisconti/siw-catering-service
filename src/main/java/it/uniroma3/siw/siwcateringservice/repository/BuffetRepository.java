package it.uniroma3.siw.siwcateringservice.repository;

import it.uniroma3.siw.siwcateringservice.model.Buffet;
import it.uniroma3.siw.siwcateringservice.model.Chef;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuffetRepository extends CrudRepository<Buffet,Long>
{
	Buffet findByName(String name);

	boolean existsByName(String name);

	List<Buffet> findByChef (Chef chef);
}
