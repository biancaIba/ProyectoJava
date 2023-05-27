/**
 * 
 */
package model;

import excepciones.DuracionInvalidaExcepcion;


public interface Durable {
	void avanzar(float inicioRelativo) throws DuracionInvalidaExcepcion;
	void detener(float finRelativo) throws DuracionInvalidaExcepcion;
	float getInicio();
	float getFin();
	float getFinReproduccion();
	float getDuracion();
}
