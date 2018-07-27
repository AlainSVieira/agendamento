package br.com.uninorte.agendamento.security;

import java.util.Set;

import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import br.com.uninorte.agendamento.security.authentication.AuthenticationEntry;
import br.com.uninorte.agendamento.security.authentication.AuthenticationFilter;
import br.com.uninorte.agendamento.utils.annotations.Authorizations;
import br.com.uninorte.agendamento.utils.annotations.Authorize;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	private	final		AuthenticationFilter		filter;
	private	final 		AuthenticationEntry 		entry;

	@Autowired
	public SecurityConfiguration(AuthenticationFilter filter, AuthenticationEntry entry) {
		this.filter		= filter;
		this.entry		= entry;
	}

	@Override
	@CrossOrigin
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().cacheControl().disable();
		
		authorize(http)
		.authorizeRequests()
		.antMatchers("/**").permitAll()
		.and()
//		http
//		.antMatcher("/ws/**").permittAll()
//		.and
		.addFilterBefore(filter, BasicAuthenticationFilter.class)
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.exceptionHandling().authenticationEntryPoint(entry)
		.and()
		.httpBasic()
		.authenticationEntryPoint(entry)
		.and().cors()
		.and().csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);		
		web.ignoring()
		.antMatchers(HttpMethod.OPTIONS)
		.antMatchers(HttpMethod.POST, "/auth/**")
		.antMatchers(HttpMethod.GET, "/ws/file/**")
		.antMatchers("/translate/**")
		.antMatchers("/assets/**", "/webjars/**", "/api-docs/**")
		.antMatchers("/jsondoc/**", "/jsondoc-ui.html", "/swagger-ui.html", "/swagger-resources/**", "/v2/api-docs");
	}

	@Bean
	public FilterRegistrationBean registration(AuthenticationFilter filter) {
		FilterRegistrationBean registration = new FilterRegistrationBean(filter);
		registration.setEnabled(false);
		return registration;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
 
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource		source			= new UrlBasedCorsConfigurationSource();
		CorsConfiguration					configuration 	= new CorsConfiguration();
		configuration.addAllowedOrigin("*");
		configuration.addAllowedHeader("*");
//		configuration.addAllowedMethod("*");
		configuration.addAllowedMethod(HttpMethod.POST);
		configuration.addAllowedMethod(HttpMethod.GET);
		configuration.addAllowedMethod(HttpMethod.PUT);
		configuration.addAllowedMethod(HttpMethod.DELETE);
		
		configuration.addExposedHeader(HttpHeaders.AUTHORIZATION);
		configuration.addExposedHeader("csrf");
//		configuration.addExposedHeader("**");
		
		source.registerCorsConfiguration("/**", configuration);
		return new CorsFilter(source);
	}

	private HttpSecurity authorize(HttpSecurity http) throws Exception {
		Set<Class<?>> classes = new Reflections("br.com.itn.speed.rest.controllers").getTypesAnnotatedWith(Authorizations.class);
		for (Class<?> clazz : classes) {
			RequestMapping mapping = clazz.getAnnotation(RequestMapping.class);
			for (Authorize authorize : clazz.getAnnotation(Authorizations.class).value()) {
				http.authorizeRequests().anyRequest().permitAll().antMatchers(authorize.method(), mapping.value()).hasAnyRole(authorize.roles());
			}
		}
		return http;
	}
}
