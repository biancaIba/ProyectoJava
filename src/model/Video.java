package model;
import java.time.LocalDate;
public class Video extends Publicacion implements Durable, Filtrable {
	private String resolucion;
	private int cantCuadros;
	private float duracion;
	private float inicio;
	private float fin;

	public Video(String nombrePublicacion, LocalDate fechaSubida, int cantMG, String resolucion, int cantCuadros, float duracion) {
		super(nombrePublicacion, fechaSubida, cantMG, EnumTipoPublicacion.VIDEO);
		this.resolucion = resolucion;
		this.cantCuadros = cantCuadros;
		this.duracion = duracion;
		this.inicio = 0;
		this.fin = duracion;
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
	
	public void setDuracion(float duracion) {
		this.duracion = duracion;
	}

	@Override
	public String toString() {
	    return "Video{" +
	        super.toString() +
	        ", Resolucion=" + resolucion +", Cantidad de cuadros=" + cantCuadros +
	        '}';
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
	
	public void aplicarFiltro() {
		
	}
	
}
