package br.com.uninorte.agendamento.security.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class AuthenticationManagerIMPL implements AuthenticationManager {
//	private	final	AccountService 		service;
//	private final 	PasswordEncoder 	encoder;

	@Autowired
	public AuthenticationManagerIMPL() {
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//		String			username		= (String) authentication.getPrincipal();
////		String			password		= Hashing.sha256().hashString((String) authentication.getCredentials(), StandardCharsets.UTF_8).toString();
//		String 			password = (String) authentication.getCredentials();
//		User 		account			= (User) service.loadUserByUsername(username);
//
//		if (account == null)
//			throw new UsernameNotFoundException("user.notfound");
//
//		if (!account.isEnabled())
//			throw new DisabledException("user.disabled");
//
//		if (!encoder.matches(password, account.getPassword()))
////		if (password.equals(account.getPassword()))
//			throw new BadCredentialsException("user.password.mismatch");
//
//		return new UsernamePasswordAuthenticationToken(account, password, account.getAuthorities());
		return null;
	}

	public Authentication reauthenticate(Authentication authentication) throws AuthenticationException {
//		String			username		= (String) authentication.getPrincipal();
//		User			account			= (User) service.loadUserByUsername(username);
//		if (account == null)
//			throw new UsernameNotFoundException("user.notfound");
//
//		if (!account.isEnabled())
//			throw new DisabledException("user.disabled");
//
//		return new UsernamePasswordAuthenticationToken(account, authentication.getCredentials(), account.getAuthorities());
		return null;
	}
}
