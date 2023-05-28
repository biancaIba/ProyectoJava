package utilidades;

/**
 * Clase AlertaUtilidades.
 */
public class AlertaUtilidades {
	/**
	 * Muestra una advertencia en la consola.
	 *
	 * @param mensaje : mensaje de advertencia a mostrar.
	 */
	public static void mostrarAdvertencia(String mensaje) {
		String ANSI_YELLOW = "\u001B[33m";
		String ANSI_RESET = "\u001B[0m";
		System.out.println(ANSI_YELLOW + "Advertencia: " + ANSI_RESET + mensaje);
	}
}
