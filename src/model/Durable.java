/**
 * 
 */
package model;

import exception.DuracionInvalidaException;


public interface Durable {
	void avanzar(float inicioRelativo) throws DuracionInvalidaException;
	void detener(float finRelativo) throws DuracionInvalidaException;
	float getInicio();
	float getFin();
	float getFinReproduccion();
	float getDuracion();
}
