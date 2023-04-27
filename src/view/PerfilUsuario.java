package view;

import model.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;

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
		setTitle("Perfil del Usuario");
		setSize(420,250);
		setForeground(Color.DARK_GRAY);
		setBackground(Color.GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 401);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		
		inicializar();
	}
	
	private void inicializar() {
		
		/**
		 * Setea el Menu Principal del Perfil
		 */
		
		JMenuBar menuPrincipal = new JMenuBar();
		
		menuPrincipal.setOpaque(false);
		menuPrincipal.setBackground(Color.LIGHT_GRAY);
		menuPrincipal.setFont(new Font("Open Sans", Font.PLAIN, 20));
		menuPrincipal.setBorderPainted(true);
		
		JMenu albumes = new JMenu("Álbumes");
		albumes.setFont(new Font("Open Sans", Font.PLAIN, 15));
		JMenu reportes = new JMenu("Reportes");
		reportes.setFont(new Font("Open Sans", Font.PLAIN, 15));
		JMenu opciones = new JMenu("Opciones");
		opciones.setFont(new Font("Open Sans", Font.PLAIN, 15));
		
		menuPrincipal.add(albumes);
		menuPrincipal.add(reportes);
		menuPrincipal.add(opciones);
		
		JMenuItem crearAlbum = new JMenuItem ("Crear álbum");
		crearAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreAlbum=JOptionPane.showInputDialog("Ingrese el nombre del nuevo Album");
				model.Album nuevoAlbum = new Album(nombreAlbum);
				// aca deberia agregarlo al TreeSet de Albumes que hay en PerfilInstagram
			}
		});
		JMenuItem gestionaAlbum = new JMenuItem ("Gestionar álbumes");
		gestionaAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionaAlbumes plGestionaAlbumes = new GestionaAlbumes();
				plGestionaAlbumes.setVisible(true);
			}
		});
		JMenuItem eliminaAlbum = new JMenuItem ("Eliminar álbum");
		eliminaAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreAlbum=JOptionPane.showInputDialog("Ingrese el nombre del Album a eliminar");
				// aca debe chequear si el album existe y luego invocar a elimina
			}
		});
		
		// La idea es reemplazar el JFrame de GestionaAlbumes por un menu de opciones dentro del JItem, pero NO funciona aun
		
		//JMenuItem gaAgregaPubli = new JMenuItem("Agregar Publicación a un Album");
		//gaAgregaPubli.addActionListener(new ActionListener() {
		//	public void actionPerformed(ActionEvent e) {	
		//	}
		//});
		
		//gestionaAlbum.add(gaAgregaPubli);
		
		albumes.add(crearAlbum);
		albumes.add(gestionaAlbum);
		albumes.add(eliminaAlbum);
		
		
		/**
		 * Setea el espacio donde apareceran las Publicaciones del Usuario
		 */
		
		JPanel jpPublicaciones = new JPanel();
		jpPublicaciones.setBackground(Color.LIGHT_GRAY);
		jpPublicaciones.setFont(new Font("Open Sans", Font.PLAIN, 20));
		
		// prueba de crear un Label
		// La idea es que se genere un label por cada elemento del TreeSet de Publicaciones que haya en PerfilInstagram
		JLabel p1 = new JLabel();
		p1.setText("SOY LA PUBLICACION 1");
		jpPublicaciones.add(p1);
		jpPublicaciones.setLayout(new GridLayout(1, 0, 0, 0));
		
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(menuPrincipal, BorderLayout.NORTH);
		contentPane.add(jpPublicaciones, BorderLayout.CENTER);
		
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
