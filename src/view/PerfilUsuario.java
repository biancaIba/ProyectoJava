package view;

import model.*;
import reproduccion.PublicacionReproduccion;
import sistema.*;
import utils.AssetsUtils;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import javax.swing.border.EmptyBorder;
import exception.*;
import java.util.*;
import java.util.List;
import view.GraficoTorta;

public class PerfilUsuario extends JFrame {

	private JPanel contentPane;
	private static PerfilInstagram perfilInstagram;
	private float duracionReproduccion;
	Map<String, PublicacionReproduccion> publicacionesSeleccionadas;

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
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setForeground(Color.DARK_GRAY);
		setBackground(Color.GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 401);

		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);

		menuTop();
		perfilInstagram.cargarPublicaciones();
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

		JMenuItem crearAlbum = new JMenuItem("Crear álbum");

		crearAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreAlbum = JOptionPane.showInputDialog(null, "Ingrese el nombre del nuevo Álbum",
						"Crear Álbum", JOptionPane.PLAIN_MESSAGE);
				if (nombreAlbum != null && !nombreAlbum.isEmpty()) {
					Album nuevoAlbum = new Album(nombreAlbum);
					PerfilInstagram.getInstance().addAlbum(nuevoAlbum);
					
					JOptionPane.showMessageDialog(null, "El álbum fue agregado con éxito");
					
				}
			}
		});

		/**
		 * Configura Gestionar Albumes
		 */

		JMenuItem gestionaAlbum = new JMenu("Gestionar álbumes");

		JMenuItem gaAgregaPubli = new JMenuItem("Agregar Publicación a un Álbum");
		gaAgregaPubli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				frame.setAlwaysOnTop(true);
				String nombreAlbum = JOptionPane.showInputDialog(null, "Ingrese el nombre del Álbum", "Ingresar Album",
						JOptionPane.PLAIN_MESSAGE);
				if (nombreAlbum != null && !nombreAlbum.isEmpty()) {
					try {
						Album album = perfilInstagram.buscaAlbum(nombreAlbum);
						String nombrePubli = JOptionPane.showInputDialog(null, "Ingrese el nombre de la Publicación",
								"Ingresar Publicacion", JOptionPane.PLAIN_MESSAGE);
						if (nombrePubli != null && !nombrePubli.isEmpty()) {
							try {
								Publicacion publicacion = perfilInstagram.buscaPubli(nombrePubli);
								perfilInstagram.addPubliDentroAlbum(album, publicacion);
								JOptionPane.showMessageDialog(null, "La publicación fue agregada con éxito");
							} catch (PublicacionNoEncontradaException e1) {
								JOptionPane.showMessageDialog(null, "La publicación NO existe. Intente de nuevo.",
										"Error", JOptionPane.ERROR_MESSAGE);
							}
						}
					} catch (AlbumNoEncontradoException e1) {
						JOptionPane.showMessageDialog(null, "El álbum NO existe. Intente de nuevo.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		gestionaAlbum.add(gaAgregaPubli);

		JMenuItem gaEliminaPubli = new JMenuItem("Eliminar Publicación");
		gaEliminaPubli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombrePublicacion = JOptionPane.showInputDialog(null, "Ingrese el nombre de la Publicación",
						"Ingresar Publicacion", JOptionPane.PLAIN_MESSAGE);
				if (nombrePublicacion != null && !nombrePublicacion.isEmpty()) {
					try {
						Publicacion publicacionAEliminar = perfilInstagram.buscaPubli(nombrePublicacion);
						perfilInstagram.eliminarPublicacion(publicacionAEliminar);
					} catch (PublicacionNoEncontradaException e1) {
						JOptionPane.showMessageDialog(null, "La publicación NO existe. Intente de nuevo.", "Error",
								JOptionPane.ERROR_MESSAGE);
					} catch (AlbumNoEncontradoException e1) {
						JOptionPane.showMessageDialog(null, "El Álbum NO existe. Intente de nuevo.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		gestionaAlbum.add(gaEliminaPubli);

		JMenuItem gaSacarPubli = new JMenuItem("Sacar publicación de un Álbum");
		gaSacarPubli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombrePublicacion = JOptionPane.showInputDialog(null, "Ingrese el nombre de la Publicación",
						"Ingresar Publicacion", JOptionPane.PLAIN_MESSAGE);
				if (nombrePublicacion != null && !nombrePublicacion.isEmpty()) {
					try {
						String nombreAlbum = JOptionPane.showInputDialog(null, "Ingrese el nombre del Álbum",
								"Ingresar Álbum", JOptionPane.PLAIN_MESSAGE);
						if (nombreAlbum != null && !nombreAlbum.isEmpty()) {
							try {
								Publicacion publicacionAEliminar = perfilInstagram.buscaPubli(nombrePublicacion);
								Album album = perfilInstagram.buscaAlbum(nombreAlbum);
								perfilInstagram.sacarPublicacionDelAlbum(publicacionAEliminar, album);
								JOptionPane.showMessageDialog(null, "Publicacion eliminada del álbum con éxito");
							} catch (AlbumNoEncontradoException e1) {
								JOptionPane.showMessageDialog(null, "El álbum NO existe. Intente de nuevo.", "Error",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					} catch (PublicacionNoEncontradaException e1) {
						JOptionPane.showMessageDialog(null, "La publicación NO existe. Intente de nuevo.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		gestionaAlbum.add(gaSacarPubli);

		JMenuItem gaAgregaSubAlbum = new JMenuItem("Agregar un Sub Álbum");
		gaAgregaSubAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreSubAlbum = JOptionPane.showInputDialog(null, "Ingrese el nombre del Sub Álbum",
						"Ingresar Sub Álbum", JOptionPane.PLAIN_MESSAGE);
				if (nombreSubAlbum != null && !nombreSubAlbum.isEmpty()) {
					String nombreAlbum = JOptionPane.showInputDialog(null, "Ingrese el nombre del Álbum Padre",
							"Ingresar Álbum Padre", JOptionPane.PLAIN_MESSAGE);
					if (nombreAlbum != null && !nombreAlbum.isEmpty()) {
						// Aca deberia llamar a una funcion que agrega un subAlbum a la lista de
						// subalbumes de ese album
						// debe tener una exception en caso de que el album ingresado no exista
					}
				}
			}
		});
		gestionaAlbum.add(gaAgregaSubAlbum);

		JMenuItem gaEliminaSubAlbum = new JMenuItem("Eliminar un Sub Álbum");
		gaAgregaSubAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreSubAlbum = JOptionPane.showInputDialog(null, "Ingrese el nombre del Sub Álbum",
						"Ingresar Sub Álbum", JOptionPane.PLAIN_MESSAGE);
				if (nombreSubAlbum != null && !nombreSubAlbum.isEmpty()) {
					String nombreAlbum = JOptionPane.showInputDialog(null, "Ingrese el nombre del Álbum Padre",
							"Ingresar Álbum Padre", JOptionPane.PLAIN_MESSAGE);
					if (nombreAlbum != null && !nombreAlbum.isEmpty()) {
						// Aca deberia llamar a una funcion que elimine un subAlbum a la lista de
						// subalbumes de ese album
						// debe tener una exception en caso de que el album ingresado no exista
					}
				}
			}
		});
		gestionaAlbum.add(gaEliminaSubAlbum);

		JMenuItem eliminaAlbum = new JMenuItem("Eliminar álbum");
		eliminaAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreAlbum = JOptionPane.showInputDialog(null, "Ingrese el nombre del Álbum a eliminar",
						"Ingresar Álbum", JOptionPane.PLAIN_MESSAGE);
				if (nombreAlbum != null && !nombreAlbum.isEmpty()) {
					try {
						Album albumAEliminar = perfilInstagram.buscaAlbum(nombreAlbum);
						perfilInstagram.eliminaAlbum(albumAEliminar);
						JOptionPane.showMessageDialog(null, "El álbum fue eliminado con éxito");
					} catch (AlbumNoEncontradoException e2) {
						JOptionPane.showMessageDialog(null, "El álbum NO existe. Intente de nuevo.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		albumes.add(crearAlbum);
		albumes.add(gestionaAlbum);
		albumes.add(eliminaAlbum);

		return albumes;
	}

	public JMenu menuTOPopciones() {
		JMenu opciones = new JMenu("Opciones");
		opciones.setFont(new Font("Open Sans", Font.PLAIN, 15));

		JMenuItem cargaDatos = new JMenuItem("Cargar datos desde XML");
		cargaDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				perfilInstagram.cargarPublicaciones();
				JOptionPane.showMessageDialog(null, "Los datos fueron agregados con éxito");
				publicacionesActuales();
			}
		});

		JMenuItem reproducir = new JMenuItem("Reproducir");
		reproducir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "No olvides aplicar filtros y seleccionar publicaciones para Reproducir.");
				JOptionPane.showMessageDialog(null, "El tiempo de reproducción total es de: " + duracionReproduccion + " segundos.");
				if (duracionReproduccion > 0) {
					String[] opciones = { "Imágenes primero", "Videos primero", "Audios primero"};
			        int eleccion = JOptionPane.showOptionDialog(null,
			        	"Elija un orden para la reproducción", "Orden de Reproducción",
			            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
			            opciones, opciones[0]
			        );
					Reproduccion ventanaReproduccion = new Reproduccion(eleccion, publicacionesSeleccionadas);
					ventanaReproduccion.setVisible(true);
				}
			}
		});

		opciones.add(cargaDatos);
		opciones.add(reproducir);
		return opciones;
	}

	public JMenu menuTOPreportes() {
		JMenu reportes = new JMenu("Reportes");
		reportes.setFont(new Font("Open Sans", Font.PLAIN, 15));

		JMenuItem generaTXT = new JMenuItem("Generar TXT Publicaciones");
		generaTXT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sistema.generarReportePublicacionEnArchivo(perfilInstagram.cantidadYpromedioDeMg());
				File reporte = new File("reporte.txt");
				if (reporte.exists())
					JOptionPane.showMessageDialog(null, "El archivo TXT fue generado con éxito");
				else
					JOptionPane.showMessageDialog(null, "El archivo NO fue generado", "Error",
							JOptionPane.ERROR_MESSAGE);
			}
		});
		
		LocalDate inicio=LocalDate.parse("2023-04-20");//deberia ser lo q el usuario ingresa
        LocalDate fin=LocalDate.parse("2023-05-05");//idem
		JMenuItem mntmGenerarTxtAlbumes = new JMenuItem("Generar TXT Albumes");
		mntmGenerarTxtAlbumes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sistema.generarReporteAlbumesEnArchivo(perfilInstagram.listadoDeAlbumes(inicio,fin));
				File reporteAlbumes = new File("reporteAlbumes.txt");
				if (reporteAlbumes.exists())
					JOptionPane.showMessageDialog(null, "El archivo TXT fue generado con éxito");
				else
					JOptionPane.showMessageDialog(null, "El archivo NO fue generado", "Error",
							JOptionPane.ERROR_MESSAGE);
				
			}	
		});

		JMenuItem ReportePublicaciones = new JMenuItem("Reporte de publicaciones");
		ReportePublicaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReportePublicaciones ventanaReportesPublicaciones = new ReportePublicaciones();
				ventanaReportesPublicaciones.setVisible(true);
			}
		});

		JMenuItem ReporteAlbumes = new JMenuItem("Reporte de Albumes");
		ReporteAlbumes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReporteAlbumes ventanaReporteAlbumes = new ReporteAlbumes();
				ventanaReporteAlbumes.setVisible(true);
			}
		});

		reportes.add(ReporteAlbumes);
		reportes.add(ReportePublicaciones);
		reportes.add(generaTXT);
		reportes.add(mntmGenerarTxtAlbumes);
		
		return reportes;
	}

	public JMenu menuTOPestadisticas() {
		JMenu estadisticas = new JMenu("Estadísticas");
		estadisticas.setFont(new Font("Open Sans", Font.PLAIN, 15));

	    JMenuItem Histograma = new JMenuItem("Histograma");
	    Histograma.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            // Crear una nueva ventana JFrame
	            JFrame ventanaHistograma = new JFrame("Estadística: Histograma Publicaciones");
	            ventanaHistograma.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	            ventanaHistograma.setSize(600, 600);

				// Obtener la lista de reportes de cantidad de "Me gusta" por tipo de publicación
				Map<String, List<Publicacion>> listaPublicacionesPorTipo = perfilInstagram
						.agruparPublicacionesPorTipo();

				// Crear el arreglo de datos para el histograma
				int[] data = new int[listaPublicacionesPorTipo.size()];
				String[] labels = new String[listaPublicacionesPorTipo.size()];
				int index = 0;
				for (Map.Entry<String, List<Publicacion>> entry : listaPublicacionesPorTipo.entrySet()) {
					String tipoPublicacion = entry.getKey();
					List<Publicacion> publicaciones = entry.getValue();
					int cantidadPublicaciones = publicaciones.size();
					data[index] = cantidadPublicaciones;
					labels[index] = tipoPublicacion;
					index++;
				}

	            Histograma estadisticasPanel = new Histograma();
	            estadisticasPanel.setHistogramData(data, labels);
	            ventanaHistograma.getContentPane().add(estadisticasPanel, BorderLayout.CENTER);
	            ventanaHistograma.setVisible(true);
	        }
	    });

	    estadisticas.add(Histograma);
	    
	    JMenuItem mntmGraficoDeTorta = new JMenuItem("Gráfico de torta");
	    mntmGraficoDeTorta.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            JFrame ventanaGraficoTorta = new JFrame("Estadística: Gráfico de Torta Álbumes");
	            ventanaGraficoTorta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	            ventanaGraficoTorta.setSize(700, 600);
	            
	            Map<String, Integer> cantidadEtiquetas = perfilInstagram.cantidadDeEtiquetasPorNombre();
	        
	            List<Integer> valores = new ArrayList<>(cantidadEtiquetas.values());
	            List<String> etiquetas = new ArrayList<>(cantidadEtiquetas.keySet());
	            
	            GraficoTorta panelGraficoTorta = new GraficoTorta(valores, etiquetas);
	            ventanaGraficoTorta.setContentPane(panelGraficoTorta);
	            
	            ventanaGraficoTorta.setVisible(true);
	        }
	    });

	    estadisticas.add(mntmGraficoDeTorta);
	    return estadisticas;
	}

	public void publicacionesActuales() {

		/**
		 * Setea el espacio donde aparecerán las Publicaciones del Perfil
		 */

		JPanel jpPublicaciones = new JPanel();
		jpPublicaciones.setBackground(Color.LIGHT_GRAY);
		jpPublicaciones.setFont(new Font("Open Sans", Font.PLAIN, 20));
		jpPublicaciones.setLayout(new GridLayout(0, 3, 10, 10)); // GridLayout con 3 columnas y espacios de 10 pix

		try {
			Set<Publicacion> listaPublicaciones = perfilInstagram.getPublicaciones();
			//publicacionesSeleccionadas = perfilInstagram.getPublicaciones();
			publicacionesSeleccionadas = new HashMap<>();
			
			// INICIO
			
			// Crear panel lateral
			JPanel panelLateral = new JPanel();
			panelLateral.setBackground(Color.WHITE);
			panelLateral.setLayout(new BorderLayout());

			// Crear título del panel
			JLabel tituloLabel = new JLabel("Publicaciones Seleccionadas");
			tituloLabel.setFont(new Font("Open Sans", Font.BOLD, 20));
			tituloLabel.setHorizontalAlignment(JLabel.CENTER);
			panelLateral.add(tituloLabel, BorderLayout.NORTH);
			
			JPanel botonReproducirPanel = new JPanel();
			botonReproducirPanel.setBackground(Color.WHITE);
			
			// Crear panel para el tiempo de reproducción y el botón "Reproducir"
			JPanel tiempoReproduccionBotonPanel = new JPanel();
			tiempoReproduccionBotonPanel.setBackground(Color.WHITE);
			tiempoReproduccionBotonPanel.setLayout(new BorderLayout());

			// Crear el JLabel para mostrar el tiempo de reproducción
			JLabel tiempoReproduccionLabel = new JLabel("Tiempo de reproducción: ");
			tiempoReproduccionLabel.setFont(new Font("Arial", Font.PLAIN, 12));
			tiempoReproduccionBotonPanel.add(tiempoReproduccionLabel, BorderLayout.NORTH);
			
			// Crear el botón "Reproducir"
			JButton botonReproducir = new JButton("Reproducir");
			botonReproducir.setFont(new Font("Arial", Font.PLAIN, 12));
			// Agregar ActionListener para el botón "Reproducir"
			botonReproducir.addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			        // Lógica para reproducir las publicaciones seleccionadas
			    }
			});
			botonReproducirPanel.add(botonReproducir);

			// Agregar el panel del botón "Reproducir" al panel lateral
			panelLateral.add(botonReproducirPanel, BorderLayout.SOUTH);
			
			// Crear el JScrollPane
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

			// Crear panel para la lista de publicaciones seleccionadas
			JPanel listaSeleccionadasPanel = new JPanel();
			listaSeleccionadasPanel.setBackground(Color.LIGHT_GRAY);
			listaSeleccionadasPanel.setLayout(new BoxLayout(listaSeleccionadasPanel, BoxLayout.Y_AXIS));
			
			
			// Agregar el JPanel al JScrollPane
			scrollPane.setViewportView(listaSeleccionadasPanel);

			// Agregar el JScrollPane al panel lateral
			panelLateral.add(scrollPane, BorderLayout.CENTER);

			// Agregar el panel lateral a tu interfaz principal donde se encuentre el contenido principal
			contentPane.add(panelLateral, BorderLayout.WEST);
			
			// FIN GPT

			for (Publicacion publicacion : listaPublicaciones) {
				JPanel panel = new JPanel(); // Crea un JPanel para cada publicación
				panel.setBackground(Color.WHITE);
				panel.setLayout(new GridLayout(4, 1)); // Configura un GridLayout para el panel interno de 4 columnas

				EnumTipoPublicacion tipoPublicacion = publicacion.getTipoPublicacion();
				JLabel imageLabel = new JLabel(); // JLabel que muestra el icono
				if (tipoPublicacion == EnumTipoPublicacion.AUDIO) {
					imageLabel.setIcon(AssetsUtils.obtenerIcono("audio"));
					panel.setBackground(Color.GRAY);
				} else if (tipoPublicacion == EnumTipoPublicacion.IMAGEN) {
					imageLabel.setIcon(AssetsUtils.obtenerIcono("image"));
					panel.setBackground(Color.GRAY);
				} else {
					imageLabel.setIcon(AssetsUtils.obtenerIcono("video"));
					panel.setBackground(Color.GRAY);
				}
				imageLabel.setHorizontalAlignment(JLabel.CENTER);

				JLabel nameLabel = new JLabel(publicacion.getNombrePublicacion());
				nameLabel.setHorizontalAlignment(JLabel.CENTER);
				nameLabel.setFont(new Font("Open Sans", Font.BOLD, 15));

				JCheckBox cBox = new JCheckBox();
				cBox.setBackground(Color.GRAY);
				cBox.setHorizontalAlignment(JCheckBox.CENTER);
				cBox.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (cBox.isSelected()) {
							PublicacionReproduccion publicacionReproduccion = new PublicacionReproduccion(publicacion.getNombrePublicacion(), publicacion.getFechaSubida(), publicacion.getCantMG(), publicacion.getDuracion(), publicacion.getTipoPublicacion());
							publicacionesSeleccionadas.putIfAbsent(publicacionReproduccion.getNombrePublicacion(), publicacionReproduccion);
							
							// INICIO
							// Establecer altura predefinida de cada item en la lista
							int itemHeight = 50; // Ajusta esta altura según tus necesidades
							
							
							// Crear panel para mostrar la publicación seleccionada en la lista lateral
							JPanel itemPanel = new JPanel();
							itemPanel.setBackground(Color.WHITE);
							itemPanel.setPreferredSize(new Dimension(0, itemHeight)); // Establecer la altura predefinida
				            
				            // Etiqueta para mostrar el nombre de la publicación seleccionada
				            JLabel nombreLabel = new JLabel(publicacionReproduccion.getNombrePublicacion());
				            nombreLabel.setFont(new Font("Open Sans", Font.PLAIN, 15));
				            nombreLabel.setHorizontalAlignment(JLabel.LEFT);
				            itemPanel.add(nombreLabel, BorderLayout.CENTER);
				            
				            // Botón "configurar" para cada publicación seleccionada
				            JButton configurarButton = new JButton("Configurar");
				            configurarButton.setFont(new Font("Arial", Font.PLAIN, 12));
				            configurarButton.addActionListener(new ActionListener() {
				                @Override
				                public void actionPerformed(ActionEvent e) {
				                    // Lógica para configurar la publicación seleccionada
				                }
				            });
				            itemPanel.add(configurarButton, BorderLayout.EAST);
				            
				            // Agregar el panel de la publicación seleccionada a la lista lateral
				            listaSeleccionadasPanel.add(itemPanel);
				            
				            // Actualizar visualmente la lista de publicaciones seleccionadas
				            listaSeleccionadasPanel.revalidate();
				            listaSeleccionadasPanel.repaint();
							// FIN GPT
							
						} else {
							publicacionesSeleccionadas.remove(publicacion.getNombrePublicacion());
							
							// Eliminar el panel de la publicación seleccionada de la lista lateral
				            Component[] components = listaSeleccionadasPanel.getComponents();
				            for (Component component : components) {
				                if (component instanceof JPanel) {
				                    JPanel itemPanel = (JPanel) component;
				                    JLabel nombreLabel = (JLabel) itemPanel.getComponent(0);
				                    String nombrePublicacion = nombreLabel.getText();
				                    if (nombrePublicacion.equals(publicacion.getNombrePublicacion())) {
				                        listaSeleccionadasPanel.remove(itemPanel);
				                        break;
				                    }
				                }
				            }
				            
				            // Actualizar visualmente la lista de publicaciones seleccionadas
				            listaSeleccionadasPanel.revalidate();
				            listaSeleccionadasPanel.repaint();
							
						}
					}
				});

				JButton btnFiltros = new JButton();
				btnFiltros.setHorizontalAlignment(JCheckBox.CENTER);
				btnFiltros.setText("Aplicar Filtros");
				btnFiltros.setBackground(Color.GRAY);
				btnFiltros.setFont(new Font("Arial", Font.ITALIC, 12));
				btnFiltros.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
					float duracion = publicacion.getDuracion();
					   String inicio = JOptionPane.showInputDialog(null, "Ingrese el momento de inicio (en segundos):",
								"Filtro: Inicio", JOptionPane.PLAIN_MESSAGE);
								
						if (inicio != null && !inicio.isEmpty()) {
							float tiempoInicio = Float.parseFloat(inicio);
							if (tiempoInicio > duracion) {
								JOptionPane.showMessageDialog(null,"El momento de inicio es mayor a la duración.",
										"Error",JOptionPane.ERROR_MESSAGE);
							} else {
								String fin = JOptionPane.showInputDialog(null,
										"Ingrese el momento de finalización (en segundos):", "Filtro: Finalización",
										JOptionPane.PLAIN_MESSAGE);
								if (fin != null && !fin.isEmpty()) {
									float tiempoFin = Float.parseFloat(fin);
									if (tiempoFin > duracion) {
										JOptionPane.showMessageDialog(null,"El momento de finalización es mayor a la duración.",
												"Error",JOptionPane.ERROR_MESSAGE);
									} else if (tiempoFin < tiempoInicio) {
										JOptionPane.showMessageDialog(null,"El momento de finalización es menor al momento de inicio.",
												"Error",JOptionPane.ERROR_MESSAGE);
									} else {
										duracionReproduccion += (tiempoFin - tiempoInicio);
										
										JOptionPane.showMessageDialog(null,"Los filtros se aplicaron correctamente.",
												"Excelente!",JOptionPane.PLAIN_MESSAGE);
									}
								}
							}
						}
					}
				});

				panel.add(imageLabel);
				panel.add(nameLabel);
				panel.add(cBox);
				if (tipoPublicacion == EnumTipoPublicacion.AUDIO || tipoPublicacion == EnumTipoPublicacion.VIDEO) {
					panel.add(btnFiltros);
				}

				jpPublicaciones.add(panel);
			}
			JPanel panelContenedor = new JPanel(new BorderLayout());
            panelContenedor.add(jpPublicaciones, BorderLayout.CENTER);

            contentPane.add(panelContenedor, BorderLayout.CENTER);
            contentPane.revalidate();
            contentPane.repaint();
		} catch (SinDatosException e) {
			jpPublicaciones.setVisible(false);
		}
	}
}
