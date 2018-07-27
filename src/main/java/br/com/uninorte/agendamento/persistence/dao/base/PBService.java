package br.com.uninorte.agendamento.persistence.dao.base;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;

import br.com.uninorte.agendamento.persistence.dao.exception.BOException;
import br.com.uninorte.agendamento.persistence.repositories.base.IBase;
import br.com.uninorte.agendamento.utils.SBPage;
import lombok.AccessLevel;
import lombok.Getter;

// PBService: Persistence Base Service
public abstract class PBService<Entity, PK extends Serializable, Query extends EntityPath<Entity>, Repository extends IBase<Entity, PK, Query>> implements IPBService<Entity, PK> {
	@Getter(AccessLevel.PROTECTED)
	private final Repository repository;

	@Inject
	public PBService(Repository repository) {
		this.repository = repository;
	}

	public <T> Page<T> queryPageable(JPAQuery<T> query, Class<T> clasz, EntityPathBase<T> qEntity, Pageable pageable) {

		javax.persistence.Query	page	= SBPage.setupPage(query, pageable, new PathBuilder<>(clasz, qEntity.getMetadata().getName()));
		return (Page<T>) SBPage.getPageList(pageable, page, null);	
	}

	
	@Override
	public Entity findOne(PK id) {
		return repository.findById(Optional.ofNullable(id).orElseThrow(() -> new BOException("id.missing"))).orElseThrow(() -> new BOException("not.found"));
	}

	@Override
	public Entity findOne(UUID uuid) {
		return repository.findByUuid(Optional.ofNullable(uuid).orElseThrow(() -> new BOException("id.missing"))).orElseThrow(() -> new BOException("not.found"));
	}

	@Override
	public Iterable<Entity> findAll() {
		return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toSet());
	}

	@Override
	public Iterable<Entity> findAll(Predicate predicate) {
		return repository.findAll(predicate);
	}

	@Override
	public Page<Entity> findAll(Predicate predicate, Pageable pageable) {
		return repository.findAll(predicate, pageable);
	}

	@Override
	public Page<Entity> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Entity save(Entity model) {
		return repository.save(model);
	}

	@Override
	public Iterable<Entity> save(Iterable<Entity> models) {
		return repository.saveAll(models);
	}

	@Override
	public void delete(PK id) {
		repository.deleteById(id);
	}

	@Override
	public PK delete(UUID id) {
		return repository.deleteByUuid(id).orElseThrow(() -> new BOException("cannot.delete"));
	}

	@Override
	public void delete(Entity entity) {
		repository.delete(entity);
	}
}