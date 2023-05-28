package modelo.interfaces;

import modelo.enums.EnumTipoFiltro;

/**
 * La Interface Filtrable.
 */
public interface IFiltrable {

	/**
	 * Aplicar filtro.
	 *
	 * @param filtro
	 */
	void setFiltro(EnumTipoFiltro filtro);

	/**
	 * Obtiene el filtro.
	 *
	 * @return el filtro
	 */
	EnumTipoFiltro getFiltro();
}
