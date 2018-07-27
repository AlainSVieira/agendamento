package br.com.uninorte.agendamento.persistence.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uninorte.agendamento.persistence.dao.base.PBService;
import br.com.uninorte.agendamento.persistence.model.Exame;
import br.com.uninorte.agendamento.persistence.model.Medico;
import br.com.uninorte.agendamento.persistence.model.QExame;
import br.com.uninorte.agendamento.persistence.model.QMedico;
import br.com.uninorte.agendamento.persistence.repositories.IExameRepository;
import br.com.uninorte.agendamento.persistence.repositories.IMedicoRepository;


@Service
public class ExameDao extends PBService<Exame, Long, QExame, IExameRepository>{

	private EntityManager entityManager;
	
	@Autowired
	public ExameDao(IExameRepository repository,EntityManager entityManager) {
		super(repository);
	}


}
