package br.com.uninorte.agendamento.utils.annotations;

import org.springframework.http.HttpMethod;

import java.lang.annotation.*;

@Target({ ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Authorize {
	HttpMethod 		method();
	String[]		roles();
}