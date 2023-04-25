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

	public String getNombreAlbum() {
		return nombreAlbum;
	}

	public void setNombreAlbum(String nombreAlbum) {
		this.nombreAlbum = nombreAlbum;
	}
	
	public void crearAlbum() {

	}
	
	public void eliminarAlbum() {
		/*
		 * Elimina un álbum y todos sus sub-álbumes
		 * Las publicaciones son sacadas, pero no eliminadas del Perfil
		 */
		
	}

}
