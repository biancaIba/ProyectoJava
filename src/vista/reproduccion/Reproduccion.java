package vista.reproduccion;

import utilidades.FechaUtilidades;
import utilidades.TiempoUtilidades;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import javax.swing.Timer;

import modelo.*;
import modelo.enums.EnumTipoPublicacion;
import modelo.interfaces.IDurable;
import modelo.interfaces.IFiltrable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JProgressBar;

/**
 * Clase Reproduccion.
 * 
 * Panel que muestra la reproducción de una lista de publicaciones.
 */
public class Reproduccion extends JPanel {

	/** Panel que muestra la lista de publicaciones seleccionadas. */
	private JPanel listaSeleccionadasPanel;

	/** Etiqueta que muestra el tiempo total de reproducción. */
	private JLabel lblTiempoTotalValor;

	/** Panel de información de la publicación actualmente reproduciéndose. */
	private JPanel panelInformacion;

	/** Panel lateral que contiene la lista de reproducción y el tiempo total. */
	private JPanel panelLateral;

	/** Lista de publicaciones seleccionadas para reproducción. */
	private List<Publicacion> publicacionesSeleccionadas;

	/** Tiempo total de reproducción. */
	private double tiempoTotal;

	/** Barra de progreso de reproducción. */
	private JProgressBar progressBar;

	/** Etiqueta para el nombre de la publicación. */
	private JLabel lblNombre;

	/** Etiqueta para el valor del nombre de la publicación. */
	private JLabel lblNombreValor;

	/** Etiqueta para la fecha de la publicación. */
	private JLabel lblFecha;

	/** Etiqueta para el valor de la fecha de la publicación. */
	private JLabel lblFechaValor;

	/** Etiqueta para la cantidad de "Me gusta" de la publicación. */
	private JLabel lblMG;

	/** Etiqueta para el valor de la cantidad de "Me gusta" de la publicación. */
	private JLabel lblMGValor;

	/**
	 * Constructor de la clase Reproduccion.
	 *
	 * @param publicacionesSeleccionadas : Lista de publicaciones seleccionadas para reproducción.
	 */
	public Reproduccion(List<Publicacion> publicacionesSeleccionadas) {
		super();
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout(15, 0));
		this.publicacionesSeleccionadas = publicacionesSeleccionadas;

		panelLateral = instanciarListaReproduccion();
		add(panelLateral, BorderLayout.WEST);

		panelInformacion = new JPanel();
		add(panelInformacion, BorderLayout.CENTER);
		panelInformacion.setLayout(new GridLayout(4, 1, 0, 0));

