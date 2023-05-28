/*
 * 
 */
package utilidades;

import java.awt.Image;

import javax.swing.ImageIcon;

import vista.reportes.ReportePublicaciones;
/**
 * Clase  IconosUtilidades.
 */
public class IconosUtilidades {
	
	/**
	 * Obtener icono.
	 *
	 * @param nombreIcono : nombre del icono.
	 * @return  imagen del icono.
	 */
	public static ImageIcon obtenerIcono(String nombreIcono) {
    	String ruta = String.format("/recursos/iconos/%s.png", nombreIcono);
    	try {    		
    		ImageIcon icono = new ImageIcon(ReportePublicaciones.class.getResource(ruta));
    		// Obtener la imagen del ImageIcon
    		Image imagenOriginal = icono.getImage();
    		Image imagenRedimensionada = imagenOriginal.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
    		// Crear un nuevo ImageIcon con la imagen redimensionada
    		ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);
    		return iconoRedimensionado;
    	} catch(Exception e) {
    	    String mensaje = String.format("No se ha podido encontrar el icono %s en la ruta %s", nombreIcono, ruta);
    		AlertaUtilidades.mostrarAdvertencia(mensaje);
    		return null;
    	}
    }
	
	/**
	 * Obteniene un icono.
	 *
	 * @param nombreIcono : nombre del icono.
	 * @param alto : alto del icono.
	 * @param ancho : del icono.
	 * @return imagen del icono.
	 */
	public static ImageIcon obtenerIcono(String nombreIcono, int alto, int ancho) {
    	String ruta = String.format("/recursos/iconos/%s.png", nombreIcono);
    	try {    		
    		ImageIcon icono = new ImageIcon(ReportePublicaciones.class.getResource(ruta));
    		// Obtener la imagen del ImageIcon
    		Image imagenOriginal = icono.getImage();
    		Image imagenRedimensionada = imagenOriginal.getScaledInstance(alto, ancho, Image.SCALE_SMOOTH);
    		// Crear un nuevo ImageIcon con la imagen redimensionada
    		ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);
    		return iconoRedimensionado;
    	} catch(Exception e) {
    	    String mensaje = String.format("No se ha podido encontrar el icono %s en la ruta %s", nombreIcono, ruta);
    		AlertaUtilidades.mostrarAdvertencia(mensaje);
    		return null;
    	}
    }
}
