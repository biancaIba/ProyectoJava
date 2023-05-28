package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ToolTipManager;
import javax.swing.border.EmptyBorder;
import excepciones.AlbumExistenteExcepcion;
import excepciones.AlbumNoEncontradoExcepcion;
import excepciones.PublicacionNoEncontradaExcepcion;
import excepciones.SinDatosExcepcion;
import modelo.Album;
import modelo.PerfilInstagram;
import modelo.Publicacion;
import modelo.enums.EnumTipoPublicacion;
import modelo.reportes.ReporteAlbum;
import modelo.reportes.ReportePublicacion;
import utilidades.IconosUtilidades;
import utilidades.TiempoUtilidades;
import vista.edicion.Edicion;
import vista.estadisticas.GraficoTorta;
import vista.estadisticas.Histograma;
import vista.reportes.ReporteAlbumes;
import vista.reportes.ReportePublicaciones;
import vista.reproduccion.Reproduccion;

/**
 * Clase PerfilUsuario.
 */

public class PerfilUsuario extends JFrame {

	/** El número de versión para la serialización. */
	private static final long serialVersionUID = 1L;

	/** El panel de contenido. */
	private JPanel contentPane;

	/** La instancia del perfil de Instagram. */
	private static PerfilInstagram perfilInstagram;

	/** La lista de publicaciones seleccionadas en el panel. */
	private List<Publicacion> publicacionesSeleccionadasPanel;

	/** La duración total de reproducción. */
	private float duracionReproduccionTotal = 0;

	/** La etiqueta de información. */
	private JLabel informacionLabel;

	/** El panel de publicaciones. */
	private JPanel jpPublicaciones;

	/** El panel de lista seleccionadas. */
	private JPanel listaSeleccionadasPanel;

