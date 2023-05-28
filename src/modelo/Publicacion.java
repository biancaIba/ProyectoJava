package modelo;

import java.util.ArrayList;
import excepciones.AlbumNoEncontradoExcepcion;
import modelo.enums.EnumTipoPublicacion;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * La Clase Abstracta Publicacion.
 * Implementa Comparable<Publicacion>.
 * Implementa Serializable.
 */
public abstract class Publicacion implements Comparable<Publicacion>, Serializable {

	/** Estatico serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** El nombre de la publicacion. */
	private String nombrePublicacion;

	/** La fecha de subida. */
	private LocalDate fechaSubida;

	/** La cantidad Me Gustas. */
	private int cantidadMG;

	/** La lista de etiquetas. */
	private ArrayList<String> listaEtiquetas;

	/** La lista de comentarios. */
	private ArrayList<String> listaComentarios;

	/**
	 * La lista de albumes a los cuales pertenece.
	 * Puede ser 0 o mas.
	 */
	private ArrayList<Album> listaAlbumesPertenece;

	/** El tipo de publicacion. */
	private EnumTipoPublicacion tipoPublicacion;

	/**
	 * Constructor.
	 *
	 * @param nombrePublicacion el nombre de la publicacion
	 * @param fechaSubida       la fecha de subida
	 * @param cantMG            la cantidad de Me Gustas
	 * @param tipoPublicacion   el tipo de publicacion
	 */
	public Publicacion(String nombrePublicacion, LocalDate fechaSubida, int cantMG,
			EnumTipoPublicacion tipoPublicacion) {
		this.nombrePublicacion = nombrePublicacion;
		this.fechaSubida = fechaSubida;
		this.cantidadMG = cantMG;
		this.tipoPublicacion = tipoPublicacion;
		this.listaEtiquetas = new ArrayList<String>();
		this.listaComentarios = new ArrayList<String>();
		this.listaAlbumesPertenece = new ArrayList<Album>();
	}

	/**
	 * Obtiene el nombre de la publicacion.
	 *
	 * @return el nombre de la publicacion
	 */
	public String getNombrePublicacion() {
		return nombrePublicacion;
	}

	/**
	 * Setea el nombre de la publicacion.
	 *
	 * @param nombrePublicacion
	 */
	public void setNombrePublicacion(String nombrePublicacion) {
		this.nombrePublicacion = nombrePublicacion;
	}

	/**
	 * Obtiene la fecha de subida.
	 *
	 * @return la fecha de subida
	 */
	public LocalDate getFechaSubida() {
		return fechaSubida;
	}

	/**
	 * Setea la fecha de subida.
	 *
	 * @param fechaSubida
	 */
	public void setFechaSubida(LocalDate fechaSubida) {
		this.fechaSubida = fechaSubida;
	}

	/**
	 * Obtiene la cantidad Me Gustas.
	 *
	 * @return la cantidad Me Gustas
	 */
	public int getCantidadMG() {
		return cantidadMG;
	}

	/**
	 * Setea la cantidad de Me Gustas.
	 *
	 * @param cantMG
	 */
	public void setCantidadMG(int cantMG) {
		this.cantidadMG = cantMG;
	}

