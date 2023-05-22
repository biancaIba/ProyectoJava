package model;
import java.time.LocalDate;
public class Audio extends Publicacion implements Durable {
	private int velocidadBits;
	private float duracion;

	public Audio(String nombrePublicacion, LocalDate fechaSubida, int cantMG, int velocidadBits, float duracion) {
		super(nombrePublicacion, fechaSubida, cantMG,EnumTipoPublicacion.AUDIO);
		this.velocidadBits = velocidadBits;
		this.duracion = duracion;
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

	public void avanzar() {
		
	}
	
	public void detener() {
		
	}
	
}
