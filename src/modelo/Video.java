package modelo;

import java.time.LocalDate;
import excepciones.DuracionInvalidaExcepcion;
import modelo.enums.EnumTipoFiltro;
import modelo.enums.EnumTipoPublicacion;
import modelo.interfaces.IDurable;
import modelo.interfaces.IFiltrable;

/**
 * La Clase Video.
 * Extiende Publicacion.
 * Implementa Durable y Filtrable.
 */
public class Video extends Publicacion implements IDurable, IFiltrable {

	/** Estatico serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** La resolucion. */
	private String resolucion;
	
	/** La cantidad de cuadros. */
	private int cantidadCuadros;
	
	/** La duracion. */
	private float duracion;
	
	/** El inicio. */
	private float inicio;
	
	/** El fin. */
	private float fin;
	
	/** El fin de reproduccion. */
	private float finReproduccion;
	
	/** El filtro. */
	private EnumTipoFiltro filtro;

	/**
	 * Instancia un nuevo video.
	 *
	 * @param nombrePublicacion el nombre de la publicacion
	 * @param fechaSubida la fecha subida
	 * @param cantMG la cantidad de Me Gustas
	 * @param resolucion la resolucion
	 * @param cantCuadros la cantidad de cuadros
	 * @param duracion la duracion
	 */
	public Video(String nombrePublicacion, LocalDate fechaSubida, int cantMG, String resolucion, int cantCuadros,
			float duracion) {
		super(nombrePublicacion, fechaSubida, cantMG, EnumTipoPublicacion.VIDEO);
		this.resolucion = resolucion;
		this.cantidadCuadros = cantCuadros;
		this.duracion = duracion;
		this.inicio = 0;
		this.fin = duracion;
		this.finReproduccion = duracion;
		this.filtro = EnumTipoFiltro.SIN_FILTRO;
	}
	
	/**
	 * Obtiene el fin configurado en la reproducción.
	 *
	 * @return tiempo de detencón configurada en la reproducción [segundos]
	 */
	public float getFinReproduccion() {
		return finReproduccion;
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
	 * @param resolucion
	 */
	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}

	/**
	 * Obtiene la cantidad de cuadros.
	 *
	 * @return la cantidad de cuadros
	 */
	public int getCantidadCuadros() {
		return cantidadCuadros;
	}

	/**
	 * Setea la cantidad de cuadros.
	 *
	 * @param cantCuadros
	 */
	public void setCantCuadros(int cantCuadros) {
		this.cantidadCuadros = cantCuadros;
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

	/**
	 * To string.
	 *
	 * @return una cadena de texto que representa los atributos particulares de los videos.
	 */
	@Override
	public String toString() {
		return "Video{" + super.toString() + ", Resolucion=" + resolucion + ", Cantidad de cuadros=" + cantidadCuadros
				+ '}';
	}

	/**
	 * Obtiene el tiempo de inicio configurado en la reproducción.
	 *
	 * @return tiempo de inicio [segundos]
	 */
	public float getInicioReproduccion() {
		return inicio;
	}

	/**
	 * Obtiene el tiempo de finalización original.
	 *
	 * @return tiempo de finalización original [segundos]
	 */
	public float getFinOriginal() {
		return fin;
	}

	/**
	 * Actualiza la duracion de la reproduccion.
	 */
	private void actualizarDuracion() {
		this.duracion = this.finReproduccion - this.inicio;
	}

	/**
	 * Avanzar. De la interface Durable.
	 * Actualiza la duracion de la reproduccion del video.
	 *
	 * @param inicioRelativo [segundos]
	 * @throws DuracionInvalidaExcepcion
	 */
	public void avanzar(float inicioRelativo) throws DuracionInvalidaExcepcion {
		System.out.println("inicioRelativo " + inicioRelativo + " this.finReproduccion " + this.finReproduccion);
		if (inicioRelativo >= 0 && inicioRelativo < this.finReproduccion) {
			this.inicio = inicioRelativo;
			actualizarDuracion();
		} else {
			throw new DuracionInvalidaExcepcion(
					"El tiempo de inicio debe ser menor al de detención y mayor o igual a 0");
		}
	}

	/**
	 * Detener. De la interface Durable.
	 * Actualiza la duracion de la reproduccion del video.
	 *
	 * @param finRelativo [segundos]
	 * @throws DuracionInvalidaExcepcion
	 */
	public void detener(float finRelativo) throws DuracionInvalidaExcepcion {
		if (finRelativo > this.inicio && finRelativo <= this.fin) {
			this.finReproduccion = finRelativo;
			actualizarDuracion();
		} else {
			throw new DuracionInvalidaExcepcion(
					"El tiempo de detención debe ser mayor al de inicio y menor o igual a la duración original");
		}
	}

	/**
	 * Aplicar filtro. De la interface Filtrable.
	 *
	 * @param filtro
	 */
	public void setFiltro(EnumTipoFiltro filtro) {
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
}
