package view;

import javax.swing.*;

public class GestionAlbumes extends JPanel {

	/**
	 * Create the panel.
	 */
	
	JLabel titulo;
	private JPanel plGestionaAlbumes;
	private JScrollPane scrollPaneles;
	
	public GestionAlbumes() {
		setLayout(null);
		
		scrollPaneles = new JScrollPane();
		scrollPaneles.setBounds(5,5,500,500);
		
	}

}
