package br.com.uninorte.agendamento.persistence.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
public class Paciente extends Base{

	@Getter
	@Setter
	private String Nome;
	
	@Getter
	@Setter
	@OneToMany(mappedBy = "paciente", cascade = { CascadeType.ALL })
	@JsonIgnoreProperties("consultas") 
	private Collection<Consulta> consultas;
}
