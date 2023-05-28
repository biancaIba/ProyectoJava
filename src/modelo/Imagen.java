package modelo;

import java.time.LocalDate;
import modelo.enums.EnumTipoFiltro;
import modelo.enums.EnumTipoPublicacion;
import modelo.interfaces.IFiltrable;

// TODO: Auto-generated Javadoc
/**
 * La Clase Imagen. 
 * Extiende de La Clase Publicacion. 
 * Implementa interface Filtrable.
 */
public class Imagen extends Publicacion implements IFiltrable {

	/** Estatico serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** La resolucion. */
	private String resolucion;

	/** El ancho. */
	private int ancho;

	/** El alto. */
	private int alto;

	/** La duracion. */
	private float duracion;

	/** La duracion generica. */
	private static float DURACION_GENERICA = 2; // [Segundos]

	/** El filtro. */
	private EnumTipoFiltro filtro;

	/**
	 * Instancia una nueva imagen.
	 *
	 * @param nombrePublicacion el nombre de la publicacion
	 * @param fechaSubida       la fecha de subida
	 * @param cantMG            la cantidad de Me Gustas
	 * @param resolucion        la resolucion
	 * @param ancho             el ancho
	 * @param alto              el alto
	 */
	public Imagen(String nombrePublicacion, LocalDate fechaSubida, int cantMG, String resolucion, int ancho, int alto) {
		super(nombrePublicacion, fechaSubida, cantMG, EnumTipoPublicacion.IMAGEN);
		this.resolucion = resolucion;
		this.ancho = ancho;
		this.alto = alto;
		this.duracion = DURACION_GENERICA;
		this.filtro = EnumTipoFiltro.SIN_FILTRO;
	}

	/**
	 * Obtiene la resolucion.
	 *
	 * @return la resolucion
	 */
	public String getResolucion() {
		return resolucion;
	}

	/**
	 * Setea la resolucion.
	 *
	 * @param resolucion the new resolucion
	 */
	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}

	/**
	 * Obtiene el ancho.
	 *
	 * @return el ancho
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * Setea el ancho.
	 *
	 * @param ancho the new ancho
	 */
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	/**
	 * Obtiene el alto.
	 *
	 * @return el alto
	 */
	public int getAlto() {
		return alto;
	}

	/**
	 * Setea el alto.
	 *
	 * @param alto the new alto
	 */
	public void setAlto(int alto) {
		this.alto = alto;
	}

	/**
	 * To string.
	 *
	 * @return una cadena de texto que representa los atributos particulares de la imagen.
	 */
	@Override
	public String toString() {
		return "Imagen{" + super.toString() + ", Resolucion=" + resolucion + ", Ancho=" + ancho + ", Alto=" + alto
				+ '}';
	}

	/**
	 * Aplicar filtro. De la interface Filtrable.
	 *
	 * @param filtro the filtro
	 */
	public void aplicarFiltro(EnumTipoFiltro filtro) {
		this.filtro = filtro;
	}

	/**
	 * Obtiene el filtro.
	 *
	 * @return el filtro
	 */
	public EnumTipoFiltro getFiltro() {
		return filtro;
	}

	/**
	 * Obtiene la duracion actualizada según la configuración particular de la reproducción.
	 *
	 * @return duracion [segundos]
	 */
	//
	public float getDuracion() {
		return duracion;
	}


}
