package modelo.interfaces;

import excepciones.DuracionInvalidaExcepcion;

/**
 * La Interface Durable.
 */
public interface IDurable {

	/**
	 * Avanzar.
	 *
	 * @param inicioRelativo
	 * @throws DuracionInvalidaExcepcion
	 */
	void avanzar(float inicioRelativo) throws DuracionInvalidaExcepcion;

	/**
	 * Detener.
	 *
	 * @param finRelativo
	 * @throws DuracionInvalidaExcepcion
	 */
	void detener(float finRelativo) throws DuracionInvalidaExcepcion;

	/**
	 * Obtiene el inicio.
	 *
	 * @return el inicio
	 */
	float getInicio();

	/**
	 * Obtiene el fin.
	 *
	 * @return el fin
	 */
	float getFin();

	/**
	 * Obtiene el fin de reproduccion.
	 *
	 * @return el fin reproduccion
	 */
	float getFinReproduccion();

	/**
	 * Obtiene la duracion.
	 *
	 * @return the duracion
	 */
	float getDuracion();
}
