package br.com.uninorte.agendamento.persistence.repositories;

import br.com.uninorte.agendamento.persistence.model.Consulta;
import br.com.uninorte.agendamento.persistence.model.Medico;
import br.com.uninorte.agendamento.persistence.model.QConsulta;
import br.com.uninorte.agendamento.persistence.model.QMedico;
import br.com.uninorte.agendamento.persistence.repositories.base.IBase;

public interface IConsultaRepository extends IBase<Consulta, Long, QConsulta>{

}