	/**
	 * Metodo abstracto que obtiene la duracion.
	 *
	 * @return la duracion
	 */
	public abstract float getDuracion();

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "nombrePublicacion{='" + nombrePublicacion + '\'' + ", fechaSubida='" + fechaSubida + '\'' + ", cantMG="
				+ cantidadMG + ", etiquetas=" + listaEtiquetas + ", comentarios=" + listaComentarios + ", albumes="
				+ listaAlbumesPertenece + '}';
	}

	/**
	 * Agrega etiquetas a la Publicacion.
	 * Se utiliza la propiedad trim (elimina los espacios en blanco al principio
	 * 												y al final de cada palabra).
	 *
	 * @param etiqueta
	 */
	public void agregarEtiqueta(String etiqueta) {
		if (etiqueta != null && !etiqueta.trim().isEmpty()) {
			listaEtiquetas.add(etiqueta.trim());
		}
	}

	/**
	 * Agrega un comentario a la Publicacion.
	 *
	 * @param comentario
	 */
	public void agregarComentario(String comentario) {
		if (comentario != null && !comentario.trim().isEmpty()) {
			listaComentarios.add(comentario.trim());
		}
	}

	/**
	 * Agrega un album a la lista de albumes a los cuales pertenece la Publicacion.
	 *
	 * @param album
	 */
	public void agregarAlbum(Album album) {
		if (album != null) {
			listaAlbumesPertenece.add(album);
		}
	}

	/**
	 * Obtiene la lista de etiquetas.
	 *
	 * @return la lista de etiquetas
	 */
	public ArrayList<String> getListaEtiquetas() {
		return listaEtiquetas;
	}

	/**
	 * Setea la lista de etiquetas.
	 *
	 * @param listaEtiquetas
	 */
	public void setListaEtiquetas(ArrayList<String> listaEtiquetas) {
		this.listaEtiquetas = listaEtiquetas;
	}

	/**
	 * Obtiene la lista de comentarios.
	 *
	 * @return la lista de comentarios
	 */
	public ArrayList<String> getListaComentarios() {
		return listaComentarios;
	}

	/**
	 * Setea la lista de comentarios.
	 *
	 * @param listaComentarios
	 */
	public void setListaComentarios(ArrayList<String> listaComentarios) {
		this.listaComentarios = listaComentarios;
	}

	/**
	 * Obtiene la cantidad de comentarios.
	 *
	 * @return la cantidad de comentarios
	 */
	public int getCantidadDeComentarios() {
		return listaComentarios.size();
	}

	/**
	 * Obtiene la lista de albumes a los cuales pertenece.
	 *
	 * @return la lista de albumes pertenece
	 */
	public ArrayList<Album> getListaAlbumesPertenece() {
		return listaAlbumesPertenece;
	}

	/**
	 * Setea la lista de albumes a los cuales pertenece.
	 *
	 * @param listaAlbumesPertenece
	 */
	public void setListaAlbumesPertenece(ArrayList<Album> listaAlbumesPertenece) {
		this.listaAlbumesPertenece = listaAlbumesPertenece;
	}

	/**
	 * Compare to.
	 *
	 * @param otraPublicacion
	 * @return el int
	 */
	@Override
	public int compareTo(Publicacion otraPublicacion) {
		return this.nombrePublicacion.compareToIgnoreCase(otraPublicacion.nombrePublicacion);
	}

	/**
	 * Elimina un album de la lista de Albumes a los cuales pertenece la Publicacion.
	 *
	 * @param albumAEliminar
	 * @throws AlbumNoEncontradoExcepcion
	 */
	public void sacarAlbum(Album albumAEliminar) throws AlbumNoEncontradoExcepcion {
		boolean borrado = this.listaAlbumesPertenece.remove(albumAEliminar);
		if (!borrado) {
			throw new AlbumNoEncontradoExcepcion("Album no encontrado");
		}
	}

	/**
	 * Verifica si la publicacion ya tiene ese album en la lista album pertenece
	 * para que no quede repetido
	 *
	 * @param album
	 * @return verdadero, si existe
	 */
	public boolean existeAlbumPertenece(Album album) {
		for (Album a : listaAlbumesPertenece) {
			if (a.equals(album)) {
				return true;
			}
		}
		return false;
	}
 
	/**
	 * Agrega un album a la lista de albumes a los cuales pertenece.
	 * Antes de agregar verifica que no sea null y que no este repetido
	 *
	 * @param album the album
	 */
	public void agregaAlbumPertenece(Album album) {
		if (album != null && !existeAlbumPertenece(album)) {
			listaAlbumesPertenece.add(album);
		}
	}

	/**
	 * Obtiene el tipo de publicacion.
	 *
	 * @return el tipo publicacion
	 */
	public EnumTipoPublicacion getTipoPublicacion() {
		return tipoPublicacion;
	}

}
