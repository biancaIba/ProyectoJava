package vista.reproduccion;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import modelo.interfaces.IFiltrable;

public class PanelReproduccionFiltros  extends JPanel  {

	private IFiltrable publicacion;
	private JLabel lblFiltro;
	private JLabel lblFiltroValor;
	
	public PanelReproduccionFiltros(IFiltrable publicacion) {
		super();
		this.publicacion = publicacion;
		instanciarPanelfiltros();
		cargarPanelFiltros();
	}
	public void instanciarPanelfiltros() {
		
		setLayout(new GridLayout(0, 2, 0, 0));
		
		lblFiltro = new JLabel("Filtro aplicado:");
		add(lblFiltro);
		
		lblFiltroValor = new JLabel("..Valor...");
		add(lblFiltroValor);
		
		add(lblFiltroValor);
	}
	public void cargarPanelFiltros() {
		lblFiltroValor.setText(publicacion.getFiltro().toString());
	}
}
