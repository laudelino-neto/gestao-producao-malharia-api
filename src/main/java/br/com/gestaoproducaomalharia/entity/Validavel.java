package br.com.gestaoproducaomalharia.entity;

public interface Validavel {

	public boolean isPersistido();
	
	public default boolean isAtivo() {
		throw new UnsupportedOperationException("O método não possui implementação válida");
	}
	
}
