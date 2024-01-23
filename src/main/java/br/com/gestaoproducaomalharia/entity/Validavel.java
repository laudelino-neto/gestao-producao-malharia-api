package br.com.gestaoproducaomalharia.entity;

import jakarta.persistence.Transient;

public interface Validavel {

	public boolean isPersistido();
	
	@Transient
	public default boolean isAtivo() {
		throw new UnsupportedOperationException("O método não possui implementação válida");
	}
	
}
