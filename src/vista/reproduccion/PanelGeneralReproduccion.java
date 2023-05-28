package vista.reproduccion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import modelo.Publicacion;
import utilidades.FechaUtilidades;

/**
 * Clase PanelGeneralReproduccion.
 * 
 * Panel que muestra los datos de la publicacion que se esta en reproduccion.
 */
public class PanelGeneralReproduccion extends JPanel {

	private Publicacion publicacion;

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

	/** Etiqueta duracion. */
	private JLabel lblDuracion;

	/** Etiqueta duracion valor. */
	private JLabel lblDuracionValor;

	public PanelGeneralReproduccion(Publicacion publicacion) {
		super();
		this.publicacion = publicacion;
		instanciarPanelGeneralReproduccion();
		cargarDatosPanelGeneralReproduccion();

	}

	/**
	 * Instanciar panel general de la reproduccion.
	 */
	private void instanciarPanelGeneralReproduccion() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new GridLayout(4, 2, 0, 15));

		lblNombre = new JLabel("Nombre de la publicacion: ");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setPreferredSize(new Dimension(100, 20));
		add(lblNombre);

		lblNombreValor = new JLabel("");
		lblNombreValor.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreValor.setPreferredSize(new Dimension(100, 20));
		add(lblNombreValor);

		lblFecha = new JLabel("Fecha:");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setPreferredSize(new Dimension(100, 20));
		add(lblFecha);

		lblFechaValor = new JLabel("");
		lblFechaValor.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaValor.setPreferredSize(new Dimension(200, 20));
		add(lblFechaValor);

		lblMG = new JLabel("Cantidad de Me gusta:");
		lblMG.setHorizontalAlignment(SwingConstants.CENTER);
		lblMG.setPreferredSize(new Dimension(150, 20));
		add(lblMG);

		lblMGValor = new JLabel("");
		lblMGValor.setHorizontalAlignment(SwingConstants.CENTER);
		lblMGValor.setPreferredSize(new Dimension(50, 20));

		add(lblMGValor);

		lblDuracion = new JLabel("Duracion: ");
		lblDuracion.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblDuracion);

		lblDuracionValor = new JLabel("");
		lblDuracionValor.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblDuracionValor);

	}

	/**
	 * Cargar datos de la publicacion(nombre,fecha,cantida de "Me gusta").
	 */

	public void cargarDatosPanelGeneralReproduccion() {
		this.lblNombreValor.setText(this.publicacion.getNombrePublicacion());
		String fecha = FechaUtilidades.formatearFechaAString(publicacion.getFechaSubida());
		this.lblFechaValor.setText(fecha);
		this.lblMGValor.setText(String.valueOf(this.publicacion.getCantidadMG()));
		this.lblDuracionValor.setText((Float.toString(this.publicacion.getDuracion())));

	}

}
