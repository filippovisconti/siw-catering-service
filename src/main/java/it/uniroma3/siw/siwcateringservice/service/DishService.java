package it.uniroma3.siw.siwcateringservice.service;

import it.uniroma3.siw.siwcateringservice.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishService {

	@Autowired
	private DishRepository dishRepository;
}
