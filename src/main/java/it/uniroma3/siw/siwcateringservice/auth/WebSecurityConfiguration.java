package it.uniroma3.siw.siwcateringservice.auth;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//https://www.baeldung.com/spring-security-login

@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure (HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.anyRequest().permitAll();

		/*http.authorizeRequests()
				.antMatchers("/public/**").permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.oauth2Login();
		 */
		//.defaultSuccessUrl("/loginSuccess")
		//.failureUrl("/loginFailure");
	}
/*
	@Bean
	public GrantedAuthoritiesMapper userAuthoritiesMapper () {
		return (authorities) -> {
			Set<GrantedAuthority> mappedAuthorities = new HashSet<>();

			authorities.forEach(authority -> {
				if (OAuth2UserAuthority.class.isInstance(authority)) {
					OAuth2UserAuthority oauth2UserAuthority = (OAuth2UserAuthority) authority;

					Map<String, Object> userAttributes = oauth2UserAuthority.getAttributes();
					mappedAuthorities.add(oauth2UserAuthority);
					// Map the attributes found in userAttributes
					// to one or more GrantedAuthority's and add it to mappedAuthorities

				}
			});

			return mappedAuthorities;
		};
	}*/
}