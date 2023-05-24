package model;
import java.time.LocalDate;
public class Audio extends Publicacion implements Durable {
	private int velocidadBits;
	private float duracion;
	private float inicio;
	private float fin;

	public Audio(String nombrePublicacion, LocalDate fechaSubida, int cantMG, int velocidadBits, float duracion) {
		super(nombrePublicacion, fechaSubida, cantMG,EnumTipoPublicacion.AUDIO);
		this.velocidadBits = velocidadBits;
		this.duracion = duracion;
		this.inicio = 0;
		this.fin = duracion;
	}
	
	@Override
	public String toString() {
	    return "Audio{" +
	        super.toString() +
	        ", velocidadBits=" + velocidadBits +
	        '}';
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
	
	public void setDuracion(float duracion) {
		this.duracion = duracion;
	}
	
	public float getInicio() {
		return inicio;
	}
	
	public float getFin() {
		return fin;
	}
	
	public void reproducir() {
		
	}

	public void avanzar(float inicioRelativo) {
		this.inicio = inicioRelativo;
	}
	
	public void detener(float finRelativo) {
		this.fin = finRelativo;
	}
	
}
