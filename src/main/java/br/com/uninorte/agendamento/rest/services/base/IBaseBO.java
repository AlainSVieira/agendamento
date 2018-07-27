
package br.com.uninorte.agendamento.rest.services.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import com.querydsl.core.types.Predicate;


public interface IBaseBO<TEntity, TDTO, TPK extends Serializable> {
	TDTO findOne(TPK id, UserDetails details);
	TDTO findOne(UUID uuid, UserDetails details);

	Collection<TDTO> findAll(UserDetails details);
	Collection<TDTO> findAll(Predicate predicate, UserDetails details);

	Page<TDTO> findAll(Predicate predicate, Pageable pageable, UserDetails details);
	Page<TDTO> findAll(Pageable pageable, UserDetails details);

	TPK delete(UUID id);
	void delete(TEntity entity);
	void delete(TPK id);

	TDTO save(TDTO create, UserDetails account);
	TDTO update(TDTO update, UserDetails account);
	TDTO setEnabled(UUID id, boolean enabled);
}
