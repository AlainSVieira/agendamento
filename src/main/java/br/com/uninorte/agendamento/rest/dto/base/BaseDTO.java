package br.com.uninorte.agendamento.rest.dto.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.lang.reflect.ParameterizedType;
import java.util.UUID;

@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseDTO<T> implements IBaseDTO<T> {
	@Getter(value = AccessLevel.PROTECTED, onMethod = @__({@JsonIgnore}))
	@Setter(value = AccessLevel.PROTECTED, onMethod = @__({@JsonIgnore}))
	private 		ModelMapper		mapper;

	@Getter
	@Setter
	@JsonProperty("id")
	private			UUID			uuid;

	public BaseDTO() {
		setMapper(new ModelMapper());
		getMapper().getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	// TODO: Skip properties
	public BaseDTO(T entity) {
		setMapper(new ModelMapper());
		getMapper().getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		getMapper().getConfiguration().setSkipNullEnabled(true);
		getMapper().map(entity, this);
	}

	@SuppressWarnings("unchecked")
	@JsonIgnore
	@Override
	public T getModel() {
		try {
			T model = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
			getMapper().map(this, model);
			return model;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@JsonIgnore
	@Override
	public T copyTo(T newInstance) {
		getMapper().map(this, newInstance);
		return newInstance;
	}
}
