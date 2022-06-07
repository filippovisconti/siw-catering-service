package it.uniroma3.siw.siwcateringservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Buffet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	private String name;
	@NotBlank
	private String description;

	@ManyToOne
	private Chef chef;

	@ManyToMany
	private List<Dish> offeredDishes;


	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Buffet buffet = (Buffet) o;
		return name.equals(buffet.name) && chef.equals(buffet.chef);
	}

	@Override
	public int hashCode () {
		return Objects.hash(name, chef);
	}
}
