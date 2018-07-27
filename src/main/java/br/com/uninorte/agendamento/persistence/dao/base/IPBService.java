package br.com.uninorte.agendamento.persistence.dao.base;

import java.io.Serializable;
import java.util.UUID;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface IPBService<TEntity, TPK extends Serializable>{
	TEntity findOne(TPK id);
	TEntity findOne(UUID uuid);

	Iterable<TEntity> findAll();
	Iterable<TEntity> findAll(Predicate predicate);

	Page<TEntity> findAll(Predicate predicate, Pageable pageable);
	Page<TEntity> findAll(Pageable pageable);

	TEntity save(TEntity model);
	Iterable<TEntity> save(Iterable<TEntity> models);

	TPK delete(UUID id);
	void delete(TPK id);
	void delete(TEntity entity);
}