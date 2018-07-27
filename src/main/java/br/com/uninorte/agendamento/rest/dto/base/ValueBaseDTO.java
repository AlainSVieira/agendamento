package br.com.uninorte.agendamento.rest.dto.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public abstract class ValueBaseDTO<T> extends BaseDTO<T> {
	@Getter
	@Setter
	@JsonProperty("sequence")
	private 		long				sequence;

	@Getter
	@Setter
	@JsonProperty("is_max_ratio")
	private 		Boolean				maxRatio;

	@Getter
	@Setter
	@JsonProperty("max_value")
	private 		Double				maxValue;

	@Getter
	@Setter
	@JsonProperty("is_fixed_ratio")
	private 		Boolean				fixedRatio;

	@Getter
	@Setter
	@JsonProperty("is_fixed_value")
	private 		Double				fixedValue;

	public ValueBaseDTO() {
		super();
	}

	public ValueBaseDTO(T entity) {
		super(entity);
	}
}
