package com.lorisensori.application.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.web.util.UriComponentsBuilder;

import com.lorisensori.application.domain.Medewerker;
import com.lorisensori.application.domain.token.EmailVerificationToken;



public class OnRegenerateEmailVerificationEvent extends ApplicationEvent {

	private UriComponentsBuilder redirectUrl;
	private Medewerker user;
	private EmailVerificationToken token;

	public OnRegenerateEmailVerificationEvent(
			Medewerker user, UriComponentsBuilder redirectUrl, EmailVerificationToken token) {
		super(user);
		this.user = user;
		this.redirectUrl = redirectUrl;
		this.token = token;
	}

	public UriComponentsBuilder getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(UriComponentsBuilder redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public Medewerker getMedewerker() {
		return user;
	}

	public void setMedewerker(Medewerker user) {
		this.user = user;
	}

	public EmailVerificationToken getToken() {
		return token;
	}

	public void setToken(EmailVerificationToken token) {
		this.token = token;
	}
}