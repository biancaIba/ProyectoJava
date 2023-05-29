package modelo.interfaces;

import modelo.enums.EnumTipoFiltro;

/**
 * La Interface Filtrable.
 */
public interface IFiltrable {

	/**
	 * aplicarFiltro
	 * Setea el filtro.
	 *
	 * @param filtro
	 */
	void setFiltro(EnumTipoFiltro filtro);

	/**
	 * getFiltro
	 * Devuelve el filtro.
	 *
	 * @return el filtro configurado
	 */
	EnumTipoFiltro getFiltro();
}
