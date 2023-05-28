package vista.reproduccion;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import modelo.interfaces.IDurable;

/**
 * Clase PanelReproduccionDuracion.
 * 
 * La clase PanelReproduccionDuracion es una clase que representa un panel para mostrar la duración de una publicación.
 */
public class PanelReproduccionDuracion  extends JPanel  {
	
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
	
	/** Etiqueta duracion. */
	private JLabel lblDuracion;
	
	/** Etiqueta duracion valor. */
	private JLabel lblDuracionValor;
	
	/** The progress bar. */
	private JProgressBar progressBar;
	
	 /**
     * Crea un nuevo objeto PanelReproduccionDuracion.
     *
     * @param publicacion : la publicación de la cual se mostrará la duración.
     */
	public PanelReproduccionDuracion(IDurable publicacion) {
		super();
		this.publicacion=publicacion;
		instanciarPanelDuracion();
		cargarPanelDuracion();
		
	}
	
	/**
     * Instancia y configura los componentes del panel de duración.
     * Este método configura un layout de rejilla (GridLayout) y crea y agrega las etiquetas correspondientes.
     */
	public void instanciarPanelDuracion() {
	
		setLayout(new GridLayout(3, 2, 0, 0));
		
		lblInicio = new JLabel("Inicio:");
		add(lblInicio);
		
		lblInicioValor = new JLabel("..Valor..");
		add(lblInicioValor);
		
		lblFin = new JLabel("Fin:");
		add(lblFin);
		
		lblFinValor = new JLabel("..Valor..");
		add(lblFinValor);
		
		lblDuracion = new JLabel("Duracion:");
		add(lblDuracion);
		
		lblDuracionValor = new JLabel("...Valor..");
		add(lblDuracionValor);
		
		add(lblDuracionValor);
	}
	
	/**
     * Carga la duración de la publicación en el panel.
     */
	public void cargarPanelDuracion() {
		lblInicioValor.setText(Float.toString(this.publicacion.getInicio()));
		lblFinValor.setText(Float.toString(this.publicacion.getFinReproduccion()));
		lblDuracionValor.setText(Float.toString(this.publicacion.getDuracion()));
	}
}
