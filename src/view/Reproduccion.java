package view;
import model.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;
import java.util.TimerTask;
import java.util.Timer;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import model.EnumTipoPublicacion;
import model.PerfilInstagram;
import model.Publicacion;

public class Reproduccion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static PerfilInstagram perfil;

	public Reproduccion(int opcion, Set<Publicacion> publicacionesSeleccionadas) {
		
		perfil = PerfilInstagram.getInstance();
		
		setResizable(false);
		setTitle("Reproducción");
		setAlwaysOnTop(true);
		setBackground(Color.LIGHT_GRAY);
		getContentPane().setBackground(Color.WHITE);
		setSize(1000, 600);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		{
			// Los criterios de ordenacion pueden modificarse por cualquier otro
			if (opcion == 1) {
				// ordenar lista por cantidad de MG
			} else if (opcion == 2) {
				// ordenar lista por cantidad de comentarios
			} else {
				// ordenar lista por Fechas
			}
			
			int duracionMostrarPublicacion = 5000; // se puso asi para probar
			// se reemplazaria con el tiempo de cada publi
			
			for (Publicacion p : publicacionesSeleccionadas) {
				
				// se deberia mostrar un texto con el nombre de la publicacion
				
				//JLabel nombrePublicacion = new JLabel(p.getNombrePublicacion());
				//nombrePublicacion.setVisible(false);
				
				// Configura un timer: 
				System.out.print("\n\n\n\n\n\n\n\n\n\n"); // limpia la consola
			    Timer timer = new Timer();
			    timer.schedule(new TimerTask() {
			        @Override
			        public void run() {
			        	//nombrePublicacion.setVisible(true);
			        	System.out.println(p.getNombrePublicacion());
			            timer.cancel();
			        }
			    }, duracionMostrarPublicacion);
			    
				// esto esta comentado porque depende de la implementacion de los metodos de avanzar/detener
			    // y de la utilizacion de instaceof (que en teoria viola los principios de la POO)
				/**EnumTipoPublicacion tipoPublicacion = p.getTipoPublicacion();
				if (tipoPublicacion == EnumTipoPublicacion.AUDIO) {
					Audio a = (Audio) p;
					//a.avanzar();
				} else if (tipoPublicacion == EnumTipoPublicacion.VIDEO) {
					Video v = (Video) p;
					//v.avanzar();
				} else {
					
				}*/
			    
			    //contentPanel.add(nombrePublicacion);
			}

		}
	
	}
		
		/**{
			JLabel lblSeleccionaPubli = new JLabel("Selecciona las publicaciones: ");
			contentPanel.add(lblSeleccionaPubli);
		}
		{
			DefaultListModel<String> modelo = new DefaultListModel<>();
			try {
				Set<String> nombresP = perfil.getNombresPublicaciones();
				for (String nombre : nombresP) {
		            modelo.addElement(nombre);
		        }
				JList<String> listaP = new JList<>(modelo);
				listaP.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				contentPanel.add(listaP);
				contentPanel.add(new JScrollPane(listaP));
			} catch (SinDatosException e) {
				JOptionPane.showMessageDialog(null, "No hay datos para filtrar.");
				dispose();
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.DARK_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnReproducir = new JButton("Reproducir");
				btnReproducir.setActionCommand("Reproducir");
				buttonPane.add(btnReproducir);
			}
			{
				JButton btnSalir = new JButton("Salir");
				btnSalir.setActionCommand("Salir");
				buttonPane.add(btnSalir);
				getRootPane().setDefaultButton(btnSalir);
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
			}
		}**/

}
