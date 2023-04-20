package instagram;

public class Video extends Multimedia {
	private String resolucion;
	private int cantCuadros;
	public Video(String nombrePublicacion, String fechaSubida, int cantMG,int duracion, String resolucion,int cantCuadros) {
		super(nombrePublicacion, fechaSubida, cantMG, duracion);
		this.resolucion=resolucion;
		this.cantCuadros=cantCuadros;
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

	@Override
	public String toString() {
		return "Video [resolucion=" + resolucion + ", cantCuadros=" + cantCuadros + "]";
	}



	public void aplicarFiltros() {
	
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
