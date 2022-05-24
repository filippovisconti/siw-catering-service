package it.uniroma3.siw.siwcateringservice.auth.roles;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import javax.persistence.*;
import java.util.Collection;
import java.util.Map;

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
