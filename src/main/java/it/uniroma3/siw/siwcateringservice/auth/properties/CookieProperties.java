package it.uniroma3.siw.siwcateringservice.auth.properties;

import lombok.Data;

@Data
public class CookieProperties {
	private String domain;
	private String path;
	private boolean httpOnly;
	private boolean secure;
	private int maxAgeInMinutes;
}