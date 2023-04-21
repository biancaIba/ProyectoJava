package model;

public class Audio extends Publicacion implements Durable {
	private int velocidadBits;

	public Audio(String nombrePublicacion, String fechaSubida, int cantMG, int velocidadBits) {
		super(nombrePublicacion, fechaSubida, cantMG);
		this.velocidadBits = velocidadBits;
	}

	public int getVelocidadBits() {
		return velocidadBits;
	}

	public void setVelocidadBits(int velocidadBits) {
		this.velocidadBits = velocidadBits;
	}

	public void avanzar() {
		
	}
	
	public void detener() {
		
	}

}
