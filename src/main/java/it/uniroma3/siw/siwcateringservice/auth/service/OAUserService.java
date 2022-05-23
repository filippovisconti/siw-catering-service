package it.uniroma3.siw.siwcateringservice.auth.service;

import it.uniroma3.siw.siwcateringservice.auth.roles.User;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class OAUserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
	@Override
	public OAuth2User loadUser (OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		return null;
	}

}
