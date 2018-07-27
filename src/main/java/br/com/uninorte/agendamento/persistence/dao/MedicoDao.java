package br.com.uninorte.agendamento.persistence.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uninorte.agendamento.persistence.dao.base.PBService;
import br.com.uninorte.agendamento.persistence.model.Medico;
import br.com.uninorte.agendamento.persistence.model.QMedico;
import br.com.uninorte.agendamento.persistence.repositories.IMedicoRepository;


@Service
public class MedicoDao extends PBService<Medico, Long, QMedico, IMedicoRepository>{

	private EntityManager entityManager;
	
	@Autowired
	public MedicoDao(IMedicoRepository repository,EntityManager entityManager) {
		super(repository);
	}


}
