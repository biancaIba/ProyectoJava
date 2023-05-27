package utilidades;

import java.awt.Image;

import javax.swing.ImageIcon;

import vista.reportes.ReportePublicaciones;

public class IconosUtilidades {
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
