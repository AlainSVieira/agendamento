package br.com.uninorte.agendamento.rest.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.uninorte.agendamento.persistence.dao.PacienteDao;
import br.com.uninorte.agendamento.persistence.model.Paciente;
import br.com.uninorte.agendamento.rest.dto.PacienteDTO;
import br.com.uninorte.agendamento.rest.services.base.BaseBO;

@Service
public class PacienteBO extends BaseBO<Paciente, PacienteDao, PacienteDTO, Long> {

	protected PacienteBO(PacienteDao dao) {
		super(dao);
	}

	@Override
	public PacienteDTO save(PacienteDTO create, UserDetails account) {
		Paciente docDriver = create.getModel();
		return new PacienteDTO(getDao().save(docDriver), false);
	}

	@Override
	public PacienteDTO update(PacienteDTO update, UserDetails account) {
		Paciente model = getDao().findOne(update.getUuid());
		update.copyTo(model);
		return new PacienteDTO(getDao().save(model), false);
	}


}
