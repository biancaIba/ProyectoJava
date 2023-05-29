package vista.edicion;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import modelo.Publicacion;
import utilidades.TiempoUtilidades;
import java.awt.GridLayout;

/**
 * Clase PanelGeneralEdicion.
 * 
 * Panel que muestra los datos de la publicacion que se esta configurando la
 * reproduccion.
 */
public class PanelGeneralEdicion extends JPanel {
	private Publicacion publicacion;
	private JLabel lblDuracionActualValor;
	private JLabel lblNombrePublicacionValor;

	public PanelGeneralEdicion(Publicacion publicacion) {
		super();
		this.publicacion = publicacion;
		instanciarPanelDuracionGeneral();
		cargarDatosNombrePublicacion();
		cargarDatosDuracion();
	}

	/**
	 * Instanciar panel duración general.
	 */
	private void instanciarPanelDuracionGeneral() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new GridLayout(2, 2, 0, 15));

		JLabel lblNombrePublicacion = new JLabel("Nombre de la publicacion: ");
		lblNombrePublicacion.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNombrePublicacion);

		lblNombrePublicacionValor = new JLabel("");
		add(lblNombrePublicacionValor);

		JLabel lblDuracionOriginal = new JLabel("Duración actual:");
		lblDuracionOriginal.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblDuracionOriginal);

		lblDuracionActualValor = new JLabel("");
		add(lblDuracionActualValor);

	}

	/**
	 * Cargar nombre de la publicacion.
	 */

	public void cargarDatosNombrePublicacion() {
		this.lblNombrePublicacionValor.setText(this.publicacion.getNombrePublicacion());
	}

	/**
	 * Cargar datos duración general.
	 */
	public void cargarDatosDuracion() {
		Float duracion = this.publicacion.calcularDuracion();
		this.lblDuracionActualValor.setText(
				Float.toString(duracion) + " segundos (" + TiempoUtilidades.duracionFormateada(duracion) + ")");
	}

}
