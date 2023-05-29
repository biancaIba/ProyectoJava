package modelo;

import java.io.Serializable;
import java.util.*;
import excepciones.AlbumExistenteExcepcion;
import excepciones.AlbumNoEncontradoExcepcion;
import excepciones.PublicacionNoEncontradaExcepcion;

/**
 * La Clase Album.
 * Implementa Serializable.
 */
public class Album implements Serializable {

	/** El statico serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** El nombre album. */
	private String nombreAlbum;

	/** La sublista albumes. */
	private ArrayList<Album> sublistaAlbumes;

	/** La lista publicaciones. */
	private ArrayList<Publicacion> listaPublicaciones;

	/** El album padre. */
	private Album albumPadre;

	/**
	 * Instancia un nuevo album.
	 *
	 * @param nombreAlbum el nombre del album
	 */
	public Album(String nombreAlbum) {
		this.nombreAlbum = nombreAlbum.toLowerCase();
		this.sublistaAlbumes = new ArrayList<Album>();
		this.listaPublicaciones = new ArrayList<Publicacion>();
	}

	/**
	 * Hash code.
	 *Devuelve el código hash del objeto.
	 * @return un entero que representa el código hash del objeto.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(nombreAlbum);
	}

	/**
	 * Equals.
	 *Compara si el objeto actual es igual a otro objeto dado.
	 * @param obj el objeto a comparar.
	 * @return true si los objetos son iguales, false en caso contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Album)) {
			return false;
		}
		Album album = (Album) obj;
		return nombreAlbum.equals(album.nombreAlbum);
	}

	/**
	 *getNombreAlbum.
	 * Obtiene el nombre del album.
	 *
	 * @return una cadena de texto que representa el nombre del album.
	 */
	public String getNombreAlbum() {
		return nombreAlbum;
	}

	/**
	 * getAlbumPadre
	 * Obtiene el album padre.
	 *
	 * @return el album padre o null si no tiene padre.
	 */
	public Album getAlbumPadre() {
		return albumPadre;
	}

	/**
	 * setAlbumPadre
	 * Carga el album padre.
	 *
	 * @param albumPadre el objeto album padre.
	 */
	public void setAlbumPadre(Album albumPadre) {
		this.albumPadre = albumPadre;
	}

	/**
	 * To string.
	 *
	 * @return una cadena de texto que representa el nombre del album.
	 */
	@Override
	public String toString() {
		return "nombreAlbum=" + nombreAlbum;
	}

	/**
	 * Setea el nombre del album.
	 *
	 * @param nombreAlbum el nombre del album
	 */
	public void setNombreAlbum(String nombreAlbum) {
		this.nombreAlbum = nombreAlbum;
	}

	/**
	 * Obtiene la sublista de albumes.
	 *
	 * @return la sublista albumes
	 */
	public ArrayList<Album> getSublistaAlbumes() {
		return sublistaAlbumes;
	}

	/**
	 * Setea la sublista albumes.
	 *
	 * @param sublistaAlbumes la sublista albumes
	 */
	public void setSublistaAlbumes(ArrayList<Album> sublistaAlbumes) {
		this.sublistaAlbumes = sublistaAlbumes;
	}

	/**
	 * Obtiene la lista de publicaciones.
	 *
	 * @return la lista publicaciones
	 */
	public ArrayList<Publicacion> getListaPublicaciones() {
		return listaPublicaciones;
	}

	/**
	 * Setea la lista de publicaciones.
	 *
	 * @param listaPublicaciones la lista publicaciones
	 */
	public void setListaPublicaciones(ArrayList<Publicacion> listaPublicaciones) {
		this.listaPublicaciones = listaPublicaciones;
	}

	/**
	 * Desasocia de manera bidireccional las referencias de las publicaciones de
	 * este album y tambien hace esta misma accion para todos los subalbumes
	 * asociados.
	 *
	 * @throws AlbumNoEncontradoExcepcion el album no encontrado excepcion
	 */
	public void desasociarReferenciasAPublicaciones() throws AlbumNoEncontradoExcepcion {
		for (Publicacion publicacion : listaPublicaciones) {
			publicacion.sacarAlbum(this);
		}
		for (Album subAlbum : sublistaAlbumes) {
			subAlbum.desasociarReferenciasAPublicaciones();
		}
		listaPublicaciones.clear();
	}

	/**
	 * Verifica si existe la publicacion dentro del album para que no quede repetida
	 *
	 * @param publicacion la publicacion
	 * @return verdadero, si existe
	 */
	public boolean existePublicacion(Publicacion publicacion) {
		for (Publicacion p : listaPublicaciones) {
			if (p.equals(publicacion)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Antes de agregar la publicacion se fija que sea distinta de null y que no
	 * este repetida
	 *
	 * @param publicacion la publicacion
	 */
	public void agregaPublicacionAalbum(Publicacion publicacion) {
		if (publicacion != null && !existePublicacion(publicacion))
			listaPublicaciones.add(publicacion);
	}

	/**
	 * Saca publicacion del album.
	 *
	 * @param publicacion la publicacion
	 * @throws PublicacionNoEncontradaExcepcion publicacion no encontrada excepcion
	 */
	public void sacarPublicacion(Publicacion publicacion) throws PublicacionNoEncontradaExcepcion {
		boolean borrado = listaPublicaciones.remove(publicacion);
		if (!borrado)
			throw new PublicacionNoEncontradaExcepcion("Publicacion no encontrada");
	}

	/**
	 * Agrega un sub album.
	 *
	 * @param nuevoSubAlbum el nuevo sub album
	 * @throws AlbumExistenteExcepcion album existente excepcion
	 */
	public void agregarSubAlbum(Album nuevoSubAlbum) throws AlbumExistenteExcepcion {
		if (sublistaAlbumes.contains(nuevoSubAlbum)) {
			throw new AlbumExistenteExcepcion("El Subalbum ya existe");
		} else {
			sublistaAlbumes.add(nuevoSubAlbum);
			nuevoSubAlbum.setAlbumPadre(this);
		}
	}

	/**
	 * Desasocia/elimina sub album del album.
	 *
	 * @param subAlbumAEliminar el sub album a eliminar
	 * @throws AlbumNoEncontradoExcepcion sub album no encontrado excepcion
	 */
	public void desasociarSubAlbum(Album subAlbumAEliminar) throws AlbumNoEncontradoExcepcion {
		int subAlbumAEliminarIndice = sublistaAlbumes.indexOf(subAlbumAEliminar);
		if (subAlbumAEliminarIndice == -1) {
			throw new AlbumNoEncontradoExcepcion("SubAlbum no encontrado");
		} else {
			sublistaAlbumes.remove(subAlbumAEliminar);
		}
	}
}
