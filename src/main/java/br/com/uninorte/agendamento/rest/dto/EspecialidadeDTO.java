package br.com.uninorte.agendamento.rest.dto;


import br.com.uninorte.agendamento.persistence.model.Especialidade;
import br.com.uninorte.agendamento.persistence.model.Medico;
import br.com.uninorte.agendamento.rest.dto.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

public class EspecialidadeDTO extends BaseDTO<Especialidade>{

	@Getter
	@Setter
	private String nome;
	
	public EspecialidadeDTO() {
		super();
	}
	
	public EspecialidadeDTO(Especialidade entity, boolean detailed) {
		super(entity);
	}
}
