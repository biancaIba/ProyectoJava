package vista.reproduccion;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import modelo.interfaces.IFiltrable;

/**
 * Clase PanelReproduccionFiltros.
 * 
 * Panel que muestra los filtros aplicados a una publicación.
 */
public class PanelReproduccionFiltros  extends JPanel  {

	/** Publicación que implementa la interfaz IFiltrable. */
	private IFiltrable publicacion;
	
	/**Etiqueta filtro. */
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
		
		setLayout(new GridLayout(0, 2, 0, 0));
		
		lblFiltro = new JLabel("Filtro aplicado:");
		add(lblFiltro);
		
		lblFiltroValor = new JLabel("..Valor...");
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
