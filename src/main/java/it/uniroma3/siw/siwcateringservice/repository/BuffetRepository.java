package it.uniroma3.siw.siwcateringservice.repository;

import it.uniroma3.siw.siwcateringservice.model.Buffet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuffetRepository extends CrudRepository<Buffet,Long>
{
}
