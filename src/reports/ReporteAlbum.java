package reports;

import java.io.Serializable;
import java.util.List;

import model.Album;
public class ReporteAlbum implements Serializable{
    private String nombreAlbum;
    private int cantidadPublicaciones;
    private int cantidadComentarios;
    private List<Album> listaSubAlbumes;

	public ReporteAlbum(String nombreAlbum, int cantidadPublicaciones, int cantidadComentarios,List<Album> listaSubAlbumes) {
		super();
		this.nombreAlbum = nombreAlbum;
		this.cantidadPublicaciones = cantidadPublicaciones;
		this.cantidadComentarios = cantidadComentarios;
		this.listaSubAlbumes=listaSubAlbumes;
	}

	public String getNombreAlbum() {
		return nombreAlbum;
	}
	public void setNombreAlbum(String nombreAlbum) {
		this.nombreAlbum = nombreAlbum;
	}
	public int getCantidadPublicaciones() {
		return cantidadPublicaciones;
	}
	public void setCantidadPublicaciones(int cantidadPublicaciones) {
		this.cantidadPublicaciones = cantidadPublicaciones;
	}
	public int getCantidadComentarios() {
		return cantidadComentarios;
	}
	public void setCantidadComentarios(int cantidadComentarios) {
		this.cantidadComentarios = cantidadComentarios;
	}

	public List<Album> getListaSubAlbumes() {
		return listaSubAlbumes;
	}

	public void setListaSubAlbumes(List<Album> listaSubAlbumes) {
		this.listaSubAlbumes = listaSubAlbumes;
	}
	

}