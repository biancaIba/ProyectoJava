package model;

import java.util.*;

public class Album {
	private String nombreAlbum;
	private ArrayList<Album> sublistaAlbumes;
	private ArrayList<Publicacion> listaPublicaciones;
	public Album(String nombreAlbum) {
		this.nombreAlbum = nombreAlbum;
		// creo una lista vacia de subAlbumes cuando creo el obj Album
		this.sublistaAlbumes = new ArrayList<Album>();
		this.listaPublicaciones=new ArrayList<Publicacion>();
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombreAlbum);
	}

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

	public String getNombreAlbum() {
		return nombreAlbum;
	}

	@Override
	public String toString() {
		return "nombreAlbum=" + nombreAlbum;
	}

	public void setNombreAlbum(String nombreAlbum) {
		this.nombreAlbum = nombreAlbum;
	}
	
	/**
	 * Desasocia de manera bidireccional las referencias de las  publicaciones de este album
	 * y tambien hace esta misma accion para todos los subalbumes asociados.
	 */
	public void desasociarReferenciasAPublicaciones() {
		for (Publicacion publicacion : listaPublicaciones) {
			publicacion.eliminaAlbum(this);// elimina el album de publicacion--> listaAlbumesPertenece
		}
		for(Album subAlbum : sublistaAlbumes) {
			subAlbum.desasociarReferenciasAPublicaciones();
		}
		listaPublicaciones.clear();
	}
	//verifica si existe la publicacion dentro del album, para que no quede repetida
			public boolean tienePublicacion(Publicacion publicacion) {
			    for (Publicacion p : listaPublicaciones) {
			        if (p.equals(publicacion)) {
			            return true;
			        }
			    }
			    return false;
			}
			// antes de agregar la publicacion se fija que sea distinta de null y que no este repetida
			//deberia tener una excepsion por si esta repedido ?
			public void agregaPublicacionAalbum(Publicacion publicacion) {
				if (publicacion != null && tienePublicacion(publicacion))
				listaPublicaciones.add(publicacion);
			}
}
