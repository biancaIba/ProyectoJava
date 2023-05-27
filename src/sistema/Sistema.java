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
import java.time.LocalDate;
//import java.time.LocalDate;
import java.util.List;

import Reportes.*;
import model.PerfilInstagram;

public class Sistema {

	private static PerfilInstagram perfil;


	public static void main(String[] args) {
		
		perfil = PerfilInstagram.getInstance();

		File datos = new File("Perfil2.ser");
		if (datos.exists()) {
			recupera();
		} else {
			perfil.cargarPublicaciones();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				PerfilUsuario interfaz = new PerfilUsuario(perfil);
				interfaz.setVisible(true);
			}
		});

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
