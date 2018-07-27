package br.com.uninorte.agendamento.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uninorte.agendamento.rest.controllers.base.BaseREST;
import br.com.uninorte.agendamento.rest.dto.ConsultaDTO;
import br.com.uninorte.agendamento.rest.dto.MedicoDTO;
import br.com.uninorte.agendamento.rest.services.ConsultaBO;
import br.com.uninorte.agendamento.rest.services.MedicoBO;

@RestController
@RequestMapping("/ws/consulta")
public class ConsultaController  extends BaseREST<ConsultaBO, ConsultaDTO>{

	@Autowired
	public ConsultaController(ConsultaBO service) {
		super(service);
	}
}
