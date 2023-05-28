package excepciones;

/**
 * La Clase SinDatosExcepcion.
 *
 */
public class SinDatosExcepcion extends Exception{
	
	/**
	 * Instancia una nueva excepcion Sin Datos.
	 *
	 * @param mensaje
	 */
	public SinDatosExcepcion(String mensaje) {
        super(mensaje);
    }
}
