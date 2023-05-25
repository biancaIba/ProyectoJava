/**
 * 
 */
package model;

public interface Filtrable {
	void aplicarFiltro(EnumTipoFiltro filtro);
	EnumTipoFiltro getFiltro();
}