	/**
	 * Constructor de la clase PerfilUsuario. Inicializa el perfil de Instagram, la
	 * lista de publicaciones seleccionadas y configura la interfaz gráfica.
	 */
	public PerfilUsuario() {
		perfilInstagram = PerfilInstagram.getInstance();
		publicacionesSeleccionadasPanel = new ArrayList<>();
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
		pantallaPrincipal();

		File datos = new File("Perfil.ser");
		if (!datos.exists()) {
			String nombrePerfil = JOptionPane.showInputDialog(null, "Ingrese el nombre del Perfil", "Nombre del Perfil",
					JOptionPane.PLAIN_MESSAGE);
			if (nombrePerfil == null || nombrePerfil.isEmpty())
				System.exit(0);
			else
				perfilInstagram.setNombrePerfil(nombrePerfil);
		}
		setTitle("Perfil del usuario " + perfilInstagram.getNombrePerfil());
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirma = JOptionPane.showConfirmDialog(null, "¿Desea guardar los cambios?", "Guardar cambios",
						JOptionPane.YES_NO_OPTION);
				if (confirma == JOptionPane.YES_OPTION) {
					PerfilInstagram.serializar();
				}
			}
		});
	}

	/**
	 * Crea y muestra el menú superior de la aplicación. Este método agrega
	 * diferentes menús desplegables al menú principal, como álbumes, reportes,
	 * estadísticas y opciones. El menú se muestra en la parte superior de la
	 * interfaz gráfica de usuario.
	 */
	public void menuTop() {
		JMenuBar menuPrincipal = new JMenuBar();

		menuPrincipal.setOpaque(false);
		menuPrincipal.setBackground(Color.LIGHT_GRAY);
		menuPrincipal.setFont(new Font("Open Sans", Font.PLAIN, 20));
		menuPrincipal.setBorderPainted(true);

		menuPrincipal.add(menuTOPalbumes());
		menuPrincipal.add(menuTOPreportes());
		menuPrincipal.add(menuTOPestadisticas());
		menuPrincipal.add(menuTOPopciones());

		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(menuPrincipal, BorderLayout.NORTH);
	}

	/**
	 * Crea y retorna un menú desplegable para administrar los álbumes.
	 *
	 * @return El menú desplegable JMenu con las opciones relacionadas a los álbumes
	 *         el cual incluye el boton de crear album, gestion albumes (agregar
	 *         publicacion, sacar publicacion de un album, crear subalbum), eliminar
	 *         album.
	 */
	public static JMenu menuTOPalbumes() {
		JMenu albumes = new JMenu("Álbumes");
		albumes.setFont(new Font("Open Sans", Font.PLAIN, 15));

		JMenuItem crearAlbum = new JMenuItem("Crear álbum");

		crearAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				frame.setAlwaysOnTop(true);
				String nombreAlbum = JOptionPane.showInputDialog(frame, "Ingrese el nombre del nuevo Álbum",
						"Crear Álbum", JOptionPane.PLAIN_MESSAGE);

				if (nombreAlbum != null && !nombreAlbum.isEmpty()) {
					Album nuevoAlbum = new Album(nombreAlbum);
					try {
						perfilInstagram.agregarAlbum(nuevoAlbum);
						JOptionPane.showMessageDialog(frame, "El álbum fue agregado con éxito");
					} catch (AlbumExistenteExcepcion e1) {
						JOptionPane.showMessageDialog(frame, "El álbum ya existe");
					}
				}
			}
		});

		JMenuItem gestionaAlbum = new JMenu("Gestionar álbumes");

		JMenuItem gaAgregaPubli = new JMenuItem("Agregar Publicación a un Álbum");
		gaAgregaPubli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				frame.setAlwaysOnTop(true);
				String nombreAlbum = JOptionPane.showInputDialog(frame, "Ingrese el nombre del Álbum", "Ingresar Album",
						JOptionPane.PLAIN_MESSAGE);
				if (nombreAlbum != null && !nombreAlbum.isEmpty()) {
					try {
						Album album = perfilInstagram.buscarAlbum(nombreAlbum);
						String nombrePubli = JOptionPane.showInputDialog(frame, "Ingrese el nombre de la Publicación",
								"Ingresar Publicacion", JOptionPane.PLAIN_MESSAGE);
						if (nombrePubli != null && !nombrePubli.isEmpty()) {
							try {
								Publicacion publicacion = perfilInstagram.buscarPublicacion(nombrePubli);
								perfilInstagram.agregarPublicacionDentroAlbum(album, publicacion);
								JOptionPane.showMessageDialog(frame, "La publicación fue agregada con éxito");
							} catch (PublicacionNoEncontradaExcepcion e1) {
								JOptionPane.showMessageDialog(frame, "La publicación NO existe. Intente de nuevo.",
										"Error", JOptionPane.ERROR_MESSAGE);
							}
						}
					} catch (AlbumNoEncontradoExcepcion e1) {
						JOptionPane.showMessageDialog(frame, "El álbum NO existe. Intente de nuevo.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		gestionaAlbum.add(gaAgregaPubli);

		JMenuItem gaSacarPubli = new JMenuItem("Sacar publicación de un álbum");
		gaSacarPubli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				frame.setAlwaysOnTop(true);
				String nombrePublicacion = JOptionPane.showInputDialog(frame, "Ingrese el nombre de la Publicación",
						"Ingresar Publicacion", JOptionPane.PLAIN_MESSAGE);
				if (nombrePublicacion != null && !nombrePublicacion.isEmpty()) {
					try {
						String nombreAlbum = JOptionPane.showInputDialog(frame, "Ingrese el nombre del Álbum",
								"Ingresar Álbum", JOptionPane.PLAIN_MESSAGE);
						if (nombreAlbum != null && !nombreAlbum.isEmpty()) {
							try {
								Publicacion publicacionAEliminar = perfilInstagram.buscarPublicacion(nombrePublicacion);
								Album album = perfilInstagram.buscarAlbum(nombreAlbum);
								perfilInstagram.sacarPublicacionDelAlbum(publicacionAEliminar, album);
								JOptionPane.showMessageDialog(frame, "Publicacion eliminada del álbum con éxito");
							} catch (AlbumNoEncontradoExcepcion e1) {
								JOptionPane.showMessageDialog(frame, "El álbum NO existe. Intente de nuevo.", "Error",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					} catch (PublicacionNoEncontradaExcepcion e1) {
						JOptionPane.showMessageDialog(frame, "La publicación NO existe. Intente de nuevo.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		gestionaAlbum.add(gaSacarPubli);

		JMenuItem gaAgregaSubAlbum = new JMenuItem("Crear un subálbum");
		gaAgregaSubAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				frame.setAlwaysOnTop(true);
				String nombreSubAlbum = JOptionPane.showInputDialog(frame, "Ingrese el nombre del subálbum a crear",
						"Ingresar Sub Álbum", JOptionPane.PLAIN_MESSAGE);
				if (nombreSubAlbum != null && !nombreSubAlbum.isEmpty()) {
					String nombreAlbumPadre = JOptionPane.showInputDialog(frame, "Ingrese el nombre del álbum Padre",
							"Ingresar Álbum Padre", JOptionPane.PLAIN_MESSAGE);
					if (nombreAlbumPadre != null && !nombreAlbumPadre.isEmpty()) {
						try {
							Album albumPadre = perfilInstagram.buscarAlbum(nombreAlbumPadre);
							Album nuevoSubAlbum = new Album(nombreSubAlbum);
							try {
								albumPadre.agregarSubAlbum(nuevoSubAlbum);
								perfilInstagram.agregarAlbum(nuevoSubAlbum);
								JOptionPane.showMessageDialog(frame, "El subálbum fue agregado con éxito");
							} catch (AlbumExistenteExcepcion e1) {
								JOptionPane.showMessageDialog(frame, "El subálbum ya existe");
							}

						} catch (AlbumNoEncontradoExcepcion ex) {
							JOptionPane.showMessageDialog(frame, "El álbum padre no existe. Intente de nuevo.", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		gestionaAlbum.add(gaAgregaSubAlbum);

		JMenuItem eliminaAlbum = new JMenuItem("Eliminar álbum");
		eliminaAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				frame.setAlwaysOnTop(true);
				String nombreAlbum = JOptionPane.showInputDialog(frame, "Ingrese el nombre del Álbum a eliminar",
						"Ingresar Álbum", JOptionPane.PLAIN_MESSAGE);
				if (nombreAlbum != null && !nombreAlbum.isEmpty()) {
					try {
						Album albumAEliminar = perfilInstagram.buscarAlbum(nombreAlbum);
						perfilInstagram.eliminarAlbum(albumAEliminar);
						JOptionPane.showMessageDialog(frame, "El álbum fue eliminado con éxito");
					} catch (AlbumNoEncontradoExcepcion e2) {
						JOptionPane.showMessageDialog(frame, "El álbum NO existe. Intente de nuevo.", "Error",
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

	/**
	 * Crea y devuelve el menú de reportes.
	 *
	 * @return El objeto JMenu que representa el menú de reportes, el cual incluye
	 *         el boton de reporte de albumes en pantalla y txt, reporte de
	 *         publicaciones en pantalla y txt.
	 */
	public JMenu menuTOPreportes() {
		JMenu reportes = new JMenu("Reportes");
		reportes.setFont(new Font("Open Sans", Font.PLAIN, 15));

		JMenuItem generaTXT = new JMenuItem("Generar TXT Publicaciones");
		generaTXT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ReportePublicacion.generarReportePublicacionEnArchivo(
							perfilInstagram.ordenarPublicacionPorCantidadYPromedioDeMg());
					JOptionPane.showMessageDialog(null, "El archivo TXT fue generado con éxito");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "El archivo NO fue generado", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JMenuItem mntmGenerarTxtAlbumes = new JMenuItem("Generar TXT Albumes");
		
		mntmGenerarTxtAlbumes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Mostrar diálogo de entrada de texto para la fecha de inicio
		        String inicioStr = JOptionPane.showInputDialog(null, "Ingrese la fecha de inicio (dd/MM/yyyy):");
		        LocalDate inicio = LocalDate.parse(inicioStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		        // Mostrar diálogo de entrada de texto para la fecha de fin
		        String finStr = JOptionPane.showInputDialog(null, "Ingrese la fecha de fin (dd/MM/yyyy):");
		        LocalDate fin = LocalDate.parse(finStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				try {
					ReporteAlbum.generarReporteAlbumesEnArchivo(perfilInstagram.listadoDeAlbumesFiltradoPorFecha
							(inicio, fin),inicio,fin);
					JOptionPane.showMessageDialog(null, "El archivo TXT fue generado con éxito");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "El archivo NO fue generado", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JMenuItem ReportePublicaciones = new JMenuItem("Reporte de publicaciones");
		ReportePublicaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReportePublicaciones ventanaReportesPublicaciones = new ReportePublicaciones(perfilInstagram);
				ventanaReportesPublicaciones.setVisible(true);
			}
		});

		JMenuItem ReporteAlbumes = new JMenuItem("Reporte de Albumes");
		ReporteAlbumes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReporteAlbumes ventanaReporteAlbumes = new ReporteAlbumes(perfilInstagram);
				ventanaReporteAlbumes.setVisible(true);
			}
		});

		reportes.add(ReporteAlbumes);
		reportes.add(ReportePublicaciones);
		reportes.add(generaTXT);
		reportes.add(mntmGenerarTxtAlbumes);

		return reportes;
	}

	/**
	 * Crea y devuelve el menú de estadísticas.
	 *
	 * @return El objeto JMenu que representa el menú de estadísticas.
	 */
	public JMenu menuTOPestadisticas() {
		JMenu estadisticas = new JMenu("Estadísticas");
		estadisticas.setFont(new Font("Open Sans", Font.PLAIN, 15));

		JMenuItem Histograma = new JMenuItem("Histograma");
		Histograma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFrame ventanaHistograma = new JFrame("Estadística: Histograma Publicaciones");
				ventanaHistograma.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				ventanaHistograma.setSize(600, 600);

				Map<String, List<Publicacion>> listaPublicacionesPorTipo = perfilInstagram
						.agruparPublicacionesPorTipo();
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
				try {
					estadisticasPanel.setHistogramData(data, labels);
				} catch (SinDatosExcepcion e1) {
					JOptionPane.showMessageDialog(null, "No hay datos.", "Error", JOptionPane.ERROR_MESSAGE);
				}
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

				GraficoTorta panelGraficoTorta;
				try {
					panelGraficoTorta = new GraficoTorta(valores, etiquetas);
					ventanaGraficoTorta.setContentPane(panelGraficoTorta);
					ventanaGraficoTorta.setVisible(true);
				} catch (SinDatosExcepcion e1) {
					JOptionPane.showMessageDialog(null, "No hay datos.", "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		estadisticas.add(mntmGraficoDeTorta);
		return estadisticas;
	}

	/**
	 * Crea y devuelve el menú de opciones.
	 *
	 * @return El objeto JMenu que representa el menú de opciones, el cual incluye
	 *         el boton de cargar datos.
	 */
	public JMenu menuTOPopciones() {
		JMenu opciones = new JMenu("Opciones");
		opciones.setFont(new Font("Open Sans", Font.PLAIN, 15));

		JMenuItem cargaDatos = new JMenuItem("Cargar datos desde XML");
		cargaDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				perfilInstagram.cargarPublicaciones();
				JOptionPane.showMessageDialog(null, "Los datos fueron agregados con éxito");
				pantallaPrincipal();
			}
		});
		opciones.add(cargaDatos);
		return opciones;
	}

	/**
	 * Actualizar duracion total de reproduccion en el Label.
	 */
	private void actualizarDuracionTotal() {
		informacionLabel.setText(TiempoUtilidades.duracionFormateada(duracionReproduccionTotal));
	}

	/**
	 * Crea el panel de publicaciones seleccionadas para reproducir. Informa la
	 * duración de reproducción total en base a la configuración seleccionada.
	 * Permite configurar el orden de reproducción.
	 */
	private void panelPublicacionesSeleccionadas() {
		JPanel panelLateral = new JPanel();
		panelLateral.setBackground(Color.WHITE);
		panelLateral.setLayout(new BorderLayout());

		JLabel tituloLabel = new JLabel("Publicaciones Seleccionadas");
		tituloLabel.setFont(new Font("Open Sans", Font.BOLD, 14));
		tituloLabel.setForeground(Color.WHITE);
		tituloLabel.setHorizontalAlignment(JLabel.CENTER);
		panelLateral.add(tituloLabel, BorderLayout.NORTH);
		panelLateral.setBackground(Color.GRAY);

		JPanel tiempoReproduccionPanel = new JPanel();
		tiempoReproduccionPanel.setBackground(Color.WHITE);
		tiempoReproduccionPanel.setLayout(new BorderLayout());

		JLabel tiempoReproduccionLabel = new JLabel("Tiempo de reproducción Original: ");
		tiempoReproduccionLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		tiempoReproduccionPanel.add(tiempoReproduccionLabel, BorderLayout.NORTH);

		informacionLabel = new JLabel("00:00:00");
		informacionLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		tiempoReproduccionPanel.add(informacionLabel, BorderLayout.CENTER);

		JButton botonReproducir = new JButton("Reproducir");
		botonReproducir.setFont(new Font("Arial", Font.PLAIN, 12));
		botonReproducir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel panelMensajes = new JPanel();
				panelMensajes.setLayout(new BoxLayout(panelMensajes, BoxLayout.PAGE_AXIS));

				JLabel mensajeAplicarFiltros = new JLabel(
						"No olvides aplicar filtros y seleccionar publicaciones para Reproducir.");
				mensajeAplicarFiltros.setAlignmentX(Component.CENTER_ALIGNMENT);
				panelMensajes.add(mensajeAplicarFiltros);
				panelMensajes.add(Box.createVerticalStrut(10));

				JLabel mensajeDuracionReproduccion = new JLabel(
						"El tiempo de reproducción total es de: " + duracionReproduccionTotal + " segundos.");
				mensajeDuracionReproduccion.setAlignmentX(Component.CENTER_ALIGNMENT);
				panelMensajes.add(mensajeDuracionReproduccion);
				panelMensajes.add(Box.createVerticalStrut(10));

				JLabel mensajeSeleccioneOrden = new JLabel("Selecciona un orden de reproducción:");
				mensajeSeleccioneOrden.setAlignmentX(Component.CENTER_ALIGNMENT);
				panelMensajes.add(mensajeSeleccioneOrden);

				String[] opciones = { "Nombre", "Fecha", "Cantidad de MG" };
				JComboBox<String> comboBoxOrden = new JComboBox<>(opciones);
				panelMensajes.add(Box.createVerticalStrut(10));

				panelMensajes.add(comboBoxOrden);

				int opcionSeleccionada = JOptionPane.showOptionDialog(null, panelMensajes, "Orden de Reproducción",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

				if (opcionSeleccionada == JOptionPane.OK_OPTION) {
					String seleccionOrden = (String) comboBoxOrden.getSelectedItem();
					List<Publicacion> copiaPublicaciones = new ArrayList<>(publicacionesSeleccionadasPanel);

					if (seleccionOrden.equals("Nombre")) {
						Collections.sort(copiaPublicaciones,
								Comparator.comparing(Publicacion::getNombrePublicacion, String.CASE_INSENSITIVE_ORDER));
					} else if (seleccionOrden.equals("Fecha")) {
						Collections.sort(copiaPublicaciones, Comparator.comparing(Publicacion::getFechaSubida));
					} else if (seleccionOrden.equals("Cantidad de MG")) {
						Collections.sort(copiaPublicaciones, Comparator.comparingInt(Publicacion::getCantidadMG));
					}

					JFrame ventanaReproduccion = new JFrame("Edición de la publicación");
					ventanaReproduccion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					ventanaReproduccion.setSize(600, 600);

					Reproduccion panelReproduccion = new Reproduccion(copiaPublicaciones);
					ventanaReproduccion.setContentPane(panelReproduccion);

					ventanaReproduccion.setVisible(true);
				}
			}
		});

		tiempoReproduccionPanel.add(botonReproducir, BorderLayout.SOUTH);
		panelLateral.add(tiempoReproduccionPanel, BorderLayout.SOUTH);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		listaSeleccionadasPanel = new JPanel();
		listaSeleccionadasPanel.setBackground(Color.LIGHT_GRAY);
		listaSeleccionadasPanel.setLayout(new BoxLayout(listaSeleccionadasPanel, BoxLayout.Y_AXIS));

		scrollPane.setViewportView(listaSeleccionadasPanel);
		panelLateral.add(scrollPane, BorderLayout.CENTER);
		if (contentPane.getComponentCount() > 1) {
			contentPane.remove(1);
		}
		contentPane.add(panelLateral, BorderLayout.WEST);
	}

	/**
	 * Crea y muestra la pantalla principal de la aplicación. Esta pantalla muestra
	 * las publicaciones disponibles para seleccionar. Permite agregar las
	 * publicaciones seleccionadas a la lista de reproducción. También permite
	 * configurar y editar las publicaciones seleccionadas.
	 */
	public void pantallaPrincipal() {
		jpPublicaciones = new JPanel();
		jpPublicaciones.setBackground(Color.LIGHT_GRAY);
		jpPublicaciones.setFont(new Font("Open Sans", Font.PLAIN, 20));
		jpPublicaciones.setLayout(new GridLayout(0, 3, 10, 10)); // GridLayout con 3 columnas y espacios de 10 pix
		panelPublicacionesSeleccionadas();
		try {
			Set<Publicacion> listaPublicaciones = perfilInstagram.getPublicaciones();

			for (Publicacion publicacion : listaPublicaciones) {
				JPanel panel = new JPanel();
				panel.setBackground(Color.WHITE);
				panel.setLayout(new GridLayout(4, 1));

				EnumTipoPublicacion tipoPublicacion = publicacion.getTipoPublicacion();
				JLabel imageLabel = new JLabel();
				if (tipoPublicacion == EnumTipoPublicacion.AUDIO) {
					imageLabel.setIcon(IconosUtilidades.obtenerIcono("audio", 35, 35));
					panel.setBackground(Color.GRAY);
					panel.setToolTipText("Audio");
				} else if (tipoPublicacion == EnumTipoPublicacion.IMAGEN) {
					imageLabel.setIcon(IconosUtilidades.obtenerIcono("image", 35, 35));
					panel.setBackground(Color.GRAY);
					panel.setToolTipText("Imagen");
				} else {
					imageLabel.setIcon(IconosUtilidades.obtenerIcono("video", 35, 35));
					panel.setBackground(Color.GRAY);
					panel.setToolTipText("Video");
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

							publicacionesSeleccionadasPanel.add(publicacion);
							final float[] duracionActual = { publicacion.getDuracion() };
							duracionReproduccionTotal += duracionActual[0];

							JPanel itemPanel = new JPanel();
							itemPanel.setBackground(Color.WHITE);

							GridBagConstraints gbc = new GridBagConstraints();
							gbc.gridx = 0;
							gbc.gridy = GridBagConstraints.RELATIVE;
							gbc.weightx = 1.0; //
							gbc.insets = new Insets(5, 5, 5, 5);

							JLabel nombrePublicacion = new JLabel(publicacion.getNombrePublicacion());
							nombrePublicacion.setFont(new Font("Open Sans", Font.PLAIN, 15));
							nombrePublicacion.setBackground(Color.WHITE);
							itemPanel.add(nombrePublicacion);

							String duracion = Float.toString(publicacion.getDuracion());
							JLabel duracionPublicacion = new JLabel("Duracion: " + duracion);
							duracionPublicacion.setFont(new Font("Open Sans", Font.PLAIN, 15));
							duracionPublicacion.setBackground(Color.WHITE);
							itemPanel.add(duracionPublicacion);

							gbc.anchor = GridBagConstraints.WEST;
							gbc.weightx = 0.8;
							listaSeleccionadasPanel.add(itemPanel, gbc);

							gbc.gridx = 1;
							gbc.weightx = 0.1;
							gbc.anchor = GridBagConstraints.CENTER;

							JButton configurarButton = new JButton("Configurar");
							configurarButton.setFont(new Font("Arial", Font.PLAIN, 12));
							configurarButton.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {

									JFrame ventanaEdicion = new JFrame("Edición de la publicación");
									ventanaEdicion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
									ventanaEdicion.setSize(700, 600);

									Edicion panelEdicion = new Edicion(publicacion);
									ventanaEdicion.setContentPane(panelEdicion);

									ventanaEdicion.setVisible(true);

									ventanaEdicion.addWindowListener(new WindowAdapter() {
										@Override
										public void windowClosing(WindowEvent e) {
											duracionReproduccionTotal = duracionReproduccionTotal - duracionActual[0]
													+ publicacion.getDuracion();
											duracionPublicacion.setText(Float.toString(publicacion.getDuracion()));
											actualizarDuracionTotal();
											duracionActual[0] = publicacion.getDuracion();
											JOptionPane.showMessageDialog(null, "Los datos fueron guardados",
													"Datos guardados", JOptionPane.INFORMATION_MESSAGE);
										}
									});
								}
							});
							itemPanel.add(configurarButton);
							listaSeleccionadasPanel.revalidate();
							listaSeleccionadasPanel.repaint();

						} else {
							publicacionesSeleccionadasPanel.remove(publicacion);
							duracionReproduccionTotal -= publicacion.getDuracion();

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

							listaSeleccionadasPanel.revalidate();
							listaSeleccionadasPanel.repaint();

						}
						actualizarDuracionTotal();
					}
				});

				panel.add(imageLabel);
				panel.add(nameLabel);
				panel.add(cBox);

				jpPublicaciones.add(panel);
				ToolTipManager.sharedInstance().setInitialDelay(20);
				ToolTipManager.sharedInstance().setDismissDelay(10000);
			}
			JPanel panelContenedor = new JPanel(new BorderLayout());
			panelContenedor.add(jpPublicaciones, BorderLayout.CENTER);

			contentPane.add(panelContenedor, BorderLayout.CENTER);
			contentPane.revalidate();
			contentPane.repaint();
		} catch (SinDatosExcepcion e) {
			jpPublicaciones.setVisible(false);
		}
	}
}
