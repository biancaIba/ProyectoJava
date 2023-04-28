package model;

import parser.CargaXML;
import java.util.*;
import exception.AlbumNoEncontradoException;

public class PerfilInstagram {
	// SINGLETON
	String nombrePerfil; // BUSCAR COMO PONER EL NOMBRE (SETEAR O SOBRECARGAR GETINSTACE)
	private static PerfilInstagram perfil;
	private Set<Publicacion> listaPublicaciones;
	private List<Album> listaAlbumes;

	private PerfilInstagram() {
		this.listaPublicaciones = new TreeSet<Publicacion>();
		this.listaAlbumes = new ArrayList<Album>();
	}

	public static PerfilInstagram getInstance() {
		if (perfil == null)
			perfil = new PerfilInstagram();
		return perfil;
	}

	// PARA PRUEBAS LOCALES / BORRAR
	public static void main(String[] args) {
		PerfilInstagram.getInstance();
	}

	public void cargarPublicaciones() {
		CargaXML cargador = new CargaXML();
		cargador.cargarPublicacionesXML(this);// es como si le pasara perfil o sea la instancia donde se ejecuta el
												// cargarPublicaciones()
	}

	// PRUEBAS / BORRAR
	public void muestraLista() {
		// String Buffer;
		// for(Publicacion p: listaPublicaciones) {
		// System.out.println(p.toString());
		// }
	}

	public void addPublicacion(Publicacion publi) {
		if (publi != null) {
			listaPublicaciones.add(publi);
		}
	}

	public void addAlbum(Album nuevoAlbum) {
		if (nuevoAlbum != null)
			listaAlbumes.add(nuevoAlbum);
	}

	public boolean buscaAlbum(String nombre) throws AlbumNoEncontradoException {
		// opcion 1: ciclar la lista hasta encontrar un nombre que coincida
		int i = 0;
		while (listaAlbumes != null && ((Album) listaAlbumes).getNombreAlbum() != nombre) {
			i++;
		}
		if (listaAlbumes != null && ((Album) listaAlbumes).getNombreAlbum() == nombre)
			return true;
		else
			throw new AlbumNoEncontradoException("El álbum no se encuentra en la lista.");

		// opcion 2: usar indexOf con un objeto album que se cree en PerfilUsuario.java
		// int posicion = listaAlbumes.indexOf(album);
		// if (posicion < 0) {
		// throw new AlbumNoEncontradoException("El álbum no se encuentra en la
		// lista.");
		// }
		// return posicion;
	}
	
	public void eliminaAlbumDeListaAlbumes(String nombreAlbum) {
		// elimina album de la lista de albumes
	}
	
	public void eliminaAlbumDeListaPublicaciones(String nombreAlbum) {
		// se mete en la lista de publicaciones --> recorre todas las publicaciones --> elimina el album de la lista de albumes a los que pertenece
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
