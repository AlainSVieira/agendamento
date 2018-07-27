package br.com.uninorte.agendamento.persistence.model;

import javax.persistence.Entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import br.com.uninorte.agendamento.persistence.model.base.Base;
import lombok.Getter;
import lombok.Setter;

@Entity
@DynamicUpdate
@DynamicInsert
public class Especialidade extends Base{

	@Getter
	@Setter
	private String nome;
}
