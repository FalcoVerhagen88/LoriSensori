package com.lorisensori.application.domain;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



public class CustomUserDetails extends Medewerker implements UserDetails {

	public CustomUserDetails(final Medewerker medewerker) {
		super(medewerker);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getRechten().stream()
				.map(recht -> new SimpleGrantedAuthority(recht.getRecht().name()))
				.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		return super.getGebruikersnaam();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return super.getActive();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return super.getEmailVerified();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		CustomUserDetails that = (CustomUserDetails) obj;
		return Objects.equals(getId(), that.getId());
	}
}
