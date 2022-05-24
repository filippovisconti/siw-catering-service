package it.uniroma3.siw.siwcateringservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Dish {

	@Id
	@GeneratedValue
	private Long id;

	@NotBlank
	private String name;
	@NotBlank
	private String description;

	@OneToMany(cascade = CascadeType.PERSIST)
	private Collection<Ingredient> ingredients;
}
