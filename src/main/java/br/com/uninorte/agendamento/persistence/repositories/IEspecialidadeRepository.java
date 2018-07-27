package br.com.uninorte.agendamento.persistence.repositories;

import br.com.uninorte.agendamento.persistence.model.Especialidade;
import br.com.uninorte.agendamento.persistence.model.Medico;
import br.com.uninorte.agendamento.persistence.model.QEspecialidade;
import br.com.uninorte.agendamento.persistence.model.QMedico;
import br.com.uninorte.agendamento.persistence.repositories.base.IBase;

public interface IEspecialidadeRepository extends IBase<Especialidade, Long, QEspecialidade>{

}
