package br.com.uninorte.agendamento.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uninorte.agendamento.rest.controllers.base.BaseREST;
import br.com.uninorte.agendamento.rest.dto.EspecialidadeDTO;
import br.com.uninorte.agendamento.rest.dto.MedicoDTO;
import br.com.uninorte.agendamento.rest.services.EspecialidadeBO;
import br.com.uninorte.agendamento.rest.services.MedicoBO;

@RestController
@RequestMapping("/ws/especialidade")
public class EspecialidadeController  extends BaseREST<EspecialidadeBO, EspecialidadeDTO>{

	@Autowired
	public EspecialidadeController(EspecialidadeBO service) {
		super(service);
	}
}
