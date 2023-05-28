package modelo.interfaces;

import excepciones.DuracionInvalidaExcepcion;

/**
 * Interface Durable.
 */
public interface IDurable {

	/**
	 * Avanzar.
	 *
	 * @param inicioRelativo [segundos]
	 * @throws DuracionInvalidaExcepcion
	 */
	void avanzar(float inicioRelativo) throws DuracionInvalidaExcepcion;

	/**
	 * Detener.
	 *
	 * @param finRelativo [segundos]
	 * @throws DuracionInvalidaExcepcion
	 */
	void detener(float finRelativo) throws DuracionInvalidaExcepcion;

	/**
	 * Obtiene el tiempo de inicio configurado en la reproducción.
	 *
	 * @return tiempo de inicio [segundos]
	 */
	float getInicioReproduccion();

	/**
	 * Obtiene el tiempo de finalización original.
	 *
	 * @return tiempo de finalización original [segundos]
	 */
	float getFinOriginal();

	/**
	 * Obtiene el fin configurado en la reproducción.
	 *
	 * @return tiempo de detencón configurada en la reproducción [segundos]
	 */
	float getFinReproduccion();
}
