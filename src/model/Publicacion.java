package model;

import java.util.ArrayList;

import excepciones.AlbumNoEncontradoExcepcion;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Publicacion implements Comparable<Publicacion>, Serializable {

	private static final long serialVersionUID = 1L;
	private String nombrePublicacion;
	private LocalDate fechaSubida;
	private int cantidadMG;
	private ArrayList<String> listaEtiquetas;
	private ArrayList<String> listaComentarios;
	private ArrayList<Album> listaAlbumesPertenece; // 0 o mas albumes
	private EnumTipoPublicacion tipoPublicacion;

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

	public String getNombrePublicacion() {
		return nombrePublicacion;
	}

	public void setNombrePublicacion(String nombrePublicacion) {
		this.nombrePublicacion = nombrePublicacion;
	}

	public LocalDate getFechaSubida() {
		return fechaSubida;
	}

	public void setFechaSubida(LocalDate fechaSubida) {
		this.fechaSubida = fechaSubida;
	}

	public int getCantidadMG() {
		return cantidadMG;
	}

	public void setCantidadMG(int cantMG) {
		this.cantidadMG = cantMG;
	}

	public abstract float getDuracion();

	public abstract void reproducir();

	@Override
	public String toString() {
		return "nombrePublicacion{='" + nombrePublicacion + '\'' + ", fechaSubida='" + fechaSubida + '\'' + ", cantMG="
				+ cantidadMG + ", etiquetas=" + listaEtiquetas + ", comentarios=" + listaComentarios + ", albumes="
				+ listaAlbumesPertenece + '}';
	}

	// trim elimina los espacios en blanco al principio y al final de cada palabra
	public void agregarEtiqueta(String etiqueta) {
		if (etiqueta != null && !etiqueta.trim().isEmpty()) {
			listaEtiquetas.add(etiqueta.trim());
		}
	}

	public void agregarComentario(String comentario) {
		if (comentario != null && !comentario.trim().isEmpty()) {
			listaComentarios.add(comentario.trim());
		}
	}

	public void agregarAlbum(Album album) {
		if (album != null) {
			listaAlbumesPertenece.add(album);
		}
	}

	public ArrayList<String> getListaEtiquetas() {
		return listaEtiquetas;
	}

	public void setListaEtiquetas(ArrayList<String> listaEtiquetas) {
		this.listaEtiquetas = listaEtiquetas;
	}

	public ArrayList<String> getListaComentarios() {
		return listaComentarios;
	}

	public void setListaComentarios(ArrayList<String> listaComentarios) {
		this.listaComentarios = listaComentarios;
	}

	public int getCantidadDeComentarios() {
		return listaComentarios.size();
	}

	public ArrayList<Album> getListaAlbumesPertenece() {
		return listaAlbumesPertenece;
	}

	public void setListaAlbumesPertenece(ArrayList<Album> listaAlbumesPertenece) {
		this.listaAlbumesPertenece = listaAlbumesPertenece;
	}

	@Override
	public int compareTo(Publicacion otraPublicacion) {
		return this.nombrePublicacion.compareToIgnoreCase(otraPublicacion.nombrePublicacion);
	}

	public void sacarAlbum(Album albumAEliminar) throws AlbumNoEncontradoExcepcion {
		boolean borrado = this.listaAlbumesPertenece.remove(albumAEliminar);
		if (!borrado) {
			throw new AlbumNoEncontradoExcepcion("Album no encontrado");
		}
	}

	// verifica si la publicacion ya tiene ese album en la lista album pertenece
	// para que no quede repetido
	public boolean existeAlbumPertenece(Album album) {
		for (Album a : listaAlbumesPertenece) {
			if (a.equals(album)) {
				return true;
			}
		}
		return false;
	}

	// antes de agregar verifica que no sea null y que no este repetido
	// deberia tener una excepsion por si esta repedido ?
	public void agregaAlbumPertenece(Album album) {
		if (album != null && !existeAlbumPertenece(album)) {
			listaAlbumesPertenece.add(album);
		}
	}

	public EnumTipoPublicacion getTipoPublicacion() {
		return tipoPublicacion;
	}

}
