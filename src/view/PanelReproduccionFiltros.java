package view;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Filtrable;

public class PanelReproduccionFiltros  extends JPanel  {

	private Filtrable publicacion;
	private JLabel lblFiltro;
	private JLabel lblFiltroValor;
	
	public PanelReproduccionFiltros(Filtrable publicacion) {
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
