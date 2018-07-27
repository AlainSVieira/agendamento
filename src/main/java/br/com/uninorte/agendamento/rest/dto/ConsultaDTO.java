package br.com.uninorte.agendamento.rest.dto;


import java.util.Date;

import br.com.uninorte.agendamento.persistence.model.Consulta;
import br.com.uninorte.agendamento.persistence.model.Exame;
import br.com.uninorte.agendamento.persistence.model.Medico;
import br.com.uninorte.agendamento.persistence.model.Paciente;
import br.com.uninorte.agendamento.rest.dto.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

public class ConsultaDTO extends BaseDTO<Consulta>{

	@Getter
	@Setter
	private Date date;
	
	@Getter
	@Setter
	private Paciente paciente;
	
	@Getter
	@Setter
	private Exame exame;
	
	@Getter
	@Setter
	private Medico medico;
	
	public ConsultaDTO() {
		super();
	}
	
	public ConsultaDTO(Consulta entity, boolean detailed) {
		super(entity);
	}
}
