package vista.edicion;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import excepciones.DuracionInvalidaExcepcion;
import modelo.interfaces.IDurable;

/**
 * Clase PanelDuracion.
 * 
 * La clase PanelDuracion representa un panel de edición para la duración de una
 * publicación. Proporciona opciones para ajustar el inicio y el fin de
 * reproducción de la publicación.
 */

public class PanelDuracion extends JPanel {

	/** La publicación asociada al panel de duración. */
	IDurable publicacion;

	/** Etiqueta para mostrar el valor de inicio. */
	private JLabel lblInicioValor;

	/** Etiqueta para mostrar el valor de fin. */
	private JLabel lblFinValor;

	/** Etiqueta para mostrar el valor de duración original. */
	private JLabel lblDuracionOriginalValor;

	/**
	 * Crea una instancia del panel de duración para una publicación dada.
	 *
	 * @param publicacion : la publicación asociada al panel de duración.
	 */
	public PanelDuracion(IDurable publicacion, Runnable onChangeDuration) {
		super();
		this.publicacion = publicacion;
		instanciarPanelDuracion(onChangeDuration);
		cargarDatosDuracion();
	}

	/**
	 * Configura y crea los elementos del panel de duración.
	 */
	private void instanciarPanelDuracion(Runnable onChangeDuracion) {
		Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		Border lineBorder = BorderFactory.createLineBorder(Color.GRAY, 1);
		Border compoundBorder = BorderFactory.createCompoundBorder(lineBorder, paddingBorder);
		setBorder(compoundBorder);
		setLayout(new GridLayout(0, 4, 15, 25));

		JLabel lblInicio = new JLabel("Inicio:");
		add(lblInicio);

		lblInicioValor = new JLabel("");
		add(lblInicioValor);

		JButton btnAtrasarInicio = new JButton("<");
		btnAtrasarInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float inicioRelativoNuevo = publicacion.getInicioReproduccion() - 1;
				try {
					publicacion.avanzar(inicioRelativoNuevo);
					onChangeDuracion.run();
					cargarDatosDuracion();
				} catch (DuracionInvalidaExcepcion error) {
					JOptionPane.showMessageDialog(null, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		add(btnAtrasarInicio);

		JButton btnAdelantarInicio = new JButton(">");
		btnAdelantarInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float inicioRelativoNuevo = publicacion.getInicioReproduccion() + 1;
				try {
					publicacion.avanzar(inicioRelativoNuevo);
					onChangeDuracion.run();
					cargarDatosDuracion();
				} catch (DuracionInvalidaExcepcion error) {
					JOptionPane.showMessageDialog(null, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		add(btnAdelantarInicio);

		JLabel lblFin = new JLabel("Fin:");
		add(lblFin);

		lblFinValor = new JLabel("");
		add(lblFinValor);

		JButton btnAtrasarFin = new JButton("<");
		btnAtrasarFin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float finRelativo = publicacion.getFinReproduccion() - 1;
				try {
					publicacion.detener(finRelativo);
					onChangeDuracion.run();
					cargarDatosDuracion();
				} catch (DuracionInvalidaExcepcion error) {
					JOptionPane.showMessageDialog(null, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		add(btnAtrasarFin);

		JButton btnAdelantarFin = new JButton(">");
		btnAdelantarFin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float finRelativo = publicacion.getFinReproduccion() + 1;
				try {
					publicacion.detener(finRelativo);
					onChangeDuracion.run();
					cargarDatosDuracion();
				} catch (DuracionInvalidaExcepcion error) {
					JOptionPane.showMessageDialog(null, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		add(btnAdelantarFin);

		JLabel lblDuracionOriginal = new JLabel("Duración original:");
		add(lblDuracionOriginal);

		lblDuracionOriginalValor = new JLabel("");
		add(lblDuracionOriginalValor);
	}

	/**
	 * Carga los datos de duración actual en las etiquetas correspondientes.
	 */
	private void cargarDatosDuracion() {
		this.lblInicioValor.setText(Float.toString(this.publicacion.getInicioReproduccion()));
		this.lblFinValor.setText(Float.toString(this.publicacion.getFinReproduccion()));
		this.lblDuracionOriginalValor.setText(Float.toString(this.publicacion.getFinOriginal()));
	}
}
