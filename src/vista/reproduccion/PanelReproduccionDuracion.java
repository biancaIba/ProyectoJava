package vista.reproduccion;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import modelo.interfaces.IDurable;
import javax.swing.SwingConstants;

/**
 * Clase PanelReproduccionDuracion.
 * 
 * La clase PanelReproduccionDuracion es una clase que representa un panel para
 * mostrar la duración de una publicación.
 */
public class PanelReproduccionDuracion extends JPanel {

	/** publicacion que implementa la interface durable. */
	private IDurable publicacion;

	/** Etiqueta inicio. */
	private JLabel lblInicio;

	/** Etiqueta inicio valor. */
	private JLabel lblInicioValor;

	/** Etiqueta fin. */
	private JLabel lblFin;

	/** Etiqueta fin valor. */
	private JLabel lblFinValor;

	/**
	 * Crea un nuevo objeto PanelReproduccionDuracion.
	 *
	 * @param publicacion : la publicación de la cual se mostrará la duración.
	 */
	public PanelReproduccionDuracion(IDurable publicacion) {
		super();
		this.publicacion = publicacion;
		instanciarPanelDuracion();
		cargarPanelDuracion();

	}

	/**
	 * Instancia y configura los componentes del panel de duración. Este método
	 * configura un layout de rejilla (GridLayout) y crea y agrega las etiquetas
	 * correspondientes.
	 */
	public void instanciarPanelDuracion() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new GridLayout(3, 2, 0, 0));

		lblInicio = new JLabel("Inicio:");
		lblInicio.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblInicio);

		lblInicioValor = new JLabel("..Valor..");
		lblInicioValor.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblInicioValor);

		lblFin = new JLabel("Fin:");
		lblFin.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblFin);

		lblFinValor = new JLabel("..Valor..");
		lblFinValor.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblFinValor);

	}

	/**
	 * Carga la duración de la publicación en el panel.
	 */
	public void cargarPanelDuracion() {
		lblInicioValor.setText(Float.toString(this.publicacion.getInicioReproduccion()));
		lblFinValor.setText(Float.toString(this.publicacion.getFinReproduccion()));
	}
}
