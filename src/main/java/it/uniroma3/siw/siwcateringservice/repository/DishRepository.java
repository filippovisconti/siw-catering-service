package it.uniroma3.siw.siwcateringservice.repository;

import it.uniroma3.siw.siwcateringservice.model.Dish;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends CrudRepository<Dish,Long>
{
}
