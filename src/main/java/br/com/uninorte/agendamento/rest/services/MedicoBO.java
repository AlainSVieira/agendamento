package br.com.uninorte.agendamento.rest.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.uninorte.agendamento.persistence.dao.MedicoDao;
import br.com.uninorte.agendamento.persistence.model.Medico;
import br.com.uninorte.agendamento.rest.dto.MedicoDTO;
import br.com.uninorte.agendamento.rest.services.base.BaseBO;

@Service
public class MedicoBO extends BaseBO<Medico, MedicoDao, MedicoDTO, Long> {

	protected MedicoBO(MedicoDao dao) {
		super(dao);
	}

	@Override
	public MedicoDTO save(MedicoDTO create, UserDetails account) {
		Medico docDriver = create.getModel();
		return new MedicoDTO(getDao().save(docDriver), false);
	}

	@Override
	public MedicoDTO update(MedicoDTO update, UserDetails account) {
		Medico model = getDao().findOne(update.getUuid());
		update.copyTo(model);
		return new MedicoDTO(getDao().save(model), false);
	}


}
