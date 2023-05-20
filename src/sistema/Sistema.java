package sistema;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import model.PerfilInstagram;
import reports.ReportePublicacion;
import reports.*;
public class Sistema {
	
	private static PerfilInstagram perfil;

	public static void main(String[] args) {
		perfil = PerfilInstagram.getInstance();
		perfil.cargarPublicaciones();
		List<ReportePublicacion> listaReportes = perfil.cantidadYpromedioDeMg();
		generarReporteEnPantalla(listaReportes);
		generarReporteEnArchivo(listaReportes);
		LocalDate inicio=LocalDate.parse("2023-05-01");
        LocalDate fin=LocalDate.parse("2023-05-02");

        List<ReporteAlbum> listaReportesAlbumes=perfil.listadoDeAlbumes(inicio,fin);
        System.out.println("Cantidad de albumes "+perfil.getListaAlbumes().size());//0
       System.out.println(listaReportesAlbumes.size());//0

        for(ReporteAlbum reportes:listaReportesAlbumes) {
        	System.out.println("Album: "+reportes.getNombreAlbum()+" Cantidad de publicaciones: "+
        	reportes.getCantidadPublicaciones()+" Cantidad de comentarios: "+reportes.getCantidadComentarios());
        }

	}
	
	public static void generarReporteEnPantalla(List<ReportePublicacion> listaReportes) {
		for(ReportePublicacion rep: listaReportes) {
			System.out.println("Tipo: "+rep.getTipoPublicacion());
			System.out.println("Cantidad de publicaciones: "+rep.getCantidadPublicaciones());
			System.out.println("Promedio de Mg: "+rep.getPromedio());
		}
	}
	
	public static void generarReporteEnArchivo(List<ReportePublicacion> listaReportes) {
        String nombreArchivo = "reporte.txt";
        
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

        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
        
    }

}
