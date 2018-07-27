package br.com.uninorte.agendamento.rest.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.uninorte.agendamento.persistence.dao.EspecialidadeDao;
import br.com.uninorte.agendamento.persistence.dao.MedicoDao;
import br.com.uninorte.agendamento.persistence.model.Especialidade;
import br.com.uninorte.agendamento.persistence.model.Medico;
import br.com.uninorte.agendamento.rest.dto.EspecialidadeDTO;
import br.com.uninorte.agendamento.rest.dto.MedicoDTO;
import br.com.uninorte.agendamento.rest.services.base.BaseBO;

@Service
public class EspecialidadeBO extends BaseBO<Especialidade, EspecialidadeDao, EspecialidadeDTO, Long> {

	protected EspecialidadeBO(EspecialidadeDao dao) {
		super(dao);
	}

	@Override
	public EspecialidadeDTO save(EspecialidadeDTO create, UserDetails account) {
		Especialidade docDriver = create.getModel();
		return new EspecialidadeDTO(getDao().save(docDriver), false);
	}

	@Override
	public EspecialidadeDTO update(EspecialidadeDTO update, UserDetails account) {
		Especialidade model = getDao().findOne(update.getUuid());
		update.copyTo(model);
		return new EspecialidadeDTO(getDao().save(model), false);
	}


}
