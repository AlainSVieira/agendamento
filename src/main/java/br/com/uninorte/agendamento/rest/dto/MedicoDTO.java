package br.com.uninorte.agendamento.rest.dto;

import br.com.uninorte.agendamento.persistence.model.Especialidade;
import br.com.uninorte.agendamento.persistence.model.Medico;
import br.com.uninorte.agendamento.rest.dto.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

public class MedicoDTO extends BaseDTO<Medico>{

	@Getter
	@Setter
	private Especialidade especialidade;
	
	@Getter
	@Setter
	private String CRM;
	
	@Getter
	@Setter
	private String Nome;
	
	public MedicoDTO() {
		super();
	}
	
	public MedicoDTO(Medico entity, boolean detailed) {
		super(entity);
	}
}
