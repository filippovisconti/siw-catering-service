package it.uniroma3.siw.siwcateringservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class DishCreator {

	public DishCreator (Dish d){
		this.id = d.getId();
		this.name = d.getName();
		this.description = d.getDescription();
		this.ingredients = d.getIngredients();
	}

	private Long id;
	@NotBlank
	private String name;
	@NotBlank
	private String description;

	@ManyToMany(mappedBy = "dishes")
	@NotEmpty
	private List<Long> ingredients;


}
