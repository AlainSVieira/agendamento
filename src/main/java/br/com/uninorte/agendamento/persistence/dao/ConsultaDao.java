package br.com.uninorte.agendamento.persistence.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uninorte.agendamento.persistence.dao.base.PBService;
import br.com.uninorte.agendamento.persistence.model.Consulta;
import br.com.uninorte.agendamento.persistence.model.Medico;
import br.com.uninorte.agendamento.persistence.model.QConsulta;
import br.com.uninorte.agendamento.persistence.model.QMedico;
import br.com.uninorte.agendamento.persistence.repositories.IConsultaRepository;
import br.com.uninorte.agendamento.persistence.repositories.IMedicoRepository;


@Service
public class ConsultaDao extends PBService<Consulta, Long, QConsulta, IConsultaRepository>{

	private EntityManager entityManager;
	
	@Autowired
	public ConsultaDao(IConsultaRepository repository,EntityManager entityManager) {
		super(repository);
	}


}
