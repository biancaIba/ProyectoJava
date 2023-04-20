package instagram;
import java.util.ArrayList;
public abstract class Publicacion {
	private String nombrePublicacion;
	private String fechaSubida;
	private int cantMG;
	private ArrayList<String> listaEtiquetas;
	private ArrayList<String> listaComentarios;
	private ArrayList<Album> listaAlbumes;	//0 o mas albumes
	
	 public Publicacion(String nombrePublicacion, String fechaSubida, int cantMG) {
	    this.nombrePublicacion = nombrePublicacion;
	    this.fechaSubida = fechaSubida;
	    this.cantMG = cantMG;
	  //creo una lista vacia de Etiquetas,Comentarios y Albumes
	    this.listaEtiquetas = new ArrayList<String>();
	    this.listaComentarios = new ArrayList<String>();
	    this.listaAlbumes = new ArrayList<Album>();
	 }
	public String getNombrePublicacion() {
		return nombrePublicacion;
	}
	public void setNombrePublicacion(String nombrePublicacion) {
		this.nombrePublicacion = nombrePublicacion;
	}
	public String getFechaSubida() {
		return fechaSubida;
	}
	public void setFechaSubida(String fechaSubida) {
		this.fechaSubida = fechaSubida;
	}
	public int getCantMG() {
		return cantMG;
	}
	public void setCantMG(int cantMG) {
		this.cantMG = cantMG;
	}
	@Override
	public String toString() {
		return "Publicacion [nombrePublicacion=" + nombrePublicacion + ", fechaSubida=" + fechaSubida + ", cantMG="
				+ cantMG + "]";
	}
	

}