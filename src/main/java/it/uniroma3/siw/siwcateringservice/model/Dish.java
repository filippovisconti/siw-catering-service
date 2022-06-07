package it.uniroma3.siw.siwcateringservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Dish {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	private String name;
	@NotBlank
	private String description;

	@ManyToMany
	@NotEmpty
	private List<Ingredient> ingredients;


	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Dish dish = (Dish) o;
		return name.equals(dish.name);
	}

	@Override
	public int hashCode () {
		return Objects.hash(name);
	}
}
