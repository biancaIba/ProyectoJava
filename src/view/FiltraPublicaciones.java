package view;

import java.util.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import model.PerfilInstagram;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Dimension;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JTable;

public class FiltraPublicaciones extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static PerfilInstagram perfil;

	public FiltraPublicaciones() {
		//int cantP;
		perfil = PerfilInstagram.getInstance();
		setTitle("Filtrar Publicaciones");
		setAlwaysOnTop(true);
		setBackground(Color.LIGHT_GRAY);
		getContentPane().setBackground(Color.WHITE);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		/**{
			JLabel lblCantPubli = new JLabel("Cantidad de Publicaciones:");
			contentPanel.add(lblCantPubli);
		}
		{
			JSpinner spCantPubli = new JSpinner();
			contentPanel.add(spCantPubli);
			cantP = (int) spCantPubli.getValue();
		}*/
		{
			JLabel lblSeleccionaPubli = new JLabel("Selecciona las publicaciones: ");
			contentPanel.add(lblSeleccionaPubli);
		}
		{
			String nombres[] = perfil.getNombresPublicaciones();
			DefaultListModel<String> modelo = new DefaultListModel<>();
			for (String nombre : nombres) {
	            modelo.addElement(nombre);
	        }
			JList<String> listPubli = new JList<>(modelo);
			List<String> elementosSeleccionados = listPubli.getSelectedValuesList();
			for (String elemento : elementosSeleccionados) {
			    // hace algo con los elementos seleccionado
			}
			
			contentPanel.add(listPubli);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.DARK_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnConsulta = new JButton("Consultar");
				btnConsulta.setActionCommand("Consultar");
				buttonPane.add(btnConsulta);
				
			}
			{
				JButton btnReproducir = new JButton("Reproducir");
				btnReproducir.setActionCommand("Reproducir");
				buttonPane.add(btnReproducir);
				
				//JOptionPane.showInputDialog("La reproducción será de un total de ... minutos.");
			}
			{
				JButton btnSalir = new JButton("Salir");
				btnSalir.setActionCommand("Salir");
				buttonPane.add(btnSalir);
				getRootPane().setDefaultButton(btnSalir);
			}
		}
	}

}
