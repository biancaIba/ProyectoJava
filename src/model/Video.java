package model;

public class Video extends Publicacion implements Durable, Filtrable {
	private String resolucion;
	private int cantCuadros;

	public Video(String nombrePublicacion, String fechaSubida, int cantMG, String resolucion, int cantCuadros) {
		super(nombrePublicacion, fechaSubida, cantMG);
		this.resolucion = resolucion;
		this.cantCuadros = cantCuadros;
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

	public void avanzar() {
		

	}

	public void detener() {
		

	}
	
	public void aplicarFiltro() {
		
	}

}
