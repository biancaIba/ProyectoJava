package sistema;

import java.awt.EventQueue;

import vista.PerfilUsuario;

public class Sistema {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				PerfilUsuario interfaz = new PerfilUsuario();
				interfaz.setVisible(true);
			}
		});

	}
}
