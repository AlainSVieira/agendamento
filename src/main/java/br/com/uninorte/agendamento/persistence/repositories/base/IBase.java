package br.com.uninorte.agendamento.persistence.repositories.base;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.transaction.Transactional;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

@NoRepositoryBean
public interface IBase<TEntity, TPK extends Serializable, TQuery extends EntityPath<TEntity>> extends PagingAndSortingRepository<TEntity, TPK>, QuerydslPredicateExecutor<TEntity>, QuerydslBinderCustomizer<TQuery> {
	Optional<TEntity> findByUuid(UUID uuid);

	@Transactional
	Optional<TPK> deleteByUuid(UUID uuid);
	
	@Override
	default void customize(@Nonnull QuerydslBindings bindings, @Nonnull TQuery root) {
		bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
	}
}
