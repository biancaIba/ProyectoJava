package view;

import model.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Insets;

public class PerfilUsuario extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PerfilUsuario frame = new PerfilUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PerfilUsuario() {
		setForeground(Color.DARK_GRAY);
		setBackground(Color.GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 401);
		contentPane = new JPanel();
		contentPane.setToolTipText("Agregar ");
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		setContentPane(contentPane);
		
		JMenuBar menuPrincipal = new JMenuBar();
		menuPrincipal.setOpaque(false);
		menuPrincipal.setBackground(Color.LIGHT_GRAY);
		menuPrincipal.setFont(new Font("Open Sans", Font.PLAIN, 20));
		menuPrincipal.setBorderPainted(true);
		
		JMenu agregaPublicacion = new JMenu("Agregar Publicación");
		agregaPublicacion.setFont(new Font("Open Sans", Font.PLAIN, 15));
		JMenu reportes = new JMenu("Reportes");
		reportes.setFont(new Font("Open Sans", Font.PLAIN, 15));
		JMenu opciones = new JMenu("Opciones");
		opciones.setFont(new Font("Open Sans", Font.PLAIN, 15));
		
		menuPrincipal.add(agregaPublicacion);
		menuPrincipal.add(reportes);
		menuPrincipal.add(opciones);
		
		JMenuItem agregaVideo = new JMenuItem ("Agrega Video");
		agregaVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Video nuevoVideo = new Video();
			}
		});
		JMenuItem agregaAudio = new JMenuItem ("Agrega Audio");
		agregaAudio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Audio nuevoAudio = new Audio();
			}
		});
		JMenuItem agregaImagen = new JMenuItem ("Agrega Imagen");
		agregaImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Imagen nuevoImagen = new Imagen();
			}
		});
		
		agregaPublicacion.add(agregaVideo);
		agregaPublicacion.add(agregaAudio);
		agregaPublicacion.add(agregaImagen);
		
		contentPane.add(menuPrincipal);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
