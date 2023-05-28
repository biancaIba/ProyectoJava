package vista.edicion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import modelo.enums.EnumTipoFiltro;
import modelo.interfaces.IFiltrable;

/**
 * Clase PanelFiltros.
 * 
 * La clase PanelFiltros representa un panel que permite seleccionar un filtro
 * para una publicación.
 */

public class PanelFiltros extends JPanel {

	/** publicacion que implementa la interface filtrable. */
	private IFiltrable publicacion;

	/** El combo box. */
	private JComboBox<EnumTipoFiltro> comboBox;

	/**
	 * Crea una instancia de PanelFiltros con una publicación específica.
	 * 
	 * @param publicacion : la publicación a la que se le aplicará el filtro.
	 */
	public PanelFiltros(IFiltrable publicacion) {
		super();
		this.publicacion = publicacion;
		instanciarPanelFiltros();
		cargarDatosFiltros();
	}

	/**
	 * Instanciar panel filtros.
	 */
	private void instanciarPanelFiltros() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);

		JLabel lblFiltro = new JLabel("Seleccione un filtro:");
		lblFiltro.setBounds(10, 66, 157, 14);
		lblFiltro.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblFiltro);
		comboBox = new JComboBox<EnumTipoFiltro>();
		add(comboBox);
	}

	/**
	 * Cargar datos filtros.
	 */
	private void cargarDatosFiltros() {
		for (EnumTipoFiltro filtro : EnumTipoFiltro.values()) {
			comboBox.addItem(filtro);
		}
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnumTipoFiltro filtroAplicado = (EnumTipoFiltro) comboBox.getSelectedItem();
				publicacion.aplicarFiltro(filtroAplicado);
			}
		});
		comboBox.setBounds(201, 62, 143, 22);
		comboBox.setSelectedItem(publicacion.getFiltro());
	}

}
