package it.uniroma3.siw.siwcateringservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter

@Table(uniqueConstraints = {
		@UniqueConstraint(
				name = "UniqueNameAndNationality",
				columnNames = {"firstName", "lastName", "nationality"})})
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

	@OneToMany(mappedBy = "chef")
	private List<Buffet> offeredBuffets;

	/*@ElementCollection
	@NotEmpty
	private List<Long> offeredBuffets;
	 */

	@Override
	public String toString () {
		return id + " " + firstName + " " + lastName + ", " + nationality;
	}
}
