package modelo.reportes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import modelo.Album;

public class ReporteAlbum implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nombreAlbum;
	private int cantidadPublicaciones;
	private int cantidadComentarios;
	private List<Album> listaSubAlbumes;

	public ReporteAlbum(String nombreAlbum, int cantidadPublicaciones, int cantidadComentarios,	List<Album> listaSubAlbumes) {
		super();
		this.nombreAlbum = nombreAlbum;
		this.cantidadPublicaciones = cantidadPublicaciones;
		this.cantidadComentarios = cantidadComentarios;
		this.listaSubAlbumes = listaSubAlbumes;
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
	
	public static void generarReporteAlbumesEnArchivo(List<ReporteAlbum> listaReportesAlbumes) throws IOException  {
	    String nombreArchivo = "Reporte de albumes.txt";
	    FileWriter fileWriter = new FileWriter(nombreArchivo);
	    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

	    for (ReporteAlbum rep : listaReportesAlbumes) {
	        bufferedWriter.write("Nombre: " + rep.getNombreAlbum());
	        bufferedWriter.newLine();
	        bufferedWriter.write("Cantidad de publicaciones en rango de fechas: " + rep.getCantidadPublicaciones());
	        bufferedWriter.newLine();
	        bufferedWriter.write("Cantidad de comentarios: " + rep.getCantidadComentarios());
	        bufferedWriter.newLine();
	        bufferedWriter.newLine();
	    }

	    bufferedWriter.close();
	}
}