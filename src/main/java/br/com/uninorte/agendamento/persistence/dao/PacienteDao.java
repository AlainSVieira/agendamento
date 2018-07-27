package br.com.uninorte.agendamento.persistence.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uninorte.agendamento.persistence.dao.base.PBService;
import br.com.uninorte.agendamento.persistence.model.Medico;
import br.com.uninorte.agendamento.persistence.model.Paciente;
import br.com.uninorte.agendamento.persistence.model.QMedico;
import br.com.uninorte.agendamento.persistence.model.QPaciente;
import br.com.uninorte.agendamento.persistence.repositories.IMedicoRepository;
import br.com.uninorte.agendamento.persistence.repositories.IPacienteRepository;


@Service
public class PacienteDao extends PBService<Paciente, Long, QPaciente, IPacienteRepository>{

	private EntityManager entityManager;
	
	@Autowired
	public PacienteDao(IPacienteRepository repository,EntityManager entityManager) {
		super(repository);
	}


}
