package it.uniroma3.siw.siwcateringservice.auth.models;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
//@PreAuthorize("hasAuthority('READ')")
@PreAuthorize("isAnonymous()")
public @interface IsStdUser {
}
