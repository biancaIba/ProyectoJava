package vista.reproduccion;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import modelo.interfaces.IDurable;

public class PanelReproduccionDuracion  extends JPanel  {
	
	private IDurable publicacion;
	private JLabel lblInicio;
	private JLabel lblInicioValor;
	private JLabel lblFin;
	private JLabel lblFinValor;
	private JLabel lblDuracion;
	private JLabel lblDuracionValor;
	private JProgressBar progressBar;
	
	public PanelReproduccionDuracion(IDurable publicacion) {
		super();
		this.publicacion=publicacion;
		instanciarPanelDuracion();
		cargarPanelDuracion();
		
	}
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
	public void cargarPanelDuracion() {
		lblInicioValor.setText(Float.toString(this.publicacion.getInicio()));
		lblFinValor.setText(Float.toString(this.publicacion.getFinReproduccion()));
		lblDuracionValor.setText(Float.toString(this.publicacion.getDuracion()));
	}
}
