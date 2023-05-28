package sistema;

import java.awt.EventQueue;
import vista.PerfilUsuario;

/**
 * La Clase Sistema.
 */
public class Sistema {

	/**
	 * El metodo main.
	 * Crea una instancia de la clase PerfilUsuario (extiende de JFrame).
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				PerfilUsuario interfaz = new PerfilUsuario();
				interfaz.setVisible(true);
			}
		});

	}
}
