package br.com.uninorte.agendamento.security.authentication.jwt;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.uninorte.agendamento.persistence.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTokenHelper {
	private	static	final		String		BEARING		= "LEAN=";
	private static 	final		String		secret		= "e$40$jJms$L34NpS$gEO66Z$HeYdAM";
	private static	final		String		appName		= "LeanPS";
	private static	final		String		header		= HttpHeaders.AUTHORIZATION;

	public String generateToken(User account) {
		JwtBuilder 		builder		= Jwts.builder();
		JWTokenData		data		= new JWTokenData();
		ZonedDateTime	issuedAt	= ZonedDateTime.now();
		ZonedDateTime	expiresIn	= issuedAt.plusDays(2);

		data.setRoles(String.join("|", account.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())));
		data.setSessionID(UUID.randomUUID().toString());
		data.setUserID(account.getUuid());
		data.setName(account.getName());
		data.setUsername(account.getUsername());
		
		builder.setClaims(new ObjectMapper().convertValue(data, Map.class));
		builder.setIssuer(appName);
		builder.setIssuedAt(Date.from(issuedAt.toInstant()));
		builder.setExpiration(Date.from(expiresIn.toInstant()));
		builder.signWith(SignatureAlgorithm.HS512, secret);
		return builder.compact();
	}

	public String getToken(HttpServletRequest request) {
		if (request.getHeader(header) != null && request.getHeader(header).startsWith(BEARING))
			return request.getHeader(header).substring(BEARING.length());
		return null;
	}

	public String getSessionID(String token) {
		return Optional.of(Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("sid", String.class)).orElse(null);
	}
	
	public String getUserLogin(String token) {
		return Optional.of(Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("username", String.class)).orElse(null);
	}

	// TODO: May userDetails can save some useful data for validation
	public Boolean isExpired(String token, UserDetails userDetails) {
		final	Claims			claims		= Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		final	User 		account 	= (User) userDetails;
		final	ZonedDateTime 	created 	= ZonedDateTime.ofInstant(claims.getIssuedAt().toInstant(), ZoneId.systemDefault());
		final 	ZonedDateTime	expires		= ZonedDateTime.ofInstant(claims.getExpiration().toInstant(), ZoneId.systemDefault());
		final	ZonedDateTime	now			= ZonedDateTime.now();
		return expires.isBefore(now);
	}
}
