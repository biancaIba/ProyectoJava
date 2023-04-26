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
		contentPane.setToolTipText("Agregar ");
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		
		inicializar();
	}
	
	private void inicializar() {
		
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
				
			}
		});
		
		albumes.add(crearAlbum);
		albumes.add(gestionaAlbum);
		albumes.add(eliminaAlbum);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
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
