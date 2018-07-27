package br.com.uninorte.agendamento.persistence.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.uninorte.agendamento.persistence.model.base.Base;
import lombok.Getter;
import lombok.Setter;


@Entity
@DynamicUpdate
@DynamicInsert
public class Medico extends Base{

	@Getter
	@Setter
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "especialidade")
	private Especialidade especialidade;
	
	@Getter
	@Setter
	private String CRM;
	
	@Getter
	@Setter
	private String Nome;
	
	@Getter
	@Setter
	@OneToMany(mappedBy = "medico", cascade = { CascadeType.ALL })
	@JsonIgnoreProperties("consultas") 
	private Collection<Consulta> consultas;
}
