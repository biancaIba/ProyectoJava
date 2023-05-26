package sistema;

import view.*;
import java.awt.EventQueue;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import java.time.LocalDate;
import java.util.List;
import model.PerfilInstagram;
import reports.ReportePublicacion;
import reports.*;

public class Sistema {

	private static PerfilInstagram perfil;

	public static void main(String[] args) {
		
		perfil = PerfilInstagram.getInstance();

		File datos = new File("Perfil.ser");
		if (datos.exists()) {
			recupera();
		} else {
			perfil.cargarPublicaciones();
		}

		/*List<ReportePublicacion> listaReportes = perfil.cantidadYpromedioDeMg();
		generarReportePublicacionEnArchivo(listaReportes);

		LocalDate inicio = LocalDate.parse("2023-05-01");
		LocalDate fin = LocalDate.parse("2023-05-02");

		List<ReporteAlbum> listaReportesAlbumes = perfil.listadoDeAlbumes(inicio, fin);
		System.out.println("Cantidad de albumes " + perfil.getListaAlbumes().size());

		for (ReporteAlbum reportes : listaReportesAlbumes) {
			System.out.println("Album: " + reportes.getNombreAlbum() + " Cantidad de publicaciones: "
					+ reportes.getCantidadPublicaciones() + " Cantidad de comentarios: "
					+ reportes.getCantidadComentarios());
		}*/

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				PerfilUsuario interfaz = new PerfilUsuario(perfil);
				interfaz.setVisible(true);
			}
		});

	}

	public static void generarReportePublicacionEnPantalla(List<ReportePublicacion> listaReportes) {
		for (ReportePublicacion rep : listaReportes) {
			System.out.println("Tipo: " + rep.getTipoPublicacion());
			System.out.println("Cantidad de publicaciones: " + rep.getCantidadPublicaciones());
			System.out.println("Promedio de Mg: " + rep.getPromedio());
		}
	}

	public static void generarReportePublicacionEnArchivo(List<ReportePublicacion> listaReportes) {
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

	public static void generarReporteAlbumesEnArchivo(List<ReporteAlbum> listaReportesAlbumes) {
		String nombreArchivo = "reporteAlbumes.txt";
		try (FileWriter fileWriter = new FileWriter(nombreArchivo);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
			for (ReporteAlbum rep : listaReportesAlbumes) {
				bufferedWriter.write("Nombre: " + rep.getNombreAlbum());
				bufferedWriter.newLine();
				bufferedWriter.write("Cantidad de publicaciones en rango de fechas: " + rep.getCantidadPublicaciones());
				bufferedWriter.newLine();
				bufferedWriter.write("Cantidad de comentarios: " + rep.getCantidadComentarios());
				bufferedWriter.newLine();
				bufferedWriter.newLine();
			}
		} catch (IOException e) {
			System.err.println("Error al escribir en el archivo: " + e.getMessage());
		}
	}

	public static void serializa() {

		File datos = new File("Perfil.ser");
        if (datos.exists()) {
            datos.delete(); 
        }
        
        ObjectOutputStream out;
        
		try {
			out = new ObjectOutputStream(new FileOutputStream("Perfil.ser"));
			out.writeObject(perfil);
		} catch (NotSerializableException e) {
			System.out.println("Un objeto no es serializable: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error de E/S: " + e.getMessage());
		}

	}

	public static void recupera() {

		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Perfil.ser"))) {
			perfil = (PerfilInstagram) in.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("Archivo no encontrado: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Clase PerfilInstagram no encontrada: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error de E/S: " + e.getMessage());
		}
	}

}
