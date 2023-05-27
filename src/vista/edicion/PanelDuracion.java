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

public class PanelDuracion extends JPanel {
	IDurable publicacion;
	private JLabel lblInicioValor;
	private JLabel lblFinValor;
	private JLabel lblDuracionActualValor;
	private JLabel lblDuracionOriginalValor;
	
	public PanelDuracion(IDurable publicacion) {
		super();
		this.publicacion = publicacion;
		instanciarPanelDuracion();
		cargarDatosDuracion();
	}
	
	private void instanciarPanelDuracion() {
		Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border lineBorder = BorderFactory.createLineBorder(Color.GRAY, 1);
        Border compoundBorder = BorderFactory.createCompoundBorder(lineBorder, paddingBorder);
		setBorder(compoundBorder);
		setLayout(new GridLayout(0, 4, 15, 25));
		
		JLabel lblInicio = new JLabel("Inicio:");
		add(lblInicio);
		
		lblInicioValor = new JLabel("...Valor...");
		add(lblInicioValor);
		
		JButton btnAtrasarInicio = new JButton("<");
		btnAtrasarInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float inicioRelativoNuevo = publicacion.getInicio() - 1;
				try {
					publicacion.avanzar(inicioRelativoNuevo);
					cargarDatosDuracion();
				} catch (DuracionInvalidaExcepcion error) {
					JOptionPane.showMessageDialog(null,error.getMessage(),
							"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		add(btnAtrasarInicio);
		
		JButton btnAdelantarInicio = new JButton(">");
		btnAdelantarInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float inicioRelativoNuevo = publicacion.getInicio() + 1;
				try {
					publicacion.avanzar(inicioRelativoNuevo);
					cargarDatosDuracion();
				} catch (DuracionInvalidaExcepcion error) {
					JOptionPane.showMessageDialog(null,error.getMessage(),
							"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		add(btnAdelantarInicio);
		
		JLabel lblFin = new JLabel("Fin:");
		add(lblFin);
		
		lblFinValor = new JLabel("...Valor...");
		add(lblFinValor);
		
		JButton btnAtrasarFin = new JButton("<");
		btnAtrasarFin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float finRelativo = publicacion.getFinReproduccion() - 1 ;
				try {
					publicacion.detener(finRelativo);
					cargarDatosDuracion();
				} catch (DuracionInvalidaExcepcion error) {
					JOptionPane.showMessageDialog(null,error.getMessage(),
							"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		add(btnAtrasarFin);
		
		JButton btnAdelantarFin = new JButton(">");
		btnAdelantarFin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float finRelativo = publicacion.getFinReproduccion() + 1 ;
				try {
					publicacion.detener(finRelativo);
					cargarDatosDuracion();
				} catch (DuracionInvalidaExcepcion error) {
					JOptionPane.showMessageDialog(null,error.getMessage(),
							"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		add(btnAdelantarFin);
		
		JLabel lblDuracionActual = new JLabel("Duración actual:");
		add(lblDuracionActual);
		
		lblDuracionActualValor = new JLabel("...Valor...");
		add(lblDuracionActualValor);
		
		JLabel lblEmpty_1 = new JLabel("");
		add(lblEmpty_1);
		
		JLabel lblEmpty_2 = new JLabel("");
		add(lblEmpty_2);
		
		JLabel lblDuracionOriginal = new JLabel("Duración original:");
		add(lblDuracionOriginal);
		
		lblDuracionOriginalValor = new JLabel("...Valor...");
		add(lblDuracionOriginalValor);
	}
	
	private void cargarDatosDuracion() {
		this.lblInicioValor.setText(Float.toString(this.publicacion.getInicio()));
		this.lblFinValor.setText(Float.toString(this.publicacion.getFinReproduccion()));
		this.lblDuracionActualValor.setText(Float.toString(this.publicacion.getDuracion()));
		this.lblDuracionOriginalValor.setText(Float.toString(this.publicacion.getFin()));
	}
}
