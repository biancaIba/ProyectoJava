package vista.edicion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import modelo.Publicacion;
import modelo.enums.EnumTipoFiltro;
import modelo.interfaces.IFiltrable;
import utilidades.TiempoUtilidades;

import java.awt.FlowLayout;
import java.awt.GridLayout;

public class PanelGeneral extends JPanel {
	private Publicacion publicacion;
	private JLabel lblDuracionActualValor;
	private JLabel lblNombrePublicacionValor;

	public PanelGeneral(Publicacion publicacion) {
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
		Float duracion = this.publicacion.getDuracion();
		this.lblDuracionActualValor.setText(Float.toString(duracion) + " segundos (" + TiempoUtilidades.duracionFormateada(duracion) + ")");
	}
	
	
}
