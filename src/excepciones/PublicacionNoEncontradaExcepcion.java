package excepciones;

/**
 * La Clase PublicacionNoEncontradaExcepcion.
 *
 */
public class PublicacionNoEncontradaExcepcion extends Exception{
	
	/**
	 * Instancia una nueva excepcion Publicacion No Encontrada.
	 *
	 * @param mensaje
	 */
	public PublicacionNoEncontradaExcepcion(String mensaje) {
        super(mensaje);
    }
}
