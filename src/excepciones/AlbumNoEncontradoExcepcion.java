package excepciones;

/**
 * La Clase AlbumNoEncontradoExcepcion.
 */
public class AlbumNoEncontradoExcepcion extends Exception {
	
	/**
	 * Instancia una nueva excepcion Album No Encontrado.
	 *
	 * @param mensaje
	 */
	public AlbumNoEncontradoExcepcion(String mensaje) {
        super(mensaje);
    }
}
