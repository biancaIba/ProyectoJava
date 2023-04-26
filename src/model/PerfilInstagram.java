package model;
import parser.CargaXML;
import java.util.*;

public class PerfilInstagram {
	private TreeSet<Publicacion> listaPublicaciones;
	private ArrayList<Album> listaAlbumes;
	public PerfilInstagram() {
		this.listaPublicaciones = new TreeSet<Publicacion>();
		this.listaAlbumes=new ArrayList<Album>();
	}

	public static void main(String[] args) {
		
		PerfilInstagram perfil=new PerfilInstagram();
		perfil.cargarPublicaciones();
		perfil.muestraLista();
		
	}
	
	public void cargarPublicaciones() {
		CargaXML cargador =new CargaXML();
	    cargador.cargarPublicacionesXML(this);// es como si le pasara perfil osea la instancia donde se ejecuta el cargarPublicaciones() 
	}
	
	public void muestraLista() {
		for(Publicacion p: listaPublicaciones) {
			System.out.println(p.toString());
		}
	}
	
	public void addPublicacion(Publicacion publi) {
		if(publi!= null) {
			listaPublicaciones.add(publi);
	
		}
	}
	
	public void confListaReproduccion() {
		/*
		 * Permita la consulta y reproducción de un grupo de publicaciones seleccionadas
		 * de acuerdo a filtros flexibles aplicados a los atributos que se crean más
		 * relevantes. El orden de reproducción puede ser configurable de acuerdo a
		 * algún o algunos atributos.
		 */
	}

	public void reportePublicacionesPorTipo() {
		/*
		 * Listado completo de publicaciones agrupadas por tipo (audio, foto, video).
		 * Para cada grupo, ordenar por cantidad de “me gusta” descendente, mostrar
		 * cantidad de publicaciones y cantidad de “me gusta” promedio de cada uno de
		 * los 3 tipos.(por pantalla y en archivos de texto):
		 */
	}

	public void resporteAlfabeticoAlbumes() {
		/*
		 * Listado alfabético de Álbumes, detallando para cada uno cantidad de
		 * publicaciones subidas en un rango de fechas solicitado al operador. Incluir
		 * la cantidad de comentarios correspondientes a esas publicaciones.(por
		 * pantalla y en archivos de texto):
		 */
	}

	@Override
	public String toString() {
		return "PerfilInstagram [listaPublicaciones=" + listaPublicaciones + ", listaAlbumes=" + listaAlbumes + "]";
	}
}
