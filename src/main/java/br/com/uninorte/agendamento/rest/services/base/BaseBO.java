package br.com.uninorte.agendamento.rest.services.base;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import com.querydsl.core.types.Predicate;

import br.com.uninorte.agendamento.persistence.dao.base.IPBService;
import br.com.uninorte.agendamento.persistence.dao.exception.BOException;
import br.com.uninorte.agendamento.persistence.model.base.Base;
import br.com.uninorte.agendamento.rest.dto.base.IBaseDTO;
import lombok.AccessLevel;
import lombok.Getter;

public abstract class BaseBO<TEntity extends Base, TDAO extends IPBService<TEntity, TPK>, TDTO extends IBaseDTO<TEntity>, TPK extends Serializable> implements IBaseBO<TEntity, TDTO, TPK> {
	@Getter(AccessLevel.PROTECTED)
	private 	final 	TDAO				dao;
	private 	final 	Class<TEntity>		entity;
	private 	final 	Class<TDTO>			dto;

	@SuppressWarnings("unchecked")
	@Inject
	protected BaseBO(TDAO dao) {
		entity			= (Class<TEntity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		dto				= (Class<TDTO>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2];
		this.dao		= dao;
	}

	@Override
	public TDTO findOne(TPK id, UserDetails details) {
		return toDTO(getDao().findOne(id), true);
	}

	@Override
	public TDTO findOne(UUID uuid, UserDetails details) {
		return toDTO(getDao().findOne(uuid), true);
	}

	@Override
	public Collection<TDTO> findAll(UserDetails details) {
		return StreamSupport.stream(getDao().findAll().spliterator(), true).map(item -> toDTO(item, false)).collect(Collectors.toList());
	}


	public Collection<TDTO> findAll(Predicate predicate, UserDetails details) {
		return StreamSupport.stream(getDao().findAll(predicate).spliterator(), true).map(item -> toDTO(item, false)).collect(Collectors.toList());
	}

	@Override
	public Page<TDTO> findAll(Predicate predicate, Pageable pageable, UserDetails details) {
		return getDao().findAll(predicate, pageable).map(item -> toDTO(item, false));
	}

	@Override
	public Page<TDTO> findAll(Pageable pageable, UserDetails details) {
		return getDao().findAll(pageable).map(item -> toDTO(item, false));
	}

	@Override
	public TPK delete(UUID id) {
		return getDao().delete(id);
	}

	@Override
	public void delete(TEntity entity) {
		getDao().delete(entity);
	}

	@Override
	public void delete(TPK id) {
		getDao().delete(id);
	}

	public TDTO setEnabled(UUID id, boolean enabled) {
		TEntity	model	= Optional.ofNullable(getDao().findOne(id)).orElseThrow(() -> new BOException("not.found"));
		model.setEnabled(enabled);
		return toDTO(getDao().save(model), false);
	}

	//================================================================================================================//
	private TDTO toDTO(TEntity item, boolean detailed) {
		try {
			return dto.getConstructor(entity, boolean.class).newInstance(item, detailed);
		} catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(dto.getName() + ": No constructor matches (TEntity, Boolean)");
		}
	}
}


