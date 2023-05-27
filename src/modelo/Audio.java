package modelo;

import java.time.LocalDate;

import excepciones.DuracionInvalidaExcepcion;
import modelo.enums.EnumTipoPublicacion;
import modelo.interfaces.IDurable;

public class Audio extends Publicacion implements IDurable {

	private static final long serialVersionUID = 1L;
	private int velocidadBits;
	private float duracion;
	private float inicio;
	private float fin;
	private float finReproduccion;

	public float getFinReproduccion() {
		return finReproduccion;
	}

	public Audio(String nombrePublicacion, LocalDate fechaSubida, int cantMG, int velocidadBits, float duracion) {
		super(nombrePublicacion, fechaSubida, cantMG, EnumTipoPublicacion.AUDIO);
		this.velocidadBits = velocidadBits;
		this.inicio = 0;
		this.fin = duracion;
		this.duracion = duracion;
		this.finReproduccion = duracion;
	}

	@Override
	public String toString() {
		return "Audio{" + super.toString() + ", velocidadBits=" + velocidadBits + '}';
	}

	public int getVelocidadBits() {
		return velocidadBits;
	}

	public void setVelocidadBits(int velocidadBits) {
		this.velocidadBits = velocidadBits;
	}

	public float getDuracion() {
		return duracion;
	}

	public float getInicio() {
		return inicio;
	}

	public float getFin() {
		return fin;
	}

	public void reproducir() {

	}

	private void actualizarDuracion() {
		this.duracion = this.finReproduccion - this.inicio;
	}

	public void avanzar(float inicioRelativo) throws DuracionInvalidaExcepcion {
		if (inicioRelativo >= 0 && inicioRelativo < this.finReproduccion) {
			this.inicio = inicioRelativo;
			actualizarDuracion();
		} else {
			throw new DuracionInvalidaExcepcion(
					"El tiempo de inicio debe ser menor al de detención y mayor o igual a 0");
		}
	}

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
