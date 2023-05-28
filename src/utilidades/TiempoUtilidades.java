/*
 * 
 */
package utilidades;

/**
 *  Clase TiempoUtilidades.
 */
public class TiempoUtilidades {
	
	/**
	 * Devuelve la duración formateada a horas, minutos y segundos.
	 *
	 * @param tiempoTotal El tiempo total en segundos.
	 * @return Una cadena con formato "00:00:00" que representa la duración.
	 */
	public static String duracionFormateada(double tiempoTotal) {
		int horas = (int) (tiempoTotal / 3600);
		int minutos = (int) ((tiempoTotal % 3600) / 60);
		int segundos = (int) (tiempoTotal % 60);
		return String.format("%02d:%02d:%02d", horas, minutos, segundos);
	}
}
