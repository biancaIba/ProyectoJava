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

public class PanelFiltros extends JPanel {
	private IFiltrable publicacion;
	private JComboBox comboBox;
	
	public PanelFiltros(IFiltrable publicacion) {
		super();
		this.publicacion = publicacion;
		instanciarPanelFiltros();
		cargarDatosFiltros();
	}
	
	private void instanciarPanelFiltros() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		
		JLabel lblFiltro = new JLabel("Seleccione un filtro:");
		lblFiltro.setBounds(10, 66, 157, 14);
		lblFiltro.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblFiltro);
		comboBox = new JComboBox();
		add(comboBox);
	}
	
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
