package br.com.uninorte.agendamento.rest.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.uninorte.agendamento.persistence.dao.ExameDao;
import br.com.uninorte.agendamento.persistence.dao.MedicoDao;
import br.com.uninorte.agendamento.persistence.model.Exame;
import br.com.uninorte.agendamento.persistence.model.Medico;
import br.com.uninorte.agendamento.rest.dto.ExameDTO;
import br.com.uninorte.agendamento.rest.dto.MedicoDTO;
import br.com.uninorte.agendamento.rest.services.base.BaseBO;

@Service
public class ExameBO extends BaseBO<Exame, ExameDao, ExameDTO, Long> {

	protected ExameBO(ExameDao dao) {
		super(dao);
	}

	@Override
	public ExameDTO save(ExameDTO create, UserDetails account) {
		Exame docDriver = create.getModel();
		return new ExameDTO(getDao().save(docDriver), false);
	}

	@Override
	public ExameDTO update(ExameDTO update, UserDetails account) {
		Exame model = getDao().findOne(update.getUuid());
		update.copyTo(model);
		return new ExameDTO(getDao().save(model), false);
	}


}