		cargarListaReproduccion(publicacionesSeleccionadas);
		cargarPanelDeInformacion(publicacionesSeleccionadas);
		cargarTiempoTotalInicial();
		renderizarTiempoTotal();
	}

	/**
	 * Instancia el panel lateral que muestra la lista de reproducción.
	 *
	 * @return El panel lateral.
	 */
	public JPanel instanciarListaReproduccion() {

		JPanel panelLateral = new JPanel();
		panelLateral.setBackground(Color.WHITE);
		panelLateral.setLayout(new BorderLayout());

		JLabel tituloLabel = new JLabel("Lista de reproducción");
		tituloLabel.setFont(new Font("Open Sans", Font.BOLD, 14));
		tituloLabel.setForeground(Color.WHITE);
		tituloLabel.setHorizontalAlignment(JLabel.CENTER);
		panelLateral.add(tituloLabel, BorderLayout.NORTH);
		panelLateral.setBackground(Color.GRAY);

		JPanel tiempoReproduccionPanel = new JPanel();
		tiempoReproduccionPanel.setBackground(Color.WHITE);
		tiempoReproduccionPanel.setLayout(new BorderLayout());

		JLabel lblTiempoTotal = new JLabel("Tiempo de reproducción total: ");
		lblTiempoTotal.setFont(new Font("Arial", Font.PLAIN, 12));
		tiempoReproduccionPanel.add(lblTiempoTotal, BorderLayout.WEST);

		lblTiempoTotalValor = new JLabel("00:00:00");
		lblTiempoTotalValor.setFont(new Font("Arial", Font.PLAIN, 12));
		tiempoReproduccionPanel.add(lblTiempoTotalValor, BorderLayout.CENTER);
		panelLateral.add(tiempoReproduccionPanel, BorderLayout.SOUTH);

		progressBar = new JProgressBar();
		tiempoReproduccionPanel.add(progressBar, BorderLayout.SOUTH);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		listaSeleccionadasPanel = new JPanel();
		listaSeleccionadasPanel.setBackground(Color.LIGHT_GRAY);
		listaSeleccionadasPanel.setLayout(new BoxLayout(listaSeleccionadasPanel, BoxLayout.Y_AXIS));

		scrollPane.setViewportView(listaSeleccionadasPanel);
		panelLateral.add(scrollPane, BorderLayout.CENTER);
		return panelLateral;

	}

	/**
	 * Cargar lista reproduccion.
	 *
	 * @param publicacionesSeleccionadas the publicaciones seleccionadas
	 */
	public void cargarListaReproduccion(List<Publicacion> publicacionesSeleccionadas) {

		Iterator<Publicacion> iterador = publicacionesSeleccionadas.iterator();
		while (iterador.hasNext()) {
			Publicacion publicacion = iterador.next();
			JPanel itemPanel = new JPanel();
			itemPanel.setBackground(Color.WHITE);

			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = GridBagConstraints.RELATIVE;
			gbc.weightx = 1.0; //
			gbc.insets = new Insets(5, 5, 5, 5);

			JLabel nombrePublicacion = new JLabel(publicacion.getNombrePublicacion());
			nombrePublicacion.setFont(new Font("Open Sans", Font.PLAIN, 15));
			nombrePublicacion.setBackground(Color.BLACK);
			itemPanel.add(nombrePublicacion);

			listaSeleccionadasPanel.add(itemPanel, gbc);
		}
	}

	/**
	 * Carga el panel de información de la publicación actualmente reproduciéndose.
	 *
	 * @param publicacionesSeleccionadas : Lista de publicaciones seleccionadas para reproducción.
	 */
	public void cargarPanelDeInformacion(List<Publicacion> publicacionesSeleccionadas) {
		Iterator<Publicacion> iterador = publicacionesSeleccionadas.iterator();
		if (iterador.hasNext()) {
			Publicacion _publicacion = iterador.next();
			renderizarPanelesDeInformacion(_publicacion);

			Timer timer = new Timer(1000, new ActionListener() {
				float duracionParcial = 0;
				Publicacion publicacion = _publicacion;

				@Override
				public void actionPerformed(ActionEvent e) {
					duracionParcial++;
					progressBar.setValue(progressBar.getValue() + 1);
					disminuirTiempoTotal();
					renderizarTiempoTotal();
					if (duracionParcial == publicacion.getDuracion()) {
						if (iterador.hasNext()) {
							panelInformacion.removeAll();
							publicacion = iterador.next();
							renderizarPanelesDeInformacion(publicacion);
							panelInformacion.revalidate();
							panelInformacion.repaint();
							duracionParcial = 0;

						} else {
							((Timer) e.getSource()).stop();
							JOptionPane.showMessageDialog(null, "La reproducción ha finalizado");
						}
					}
				}
			});
			timer.start();
		}
	}

	/**
	 * Carga el tiempo total inicial de reproduccion.
	 */
	public void cargarTiempoTotalInicial() {
		tiempoTotal = publicacionesSeleccionadas.stream().mapToDouble(publicacion -> publicacion.getDuracion()).sum();
		progressBar.setMinimum(0);
		progressBar.setMaximum((int) tiempoTotal);
	}

	/**
	 * Disminuiye el tiempo total de reproducción en 1 segundo..
	 */
	public void disminuirTiempoTotal() {
		tiempoTotal -= 1.0;
	}

	/**
	 * Renderiza el tiempo total de reproducción en el panel.
	 */
	public void renderizarTiempoTotal() {
		lblTiempoTotalValor.setText(TiempoUtilidades.duracionFormateada(tiempoTotal));
	}

	/**
	 * Renderizalos paneles de información de la publicación actualmente reproduciéndose.
	 *
	  * @param publicacion : Publicación actualmente reproduciéndose.
	 */
	public void renderizarPanelesDeInformacion(Publicacion publicacion) {
	    lblNombre = new JLabel("La publicacion reproduciendose es:");
	    lblNombre.setPreferredSize(new Dimension(100, 20));
	    panelInformacion.add(lblNombre);

	    lblNombreValor = new JLabel("");
	    lblNombreValor.setPreferredSize(new Dimension(200, 20));
	    lblNombreValor.setText(publicacion.getNombrePublicacion());
	    panelInformacion.add(lblNombreValor);

	    lblFecha = new JLabel("Fecha:");
	    lblFecha.setPreferredSize(new Dimension(100, 20));
	    panelInformacion.add(lblFecha);

	    lblFechaValor = new JLabel("");
	    lblFechaValor.setPreferredSize(new Dimension(200, 20));
	    String fecha = FechaUtilidades.formatearFechaAString(publicacion.getFechaSubida());
	    lblFechaValor.setText(fecha);
	    panelInformacion.add(lblFechaValor);

	    lblMG = new JLabel("Cantidad de Me gusta:");
	    lblMG.setPreferredSize(new Dimension(150, 20));
	    panelInformacion.add(lblMG);

	    lblMGValor = new JLabel("");
	    lblMGValor.setPreferredSize(new Dimension(50, 20));
	    lblMGValor.setText(String.valueOf(publicacion.getCantidadMG()));
	    panelInformacion.add(lblMGValor);

	    if (publicacion.getTipoPublicacion() == EnumTipoPublicacion.AUDIO
	            || publicacion.getTipoPublicacion() == EnumTipoPublicacion.VIDEO) {
	        panelInformacion.add(new PanelReproduccionDuracion((IDurable) publicacion));
	    }
	    if (publicacion.getTipoPublicacion() == EnumTipoPublicacion.IMAGEN
	            || publicacion.getTipoPublicacion() == EnumTipoPublicacion.VIDEO) {
	        panelInformacion.add(new PanelReproduccionFiltros((IFiltrable) publicacion));
	    }
	}

}
