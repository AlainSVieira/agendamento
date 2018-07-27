package br.com.uninorte.agendamento.rest.dto;


import br.com.uninorte.agendamento.persistence.model.Paciente;
import br.com.uninorte.agendamento.rest.dto.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

public class PacienteDTO extends BaseDTO<Paciente>{

	@Getter
	@Setter
	private String Nome;
	
	public PacienteDTO() {
		super();
	}
	
	public PacienteDTO(Paciente entity, boolean detailed) {
		super(entity);
	}
}
