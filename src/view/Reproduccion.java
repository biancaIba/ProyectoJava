package view;
import model.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.swing.Timer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;

public class Reproduccion extends JPanel {
	
	private JPanel listaSeleccionadasPanel;
	private JLabel lblTiempoTotalValor;
	private JPanel panelInformacion;
	private JPanel panelLateral;
	private Set<Publicacion> publicacionesSeleccionadas;
	private double tiempoTotal;
	JProgressBar progressBar;
	
	public Reproduccion(Set<Publicacion> publicacionesSeleccionadas) {
		super();
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout(15, 0));
		this.publicacionesSeleccionadas = publicacionesSeleccionadas;
		
		panelLateral = instanciarListaReproduccion();	
		add(panelLateral, BorderLayout.WEST);
		
		panelInformacion = new JPanel();
		add(panelInformacion, BorderLayout.CENTER);
		panelInformacion.setLayout(new GridLayout(3, 1, 0, 0));
		
		cargarListaReproduccion(publicacionesSeleccionadas);
		cargarPanelDeInformacion(publicacionesSeleccionadas);
		cargarTiempoTotalInicial();
		renderizarTiempoTotal();
	}


	public JPanel instanciarListaReproduccion() {
		
		JPanel panelLateral = new JPanel();
		panelLateral.setBackground(Color.WHITE);
		panelLateral.setLayout(new BorderLayout());

		JLabel tituloLabel = new JLabel("Lista de reproducción");
		tituloLabel.setFont(new Font("Open Sans", Font.BOLD, 14));
		tituloLabel.setForeground(Color.WHITE);
		tituloLabel.setHorizontalAlignment(JLabel.CENTER);
		panelLateral.add(tituloLabel, BorderLayout.NORTH);
		panelLateral.setBackground(Color.GRAY);

		JPanel tiempoReproduccionPanel = new JPanel();
		tiempoReproduccionPanel.setBackground(Color.WHITE);
		tiempoReproduccionPanel.setLayout(new BorderLayout());

		JLabel lblTiempoTotal = new JLabel("Tiempo de reproducción total: ");
		lblTiempoTotal.setFont(new Font("Arial", Font.PLAIN, 12));
		tiempoReproduccionPanel.add(lblTiempoTotal, BorderLayout.WEST);

		lblTiempoTotalValor = new JLabel("00:00:00");
		lblTiempoTotalValor.setFont(new Font("Arial", Font.PLAIN, 12));
		tiempoReproduccionPanel.add(lblTiempoTotalValor, BorderLayout.CENTER);
		panelLateral.add(tiempoReproduccionPanel, BorderLayout.SOUTH);
		
		progressBar = new JProgressBar();
		tiempoReproduccionPanel.add(progressBar, BorderLayout.SOUTH);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		listaSeleccionadasPanel = new JPanel();
		listaSeleccionadasPanel.setBackground(Color.LIGHT_GRAY);
		listaSeleccionadasPanel.setLayout(new BoxLayout(listaSeleccionadasPanel, BoxLayout.Y_AXIS));

		scrollPane.setViewportView(listaSeleccionadasPanel);
		panelLateral.add(scrollPane, BorderLayout.CENTER);
		return panelLateral;
	
	}
	
	public void cargarListaReproduccion(Set<Publicacion> publicacionesSeleccionadas) {
		
		Iterator<Publicacion> iterador = publicacionesSeleccionadas.iterator();
		while (iterador.hasNext()) {
			Publicacion publicacion = iterador.next();
			JPanel itemPanel = new JPanel();
			itemPanel.setBackground(Color.WHITE);
	
			// restricciones del GridBagConstraints
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0; 
			gbc.gridy = GridBagConstraints.RELATIVE; 
			gbc.weightx = 1.0; // 
			gbc.insets = new Insets(5,5,5,5); 
	
			JLabel nombrePublicacion = new JLabel (publicacion.getNombrePublicacion());
			nombrePublicacion.setFont(new Font("Open Sans", Font.PLAIN, 15));
			nombrePublicacion.setBackground(Color.BLACK);
			itemPanel.add(nombrePublicacion);
			
			listaSeleccionadasPanel.add(itemPanel, gbc);	
			
		}
	}
	
	
	public void cargarPanelDeInformacion(Set<Publicacion> publicacionesSeleccionadas) {
		Iterator<Publicacion> iterador = publicacionesSeleccionadas.iterator();
		if (iterador.hasNext()) {
			Publicacion _publicacion = iterador.next();
			renderizarPanelesDeInformacion(_publicacion);
            
			Timer timer = new Timer(1000, new ActionListener() {
				float duracionParcial = 0;
				Publicacion publicacion = _publicacion;
				@Override
			    public void actionPerformed(ActionEvent e) {
					duracionParcial++;
					progressBar.setValue(progressBar.getValue() + 1); 
					disminuirTiempoTotal();
					renderizarTiempoTotal();
					if(duracionParcial == publicacion.getDuracion()) {
						if (iterador.hasNext()) {
			                panelInformacion.removeAll();
			                publicacion = iterador.next();
			                renderizarPanelesDeInformacion(publicacion);
			                panelInformacion.revalidate();
			                panelInformacion.repaint();
			                duracionParcial = 0;
			                
		            	} else {
		            		((Timer) e.getSource()).stop();
		            		JOptionPane.showMessageDialog(null, "La reproducción ha finalizado");
		            	}
					}
				}
			});
	        timer.start();
		}
	}
		

	public void  cargarTiempoTotalInicial() {
		tiempoTotal = publicacionesSeleccionadas.stream().mapToDouble(publicacion -> publicacion.getDuracion()).sum();
		progressBar.setMinimum(0);
        progressBar.setMaximum((int)tiempoTotal);
	}
	
	public void  disminuirTiempoTotal() {
		tiempoTotal-=1.0;
	}
	
	public void renderizarTiempoTotal() {
		int horas = (int) (tiempoTotal / 3600);
        int minutos = (int) ((tiempoTotal % 3600) / 60);
        int segundos = (int) (tiempoTotal % 60);
        String duracionFormateada = String.format("%02d:%02d:%02d", horas, minutos, segundos);
        lblTiempoTotalValor.setText(duracionFormateada);
	}
	
	public void renderizarPanelesDeInformacion(Publicacion publicacion) {
		if (publicacion.getTipoPublicacion() == EnumTipoPublicacion.AUDIO || publicacion.getTipoPublicacion() == EnumTipoPublicacion.VIDEO) {
            panelInformacion.add(new PanelReproduccionDuracion((Durable) publicacion));
        }
        if (publicacion.getTipoPublicacion() == EnumTipoPublicacion.IMAGEN || publicacion.getTipoPublicacion() == EnumTipoPublicacion.VIDEO) {
            panelInformacion.add(new PanelReproduccionFiltros((Filtrable) publicacion));
        }
	}
	
}
