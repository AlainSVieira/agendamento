package br.com.uninorte.agendamento.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uninorte.agendamento.rest.controllers.base.BaseREST;
import br.com.uninorte.agendamento.rest.dto.MedicoDTO;
import br.com.uninorte.agendamento.rest.dto.PacienteDTO;
import br.com.uninorte.agendamento.rest.services.MedicoBO;
import br.com.uninorte.agendamento.rest.services.PacienteBO;

@RestController
@RequestMapping("/ws/paciente")
public class PacienteController  extends BaseREST<PacienteBO, PacienteDTO>{

	@Autowired
	public PacienteController(PacienteBO service) {
		super(service);
	}
}
