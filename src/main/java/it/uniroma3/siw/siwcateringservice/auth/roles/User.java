package it.uniroma3.siw.siwcateringservice.auth.roles;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_table")
@Getter
@Setter
@NoArgsConstructor
public class User{// extends DefaultOAuth2User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String externalUid;
/*
	@Column(nullable = false)
	private String email;

 */

	/*@ElementCollection(targetClass = MyPermission.class)
	@CollectionTable
	@Enumerated(EnumType.STRING)
	private Collection<MyPermission> permissions;
	 */
/*
	private String imageUrl;

	@Column(nullable = false)
	private UserType type;
	*/

}
