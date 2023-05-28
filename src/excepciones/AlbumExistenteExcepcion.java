package excepciones;

/**
 * La Clase AlbumExistenteExcepcion.
 */
public class AlbumExistenteExcepcion extends Exception {

	/**
	 * Instancia una nueva excepcion Album Existente.
	 *
	 * @param mensaje
	 */
	public AlbumExistenteExcepcion(String mensaje) {
		super(mensaje);
	}
}
