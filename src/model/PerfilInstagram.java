package model;

import parser.CargaXML;
import java.util.*;
import exception.*;

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

	public void cargarPublicaciones() {
		CargaXML cargador = new CargaXML();
		cargador.cargarPublicacionesXML(this);// es como si le pasara perfil o sea la instancia donde se ejecuta el
												// cargarPublicaciones()
	}
	
	public Set<Publicacion> getPublicaciones() {
		return listaPublicaciones;
	}
	
	public String[] getNombresPublicaciones() {
		// este metodo deberia devolver un array de strings
		// con los nombres de todas las publicaciones que hay en la lista
		// se invoca en FiltraPublicaciones.java para que el usuario seleccione
		// que publicaciones quiere consultar/reproducir
		String nombres[] = new String[2];
		nombres[0] = "ESTO ES UNA PRUEBA 1";
		nombres[1] = "ESTO ES UNA PRUEBA 2";
		 return nombres;
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

	public Album buscaAlbum(String nombre) throws AlbumNoEncontradoException {
		int i = 0;
		while (i < listaAlbumes.size()) {
			Album album = listaAlbumes.get(i);
			if (album.getNombreAlbum().equalsIgnoreCase(nombre))
				return album;
			i++;
		}
		throw new AlbumNoEncontradoException("El álbum no se encuentra en la lista.");
	}
	
	public Publicacion buscaPubli(String nombre) throws PublicacionNoEncontradaException{
		Iterator <Publicacion> i = listaPublicaciones.iterator();
		while (i.hasNext()) {
			Publicacion publi = i.next();
			if (publi.getNombrePublicacion().equals(nombre))
				return publi;
		}
		throw new PublicacionNoEncontradaException("La publicación no se encuentra en la lista.");
	}
	
	public void addPubliDentroAlbum(String nombreAlbum, String nombrePubli) {
		
	}
	
	public void eliminaAlbum(Album albumAEliminar) throws AlbumNoEncontradoException {
		// elimina album de la lista de albumes
		int albumAEliminarIndice = listaAlbumes.indexOf(albumAEliminar);
		if (albumAEliminarIndice == -1) {
			throw new AlbumNoEncontradoException("Album no encontrado");
		} else {
			Album album = listaAlbumes.get(0);
			album.desasociarReferenciasAPublicaciones();
			listaAlbumes.remove(albumAEliminar);
			// en perfil instagram tenemos una referencia a las subAlbumes??
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
		// return "PerfilInstagram [listaPublicaciones=" + listaPublicaciones + ", listaAlbumes=" + listaAlbumes + "]";
		StringBuilder sb = new StringBuilder();
        sb.append("Lista de publicaciones:\n");
        for (Publicacion publicacion : listaPublicaciones) {
            sb.append("\t- " + publicacion.toString() + "\n");
        }
        sb.append("Lista de álbumes:\n");
        for (Album album : listaAlbumes) {
            sb.append("\t- " + album.toString() + "\n");
        }
        return sb.toString();
	}
}
