package modelo;

import java.time.LocalDate;
import excepciones.DuracionInvalidaExcepcion;
import modelo.enums.EnumTipoPublicacion;
import modelo.interfaces.IDurable;

/**
 * La Clase Audio. 
 * Extiende de La Clase Publicacion. 
 * Implementa interface Durable.
 */
public class Audio extends Publicacion implements IDurable {

	/** Estatico serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** La velocidad en bits. */
	private int velocidadBits;

	/** La duracion. */
	private float duracion;

	/** El inicio. */
	private float inicio;

	/** El fin. */
	private float fin;

	/** El fin de la reproduccion. */
	private float finReproduccion;

	/**
	 * Instancia un nuevo audio.
	 *
	 * @param nombrePublicacion el nombre publicacion
	 * @param fechaSubida       la fecha subida
	 * @param cantMG            la cantidad de Me Gustas
	 * @param velocidadBits     la velocidad en bits
	 * @param duracion          la duracion
	 */
	public Audio(String nombrePublicacion, LocalDate fechaSubida, int cantMG, int velocidadBits, float duracion) {
		super(nombrePublicacion, fechaSubida, cantMG, EnumTipoPublicacion.AUDIO);
		this.velocidadBits = velocidadBits;
		this.inicio = 0;
		this.fin = duracion;
		this.duracion = duracion;
		this.finReproduccion = duracion;
	}
	
	/**
	 * Obtiene el momento del fin de la reproduccion.
	 *
	 * @return el fin reproduccion
	 */
	public float getFinReproduccion() {
		return finReproduccion;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Audio{" + super.toString() + ", velocidadBits=" + velocidadBits + '}';
	}

	/**
	 * Obtiene la velocidad en bits.
	 *
	 * @return la velocidad en bits
	 */
	public int getVelocidadBits() {
		return velocidadBits;
	}

	/**
	 * Setea la velocidad en bits.
	 *
	 * @param velocidadBits
	 */
	public void setVelocidadBits(int velocidadBits) {
		this.velocidadBits = velocidadBits;
	}

	/**
	 * Obtiene la duracion.
	 *
	 * @return la duracion
	 */
	public float getDuracion() {
		return duracion;
	}

	/**
	 * Obtiene el inicio de la reproduccion.
	 *
	 * @return el inicio
	 */
	public float getInicioReproduccion() {
		return inicio;
	}

	/**
	 * Obtiene el fin.
	 *
	 * @return el fin
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
	 * Actualiza la duracion de la reproduccion del audio.
	 *
	 * @param inicioRelativo
	 * @throws DuracionInvalidaExcepcion
	 */
	public void avanzar(float inicioRelativo) throws DuracionInvalidaExcepcion {
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
	 * Actualiza la duracion de la reproduccion del audio.
	 *
	 * @param finRelativo
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

}
