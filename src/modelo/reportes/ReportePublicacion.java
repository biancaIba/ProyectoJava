package modelo.reportes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;


/**
 * Clase ReportePublicacion
 * Implementa el serializado
 */
public class ReportePublicacion implements Serializable{
	
	/** El tipo de publicacion. */
	private String tipoPublicacion;
	
	/** La cantidad de publicaciones. */
	private int cantidadPublicaciones;
	
	/** El promedio de "me gusta". */
	private float promedio;
	
	/**
	 * Instancia un nuevo reportePublicacion.
	 *
	 * @param tipoPublicacion el tipo de publicacion
	 * @param cantidadPublicaciones la cantidad de publicaciones
	 * @param promedio el promedio de "me gusta"
	 */
	public ReportePublicacion(String tipoPublicacion, int cantidadPublicaciones, float promedio) {
		this.tipoPublicacion=tipoPublicacion;
		this.cantidadPublicaciones = cantidadPublicaciones;
		this.promedio = promedio;
	}
	
	/**
	 * Getter de tipo publicacion.
	 *
	 * @return el tipo publicacion
	 */
	public String getTipoPublicacion() {
		return tipoPublicacion;
	}

	/**
	 * Setter de tipo publicacion.
	 *
	 * @param tipoPublicacion el nuevo tipo publicacion
	 */
	public void setTipoPublicacion(String tipoPublicacion) {
		this.tipoPublicacion = tipoPublicacion;
	}

	/**
	 * Getter de la cantidad publicaciones.
	 *
	 * @return la cantidad de publicaciones
	 */
	public int getCantidadPublicaciones() {
		return cantidadPublicaciones;
	}
	
	/**
	 * Setter de cantidad publicaciones.
	 *
	 * @param cantidadPublicaciones la nueva cantidad de publicaciones
	 */
	public void setCantidadPublicaciones(int cantidadPublicaciones) {
		this.cantidadPublicaciones = cantidadPublicaciones;
	}

	/**
	 * Getter de promedio.
	 *
	 * @return el promedio
	 */
	public float getPromedio() {
		return promedio;
	}

	/**
	 * Setter de promedio.
	 *
	 * @param promedio el nuevo promedio
	 */
	public void setPromedio(float promedio) {
		this.promedio = promedio;
	}
	
	    /**
    	 * To string.
    	 *
    	 * @return muestra la informacion de los atributos.
    	 */
    	@Override
	public String toString() {
		return "ReportePublicacion [tipoPublicacion=" + tipoPublicacion + ", cantidadPublicaciones="
				+ cantidadPublicaciones + ", promedio=" + promedio + "]";
	}

    /**
     * Generar reporte publicacion en un archivo de texto.
     *
     * @param listaReportes la lista de reportes
     * @throws IOException señala que se ha producido una excepcion de E/S.
     */
    public static void generarReportePublicacionEnArchivo(List<ReportePublicacion> listaReportes) throws IOException {
        String nombreArchivo = "Reporte de publicaciones.txt";
        
        try (FileWriter fileWriter = new FileWriter(nombreArchivo);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            
            for (ReportePublicacion rep : listaReportes) {
                bufferedWriter.write("Tipo: " + rep.getTipoPublicacion());
                bufferedWriter.newLine();
                bufferedWriter.write("Cantidad de publicaciones: " + rep.getCantidadPublicaciones());
                bufferedWriter.newLine();
                bufferedWriter.write("Promedio de Mg: " + rep.getPromedio());
                bufferedWriter.newLine();
                bufferedWriter.newLine();
            }
            
        }
    }
	
}