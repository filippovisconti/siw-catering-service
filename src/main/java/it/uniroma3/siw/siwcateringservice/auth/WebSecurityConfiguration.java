package it.uniroma3.siw.siwcateringservice.auth;

import com.google.firebase.auth.FirebaseAuth;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
//https://www.baeldung.com/spring-security-login
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/public*").permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.formLogin()
				.loginPage("/login.html")
				.loginProcessingUrl("/perform_login")
				.defaultSuccessUrl("/homepage.html", true)
				.failureUrl("/login.html?error=true")
				.and()
				.logout()
				.logoutUrl("/perform_logout")
				.invalidateHttpSession(true) //
				.deleteCookies("JSESSIONID")
				.clearAuthentication(true).permitAll();//

		http.oauth2ResourceServer()
				.jwt()
				.jwtAuthenticationConverter(jwtAuthenticationConverter());
	}

	public JwtAuthenticationConverter jwtAuthenticationConverter() {
		JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

		converter.setJwtGrantedAuthoritiesConverter(jwt ->
				Optional.ofNullable(jwt.getClaimAsStringList("custom_claims"))
						.stream()
						.flatMap(Collection::stream)
						.map(SimpleGrantedAuthority::new)
						.collect(Collectors.toList())
		);

		return converter;
	}
}