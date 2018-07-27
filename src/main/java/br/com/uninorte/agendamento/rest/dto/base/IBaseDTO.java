package br.com.uninorte.agendamento.rest.dto.base;

public interface IBaseDTO<T> {
	T getModel();
	
	T copyTo(T newInstance);

}
