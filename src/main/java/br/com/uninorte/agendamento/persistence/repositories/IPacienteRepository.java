package br.com.uninorte.agendamento.persistence.repositories;

import br.com.uninorte.agendamento.persistence.model.Medico;
import br.com.uninorte.agendamento.persistence.model.Paciente;
import br.com.uninorte.agendamento.persistence.model.QMedico;
import br.com.uninorte.agendamento.persistence.model.QPaciente;
import br.com.uninorte.agendamento.persistence.repositories.base.IBase;

public interface IPacienteRepository extends IBase<Paciente, Long, QPaciente>{

}
