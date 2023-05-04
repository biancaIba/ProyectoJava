package view;

import javax.swing.*;
import java.util.*;
import model.PerfilInstagram;
import exception.SinDatosException;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JList;

public class FiltraPublicaciones extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static PerfilInstagram perfil;

	public FiltraPublicaciones() {
		
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
		
		{
			JLabel lblSeleccionaPubli = new JLabel("Selecciona las publicaciones: ");
			contentPanel.add(lblSeleccionaPubli);
		}
		{
			DefaultListModel<String> modelo = new DefaultListModel<>();
			try {
				Set<String> nombresP = perfil.getNombresPublicaciones();
				for (String nombre : nombresP) {
		            modelo.addElement(nombre);
		        }
				JList<String> listaP = new JList<>(modelo);
				listaP.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				contentPanel.add(listaP);
				contentPanel.add(new JScrollPane(listaP));
			} catch (SinDatosException e) {
				JOptionPane.showMessageDialog(null, "No hay datos para filtrar.");
				dispose();
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.DARK_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnReproducir = new JButton("Reproducir");
				btnReproducir.setActionCommand("Reproducir");
				buttonPane.add(btnReproducir);
			}
			{
				JButton btnSalir = new JButton("Salir");
				btnSalir.setActionCommand("Salir");
				buttonPane.add(btnSalir);
				getRootPane().setDefaultButton(btnSalir);
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
			}
		}
	}

}
