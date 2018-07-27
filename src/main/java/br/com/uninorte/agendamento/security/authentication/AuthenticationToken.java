package br.com.uninorte.agendamento.security.authentication;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticationToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	private 		String		token;
	private	final	UserDetails details;

	AuthenticationToken(UserDetails details) {
		super(details.getAuthorities());
		this.details = details;
	}

	@Override
	public boolean isAuthenticated() {
		return true;
	}

	@Override
	public Object getCredentials() {
		return token;
	}

	@Override
	public UserDetails getPrincipal() {
		return details;
	}
}
