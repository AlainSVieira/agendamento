package br.com.uninorte.agendamento.rest.dto;


import br.com.uninorte.agendamento.persistence.model.Especialidade;
import br.com.uninorte.agendamento.persistence.model.Exame;
import br.com.uninorte.agendamento.persistence.model.Medico;
import br.com.uninorte.agendamento.rest.dto.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

public class ExameDTO extends BaseDTO<Exame>{

	@Getter
	@Setter
	private String Nome;
	
	public ExameDTO() {
		super();
	}
	
	public ExameDTO(Exame entity, boolean detailed) {
		super(entity);
	}
}
