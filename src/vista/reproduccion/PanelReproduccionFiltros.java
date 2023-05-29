package vista.reproduccion;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import modelo.interfaces.IFiltrable;
import javax.swing.SwingConstants;

/**
 * Clase PanelReproduccionFiltros.
 * 
 * Panel que muestra los filtros aplicados a una publicación.
 */
public class PanelReproduccionFiltros extends JPanel {

	/** Publicación que implementa la interfaz IFiltrable. */
	private IFiltrable publicacion;

	/** Etiqueta filtro. */
	private JLabel lblFiltro;

	/** Etiqueta filtro valor. */
	private JLabel lblFiltroValor;

	/**
	 * Constructor de la clase PanelReproduccionFiltros.
	 * 
	 * @param publicacion La publicación a la que se le aplican los filtros.
	 */
	public PanelReproduccionFiltros(IFiltrable publicacion) {
		super();
		this.publicacion = publicacion;
		instanciarPanelfiltros();
		cargarPanelFiltros();
	}

	/**
	 * Instancia los componentes del panel de filtros.
	 */
	public void instanciarPanelfiltros() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new GridLayout(0, 2, 0, 0));

		lblFiltro = new JLabel("Filtro aplicado:");
		lblFiltro.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblFiltro);

		lblFiltroValor = new JLabel("..Valor...");
		lblFiltroValor.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblFiltroValor);

		add(lblFiltroValor);
	}

	/**
	 * Carga los filtros aplicados en el panel.
	 */
	public void cargarPanelFiltros() {
		lblFiltroValor.setText(publicacion.getFiltro().toString());
	}
}
