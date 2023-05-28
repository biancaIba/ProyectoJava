package excepciones;

/**
 * La Clase DuracionInvalidaExcepcion.
 */
public class DuracionInvalidaExcepcion extends Exception{
	
	/**
	 * Instancia una nueva excepcion Duracion Invalida.
	 *
	 * @param mensaje
	 */
	public DuracionInvalidaExcepcion(String mensaje) {
        super(mensaje);
    }
}
