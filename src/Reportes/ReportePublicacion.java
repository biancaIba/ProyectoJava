package Reportes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class ReportePublicacion implements Serializable{
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
