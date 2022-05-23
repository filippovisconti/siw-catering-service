package it.uniroma3.siw.siwcateringservice.repository;

import it.uniroma3.siw.siwcateringservice.model.Chef;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChefRepository extends CrudRepository<Chef, Long>
{
}
