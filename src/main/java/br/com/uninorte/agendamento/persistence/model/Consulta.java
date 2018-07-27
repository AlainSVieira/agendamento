package br.com.uninorte.agendamento.persistence.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


import br.com.uninorte.agendamento.persistence.model.base.Base;
import lombok.Getter;
import lombok.Setter;

@Entity
@DynamicUpdate
@DynamicInsert
public class Consulta extends Base{

	@Getter
	@Setter
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Getter
	@Setter
	@ManyToOne
	private Paciente paciente;
	
	@Getter
	@Setter
	@ManyToOne
	private Exame exame;
	
	@Getter
	@Setter
	@ManyToOne
	private Medico medico;
}
