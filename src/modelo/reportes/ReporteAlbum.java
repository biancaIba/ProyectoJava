package modelo.reportes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import modelo.Album;


/**
 *  Clase ReporteAlbum.
 * Implementa el serializado.
 */

public class ReporteAlbum implements Serializable {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** El nombre del album. */
	private String nombreAlbum;
	
	/** La cantidad de publicaciones. */
	private int cantidadPublicaciones;
	
	/** La cantidad de comentarios. */
	private int cantidadComentarios;
	
	/** La lista de sub albumes. */
	private List<Album> listaSubAlbumes;

	/**
	 * Instancia un nuevo reporteAlbum.
	 *
	 * @param nombreAlbum el nombre del album
	 * @param cantidadPublicaciones la cantidad de publicaciones
	 * @param cantidadComentarios la cantidad de comentarios
	 * @param listaSubAlbumes la lista de sub albumes
	 */
	public ReporteAlbum(String nombreAlbum, int cantidadPublicaciones, int cantidadComentarios,	List<Album> listaSubAlbumes) {
		super();
		this.nombreAlbum = nombreAlbum;
		this.cantidadPublicaciones = cantidadPublicaciones;
		this.cantidadComentarios = cantidadComentarios;
		this.listaSubAlbumes = listaSubAlbumes;
	}

	/**
	 * Getter de nombre album.
	 *
	 * @return el nombre del album
	 */
	public String getNombreAlbum() {
		return nombreAlbum;
	}

	/**
	 * Setter el nombre del album.
	 *
	 * @param nombreAlbum el nuevo nombre del album
	 */
	public void setNombreAlbum(String nombreAlbum) {
		this.nombreAlbum = nombreAlbum;
	}

	/**
	 * Getter la cantidad de publicaciones.
	 *
	 * @return la cantidad de publicaciones
	 */
	public int getCantidadPublicaciones() {
		return cantidadPublicaciones;
	}

	/**
	 * Sets la cantidad de publicaciones.
	 *
	 * @param cantidadPublicaciones la nueva cantidad de publicaciones
	 */
	public void setCantidadPublicaciones(int cantidadPublicaciones) {
		this.cantidadPublicaciones = cantidadPublicaciones;
	}

	/**
	 * Getter la cantidad de comentarios.
	 *
	 * @return la cantidad de comentarios
	 */
	public int getCantidadComentarios() {
		return cantidadComentarios;
	}

	/**
	 * Sets la cantidad de comentarios.
	 *
	 * @param cantidadComentarios la nueva cantidad de comentarios
	 */
	public void setCantidadComentarios(int cantidadComentarios) {
		this.cantidadComentarios = cantidadComentarios;
	}

	/**
	 * Getter la lista de sub albumes.
	 *
	 * @return la lista de sub albumes
	 */
	public List<Album> getListaSubAlbumes() {
		return listaSubAlbumes;
	}

	/**
	 * Setter la lista de sub albumes.
	 *
	 * @param listaSubAlbumes la nueva lista de sub albumes
	 */
	public void setListaSubAlbumes(List<Album> listaSubAlbumes) {
		this.listaSubAlbumes = listaSubAlbumes;
	}
	
	/**
	 * Genera un reporte de albumes en un archivo de texto.
	 *
	 * @param listaReportesAlbumes la lista de reportes de albumes
	 * @throws IOException señala que se ha producido una excepcion de E/S
	 */
	public static void generarReporteAlbumesEnArchivo(List<ReporteAlbum> listaReportesAlbumes,LocalDate inicio,LocalDate fin) throws IOException  {
	    String nombreArchivo = "Reporte de albumes.txt";
	    FileWriter fileWriter = new FileWriter(nombreArchivo);
	    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	    bufferedWriter.write("Listado de albumes con sus publicaciones en el rango de fechas seleccionado: \n"
	    		+ "Fecha de inicio:"+inicio+" Fecha de fin: "+fin+"\n\n");
	   
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