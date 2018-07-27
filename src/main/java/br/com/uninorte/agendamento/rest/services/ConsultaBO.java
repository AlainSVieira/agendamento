package br.com.uninorte.agendamento.rest.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.uninorte.agendamento.persistence.dao.ConsultaDao;
import br.com.uninorte.agendamento.persistence.dao.MedicoDao;
import br.com.uninorte.agendamento.persistence.model.Consulta;
import br.com.uninorte.agendamento.persistence.model.Medico;
import br.com.uninorte.agendamento.rest.dto.ConsultaDTO;
import br.com.uninorte.agendamento.rest.dto.MedicoDTO;
import br.com.uninorte.agendamento.rest.services.base.BaseBO;

@Service
public class ConsultaBO extends BaseBO<Consulta, ConsultaDao, ConsultaDTO, Long> {

	protected ConsultaBO(ConsultaDao dao) {
		super(dao);
	}

	@Override
	public ConsultaDTO save(ConsultaDTO create, UserDetails account) {
		Consulta docDriver = create.getModel();
		return new ConsultaDTO(getDao().save(docDriver), false);
	}

	@Override
	public ConsultaDTO update(ConsultaDTO update, UserDetails account) {
		Consulta model = getDao().findOne(update.getUuid());
		update.copyTo(model);
		return new ConsultaDTO(getDao().save(model), false);
	}


}
