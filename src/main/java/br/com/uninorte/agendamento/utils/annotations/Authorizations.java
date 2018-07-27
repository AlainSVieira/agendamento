package br.com.uninorte.agendamento.utils.annotations;

import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Authorizations {
	Authorize[]		value();
}