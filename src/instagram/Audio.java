package instagram;

public class Audio extends Multimedia {
	private int velocidadBits;
	 public Audio(String nombrePublicacion, String fechaSubida, int cantMG, int duracion, int velocidadBits) {
	    super(nombrePublicacion,fechaSubida,cantMG,duracion);
	    this.velocidadBits = velocidadBits;
	}
	
	public int getVelocidadBits() {
		return velocidadBits;
	}
	public void setVelocidadBits(int velocidadBits) {
		this.velocidadBits = velocidadBits;
	}
	@Override
	public void avanzar() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void detener() {
		// TODO Auto-generated method stub
		
	}



	
}
