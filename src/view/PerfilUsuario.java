package view;

import model.*;
import reports.ReportePublicacion;
import sistema.Sistema;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import exception.*;
import java.util.*;
import java.util.List;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import java.io.File;

public class PerfilUsuario extends JFrame {
	
	private JPanel contentPane;
	private static PerfilInstagram perfilInstagram;

	public static void main(String[] args) {
		perfilInstagram = PerfilInstagram.getInstance();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				PerfilUsuario frame = new PerfilUsuario();
				frame.setVisible(true);
			}
		});
	}

	public PerfilUsuario() {
		setTitle("Perfil del Usuario");
		setSize(787,401);
		setForeground(Color.DARK_GRAY);
		setBackground(Color.GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 401);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		
		menuTop();
		publicacionesActuales();
	}
	
public void menuTop() {
		
		/**
		 * Setea el Menu Principal de la Interfaz
		 */
		
		JMenuBar menuPrincipal = new JMenuBar();
		
		menuPrincipal.setOpaque(false);
		menuPrincipal.setBackground(Color.LIGHT_GRAY);
		menuPrincipal.setFont(new Font("Open Sans", Font.PLAIN, 20));
		menuPrincipal.setBorderPainted(true);
		
		menuPrincipal.add(menuTOPalbumes());
		menuPrincipal.add(menuTOPreportes());
		menuPrincipal.add(menuTOPopciones());
		menuPrincipal.add(menuTOPestadisticas());

		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(menuPrincipal, BorderLayout.NORTH);
       
	}
	
	public JMenu menuTOPalbumes() {
		JMenu albumes = new JMenu("Álbumes");
		albumes.setFont(new Font("Open Sans", Font.PLAIN, 15));
		
		JMenuItem crearAlbum = new JMenuItem ("Crear álbum");
		
		crearAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreAlbum = JOptionPane.showInputDialog("Ingrese el nombre del nuevo Album");
				Album nuevoAlbum = new Album(nombreAlbum);
				PerfilInstagram.getInstance().addAlbum(nuevoAlbum);				
				JOptionPane.showMessageDialog(null, "El álbum fue agregado con éxito");
			}
		});
		
		/**
		 * Configura Gestionar Albumes
		 */
		JMenuItem gestionaAlbum = new JMenu ("Gestionar álbumes");
		
		JMenuItem gaAgregaPubli = new JMenuItem("Agregar Publicación a un Album");
		gaAgregaPubli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreAlbum = JOptionPane.showInputDialog("Ingrese el nombre del Album");
				String nombrePubli = JOptionPane.showInputDialog("Ingrese el nombre de la Publicación");
				try {
					PerfilInstagram.getInstance().buscaAlbum(nombreAlbum);
					PerfilInstagram.getInstance().buscaPubli(nombrePubli);
					PerfilInstagram.getInstance().addPubliDentroAlbum(nombreAlbum, nombrePubli);
					JOptionPane.showMessageDialog(null, "La publicación fue agregada con éxito");
				} catch (AlbumNoEncontradoException e1) {
					JOptionPane.showMessageDialog(null, "El álbum NO existe. Intente de nuevo.");
				}
				catch (PublicacionNoEncontradaException e1) {
					JOptionPane.showMessageDialog(null, "La publicación NO existe. Intente de nuevo.");
				}
			}
		});
		gestionaAlbum.add(gaAgregaPubli);
		
		JMenuItem gaEliminaPubli = new JMenuItem("Eliminar Publicación de un Album");
		gaEliminaPubli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		gestionaAlbum.add(gaEliminaPubli);
		
		JMenuItem eliminaAlbum = new JMenuItem ("Eliminar álbum");
		eliminaAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreAlbum = JOptionPane.showInputDialog("Ingrese el nombre del Album a eliminar");
				try {
					Album albumAEliminar = perfilInstagram.buscaAlbum(nombreAlbum);
					perfilInstagram.eliminaAlbum(albumAEliminar);
					JOptionPane.showMessageDialog(null, "El álbum fue eliminado con éxito");
				} catch (AlbumNoEncontradoException e1) {
					JOptionPane.showMessageDialog(null, "El álbum NO existe. Intente de nuevo.");
				}
			}
		});
		
		albumes.add(crearAlbum);
		albumes.add(gestionaAlbum);
		albumes.add(eliminaAlbum);
		
		return albumes;
	}
	
	public JMenu menuTOPreportes() {
		JMenu reportes = new JMenu("Reportes");
		reportes.setFont(new Font("Open Sans", Font.PLAIN, 15));
		
		JMenuItem ReportePublicaciones = new JMenuItem("Reporte de publicaciones");
		ReportePublicaciones.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFrame frame = new JFrame("Reporte de publicaciones");
		        frame.setSize(400, 300);
		     // Nombres de columnas
		        String[] columnNames = { "Nombre", "Tipo de publicacion", "Cantidad de MG","Fecha de Subida","Albumes asociados" };

		        // Crear un DefaultTableModel con los datos y nombres de columnas
		        DefaultTableModel model = new DefaultTableModel(null, columnNames);

		        // Crear un JTable con el DefaultTableModel
		        JTable table = new JTable(model);
		        table.setEnabled(false);
		        table.setRowSelectionAllowed(false);
				
				JScrollPane scrollPane = new JScrollPane(table);
				
				JButton btnNewButton = new JButton("Cargar datos ");
				
				JLabel lblNewLabel = new JLabel("Video");
				
				JLabel lblAudio = new JLabel("Audio");
				
				JLabel lblImagen = new JLabel("Imagen");
				
				JLabel lblNewLabel_1 = new JLabel("Promedio de MG:");
				
				JLabel lblNewLabel_1_1 = new JLabel("Cantidad de publicaciones:");
				lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
				
				JLabel lblPromMGVideo = new JLabel("");
				
				JLabel lblCantPubliVideo = new JLabel("");
				
				JLabel lblPromMGAudio = new JLabel("");
				
				JLabel lblPromMGImagen = new JLabel("");
				
				JLabel lblCantPubliAudio = new JLabel("");
				
				JLabel lblCantPubliImagen = new JLabel("");
				
				
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Map<String,List<Publicacion>> listaporMG = perfilInstagram.ordenarPublicacionesPorMg();
						model.setRowCount(0);
						for(Map.Entry<String, List<Publicacion>> entry: listaporMG.entrySet()) {
							List<Publicacion> publicaciones=entry.getValue();
							for(Publicacion publicacion  : publicaciones ) {
								String albumesStr = new String();
								for (Album album : publicacion.getListaAlbumesPertenece() ) {
									albumesStr+=album.getNombreAlbum()+", ";
								}
								model.addRow(new Object[] {publicacion.getNombrePublicacion(), publicacion.getTipoPublicacion(), publicacion.getCantMG(),publicacion.getFechaSubida(),albumesStr});
							}
						}
						List<ReportePublicacion> reportes = perfilInstagram.cantidadYpromedioDeMg();
						for (ReportePublicacion reporte : reportes) {
							
							switch (reporte.getTipoPublicacion().toLowerCase()) {
								case "video": 
									lblPromMGVideo.setText(Float.toString(reporte.getPromedio()));
									lblCantPubliVideo.setText(Integer.toString(reporte.getCantidadPublicaciones()));
									break;
								case "audio": 
									lblPromMGAudio.setText(Float.toString(reporte.getPromedio()));
									lblCantPubliAudio.setText(Integer.toString(reporte.getCantidadPublicaciones()));
									break;
								case "imagen": 
									lblPromMGImagen.setText(Float.toString(reporte.getPromedio()));
									lblCantPubliImagen.setText(Integer.toString(reporte.getCantidadPublicaciones()));
									break;
							}
									
								
						}
					}
				});
			
				GroupLayout gl_contentPane = new GroupLayout(contentPane);
				gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(ReportePublicaciones, GroupLayout.PREFERRED_SIZE, 607, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnNewButton)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 578, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(65)
											.addComponent(lblPromMGVideo)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lblPromMGAudio))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(73)
											.addComponent(lblNewLabel)
											.addGap(64)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblCantPubliAudio)
												.addComponent(lblAudio)))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_1_1)
									.addGap(18)
									.addComponent(lblCantPubliVideo)))
							.addGap(38)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCantPubliImagen)
								.addComponent(lblPromMGImagen)
								.addComponent(lblImagen)))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(ReportePublicaciones, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btnNewButton)
							.addGap(9)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblImagen)
								.addComponent(lblNewLabel)
								.addComponent(lblAudio))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPromMGVideo)
								.addComponent(lblPromMGAudio)
								.addComponent(lblPromMGImagen)
								.addComponent(lblNewLabel_1))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCantPubliVideo)
								.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCantPubliImagen)
								.addComponent(lblCantPubliAudio))
							.addGap(13))
				);
				contentPane.setLayout(gl_contentPane);
		        
		    }
		});
		
		JMenuItem ReporteDeAlbumes = new JMenuItem("Reporte de Albumes");
		ReporteDeAlbumes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
				
			}
		});
		reportes.add(ReporteDeAlbumes);
		reportes.add(ReportePublicaciones);	
		return reportes;
	}
	public JMenu menuTOPestadisticas(){
		JMenu estadisticas = new JMenu("Estadísticas");
		estadisticas.setFont(new Font("Open Sans", Font.PLAIN, 15));	
		
		JMenuItem mntmVerEstadisticas = new JMenuItem("Ver estadisticas");
		estadisticas.add(mntmVerEstadisticas);
		estadisticas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				contentPane = new JPanel();
				contentPane.setBackground(Color.blue);
				
				contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
				setContentPane(contentPane);
			}
		});
		return estadisticas;
	}
	public JMenu menuTOPopciones() {
		JMenu opciones = new JMenu("Opciones");
		opciones.setFont(new Font("Open Sans", Font.PLAIN, 15));
		
		JMenuItem cargaDatos = new JMenuItem ("Cargar datos desde XML");
		cargaDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PerfilInstagram.getInstance().cargarPublicaciones();				
				JOptionPane.showMessageDialog(null, "Los datos fueron agregados con éxito");
				publicacionesActuales();
			}
		});
		
		JMenuItem filtraPublicaciones = new JMenuItem("Filtrar publicaciones");
		filtraPublicaciones.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				FiltraPublicaciones ventanaFiltros = new FiltraPublicaciones();
				ventanaFiltros.setVisible(true);
			}
		});
		
		opciones.add(cargaDatos);
		opciones.add(filtraPublicaciones);
		
		return opciones;
	}
	
	

	
	public void publicacionesActuales() {
		/**
		 * Setea el espacio donde apareceran las Publicaciones del Perfil
		 */
		
		JPanel jpPublicaciones = new JPanel();
		jpPublicaciones.setBackground(Color.LIGHT_GRAY);
		jpPublicaciones.setFont(new Font("Open Sans", Font.PLAIN, 20));
		
		//PerfilInstagram.getInstance().getPublicaciones();
		//int i=0;
		//for (Publicacion p : publicaciones) {
		//	i++;
		//	JLabel publi = new JLabel();
		//	publi.setText("SOY LA PUBLICACION " + i);
		//	jpPublicaciones.add(publi);
		//}
		//jpPublicaciones.setLayout(new GridLayout(1, 0, 0, 0));
		//contentPane.add(jpPublicaciones, BorderLayout.CENTER);
	}
}
