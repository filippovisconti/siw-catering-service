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
public class Chef {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@NotBlank
	private String nationality;

	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "chef")
	private Collection<Buffet> offeredBuffets;
}
