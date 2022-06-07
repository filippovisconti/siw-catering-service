package it.uniroma3.siw.siwcateringservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	private String name;
	@NotBlank
	private String origin;
	@NotBlank
	private String description;

	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Ingredient that = (Ingredient) o;
		return name.equals(that.name) && origin.equals(that.origin);
	}

	@Override
	public int hashCode () {
		return Objects.hash(name, origin);
	}
}
