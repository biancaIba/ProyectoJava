package reports;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ReportePublicacion {
	private String tipoPublicacion;
	private int cantidadPublicaciones;
	private float promedio;
	
	public ReportePublicacion(String tipoPublicacion, int cantidadPublicaciones, float promedio) {
		this.tipoPublicacion=tipoPublicacion;
		this.cantidadPublicaciones = cantidadPublicaciones;
		this.promedio = promedio;
	}
	
	public String getTipoPublicacion() {
		return tipoPublicacion;
	}

	public void setTipoPublicacion(String tipoPublicacion) {
		this.tipoPublicacion = tipoPublicacion;
	}

	public int getCantidadPublicaciones() {
		return cantidadPublicaciones;
	}
	public void setCantidadPublicaciones(int cantidadPublicaciones) {
		this.cantidadPublicaciones = cantidadPublicaciones;
	}

	public float getPromedio() {
		return promedio;
	}

	public void setPromedio(float promedio) {
		this.promedio = promedio;
	}
	
	    @Override
	public String toString() {
		return "ReportePublicacion [tipoPublicacion=" + tipoPublicacion + ", cantidadPublicaciones="
				+ cantidadPublicaciones + ", promedio=" + promedio + "]";
	}

		public void generarReporte(List<ReportePublicacion> elementos) {
	        String nombreArchivo = "reporte.txt";

	        try (FileWriter fileWriter = new FileWriter(nombreArchivo);
	             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

	            for (ReportePublicacion elemento : elementos) {
	                bufferedWriter.write(elemento.toString());
	                bufferedWriter.newLine();
	            }

	        } catch (IOException e) {
	            System.err.println("Error al escribir en el archivo: " + e.getMessage());
	        }
	    }
	
}
