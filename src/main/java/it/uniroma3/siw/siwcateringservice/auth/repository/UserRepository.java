package it.uniroma3.siw.siwcateringservice.auth.repository;

import it.uniroma3.siw.siwcateringservice.auth.roles.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
