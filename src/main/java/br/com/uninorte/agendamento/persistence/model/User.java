package br.com.uninorte.agendamento.persistence.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.uninorte.agendamento.persistence.model.base.Base;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
public class User extends Base implements UserDetails{		
	
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Column(nullable = false)
	private String name;	
	
	@Getter
	@Setter
	@Column(nullable = false)
	private String username;	
	
	@Getter
	@Setter
	@Column(nullable = false)
	private String password;
	
	@Getter
	@Setter
	private int status;
	
	@Getter
	@Setter
	@Column(nullable = false)
	private String email;


	@Column(columnDefinition = "text")
	private 	String			authorities;

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = StringUtils.join(authorities, "|");
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities != null && !authorities.isEmpty()? Arrays.stream(authorities.split("\\|")).map(SimpleGrantedAuthority::new).collect(Collectors.toList()) : new ArrayList<>();
	}

	@Getter
	@Setter
	@Column(nullable = false)
	private 	boolean			accountNonExpired;

	@Getter
	@Setter
	@Column(nullable = false)
	private 	boolean			accountNonLocked;
	
	@Getter
	@Setter
	@Column(nullable = false)
	private 	boolean			credentialsNonExpired;

	

		

	
	
	
}