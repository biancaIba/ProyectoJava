/**
 * 
 */
package modelo.interfaces;

import modelo.enums.EnumTipoFiltro;

public interface IFiltrable {
	void aplicarFiltro(EnumTipoFiltro filtro);
	EnumTipoFiltro getFiltro();
}
