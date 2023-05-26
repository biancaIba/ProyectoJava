package model;

import java.time.LocalDate;

import exception.DuracionInvalidaException;

public class Video extends Publicacion implements Durable, Filtrable {
	private String resolucion;
	private int cantCuadros;
	private float duracion;
	private float inicio;
	private float fin;
	private float finReproduccion;
	private EnumTipoFiltro filtro;

	public float getFinReproduccion() {
		return finReproduccion;
	}

	public Video(String nombrePublicacion, LocalDate fechaSubida, int cantMG, String resolucion, int cantCuadros,
			float duracion) {
		super(nombrePublicacion, fechaSubida, cantMG, EnumTipoPublicacion.VIDEO);
		this.resolucion = resolucion;
		this.cantCuadros = cantCuadros;
		this.duracion = duracion;
		this.inicio = 0;
		this.fin = duracion;
		this.finReproduccion = duracion;
		this.filtro = EnumTipoFiltro.SIN_FILTRO;
	}

	public String getResolucion() {
		return resolucion;
	}

	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}

	public int getCantCuadros() {
		return cantCuadros;
	}

	public void setCantCuadros(int cantCuadros) {
		this.cantCuadros = cantCuadros;
	}

	public float getDuracion() {
		return duracion;
	}

	@Override
	public String toString() {
		return "Video{" + super.toString() + ", Resolucion=" + resolucion + ", Cantidad de cuadros=" + cantCuadros
				+ '}';
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

	public void avanzar(float inicioRelativo) throws DuracionInvalidaException {
		System.out.println("inicioRelativo " + inicioRelativo + " this.finReproduccion " + this.finReproduccion);
		if (inicioRelativo >= 0 && inicioRelativo < this.finReproduccion) {
			this.inicio = inicioRelativo;
			actualizarDuracion();
		} else {
			throw new DuracionInvalidaException(
					"El tiempo de inicio debe ser menor al de detención y mayor o igual a 0");
		}
	}

	public void detener(float finRelativo) throws DuracionInvalidaException {
		if (finRelativo > this.inicio && finRelativo <= this.fin) {
			this.finReproduccion = finRelativo;
			actualizarDuracion();
		} else {
			throw new DuracionInvalidaException(
					"El tiempo de detención debe ser mayor al de inicio y menor o igual a la duración original");
		}
	}

	public void aplicarFiltro(EnumTipoFiltro filtro) {
		this.filtro = filtro;
	}

	public EnumTipoFiltro getFiltro() {
		return filtro;
	}
}
