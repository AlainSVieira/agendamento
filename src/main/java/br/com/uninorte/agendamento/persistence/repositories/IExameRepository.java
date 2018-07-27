package br.com.uninorte.agendamento.persistence.repositories;

import br.com.uninorte.agendamento.persistence.model.Exame;
import br.com.uninorte.agendamento.persistence.model.Medico;
import br.com.uninorte.agendamento.persistence.model.QExame;
import br.com.uninorte.agendamento.persistence.model.QMedico;
import br.com.uninorte.agendamento.persistence.repositories.base.IBase;

public interface IExameRepository extends IBase<Exame, Long, QExame>{

}
