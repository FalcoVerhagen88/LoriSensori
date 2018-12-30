package com.lorisensori.application.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.web.util.UriComponentsBuilder;

import com.lorisensori.application.domain.Medewerker;


public class OnUserRegistrationCompleteEvent extends ApplicationEvent {

	private UriComponentsBuilder redirectUrl;
	private Medewerker medewerker;

	public OnUserRegistrationCompleteEvent(
			Medewerker medewerker, UriComponentsBuilder redirectUrl) {
		super(medewerker);
		this.medewerker = medewerker;
		this.redirectUrl = redirectUrl;
	}

	public UriComponentsBuilder getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(UriComponentsBuilder redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public Medewerker getMedewerker() {
		return medewerker;
	}

	public void setMedewerker(Medewerker medewerker) {
		this.medewerker = medewerker;
	}
}
