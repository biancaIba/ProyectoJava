package vista.reportes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import modelo.Album;
import modelo.PerfilInstagram;
import modelo.Publicacion;
import modelo.reportes.ReportePublicacion;
import utilidades.IconosUtilidades;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;



/**
 * Clase ReportePublicaciones.
 * 
 * La clase ReportePublicaciones representa una ventana de diálogo que muestra una tabla ordenada por tipo y cantidad de "me gusta" y un informe de las publicaciones de un perfil de Instagram.
 */
public class ReportePublicaciones extends JDialog {

	/** El contentPanel. */
	private final JPanel contentPanel = new JPanel();
    
    /** El perfil de Instagram. */
    private static PerfilInstagram perfilInstagram;
    
    /** El modelo. */
    private DefaultTableModel model;
    
    /** La tabla. */
    private JTable table;
    
    /** El panel de desplazamiento. */
    private JScrollPane scrollPane;
    
    /** El botón de actualización. */
    private JButton btnNewButton;
    
    /** La etiqueta para videos. */
    private JLabel lblVideo;
    
    /** La etiqueta para audios. */
    private JLabel lblAudio;
    
    /** La etiqueta para imágenes. */
    private JLabel lblImagen;
    
    /** La etiqueta para el promedio de Me gusta. */
    private JLabel lblPromedioMG;
    
    /** La etiqueta para el promedio de cantidad de publicaciones. */
    private JLabel lblPromedioCantPub;
    
    /** La etiqueta para el promedio de Me gusta en videos. */
    private JLabel lblPromMGVideo;
    
    /** La etiqueta para la cantidad de publicaciones de videos. */
    private JLabel lblCantPubliVideo;
    
    /** La etiqueta para el promedio de Me gusta en audios. */
    private JLabel lblPromMGAudio;
    
    /** La etiqueta para el promedio de Me gusta en imágenes. */
    private JLabel lblPromMGImagen;
    
    /** La etiqueta para la cantidad de publicaciones de audios. */
    private JLabel lblCantPubliAudio;
    
    /** La etiqueta para la cantidad de publicaciones de imágenes. */
    private JLabel lblCantPubliImagen;
    
    /** El panel de resumen. */
    private JPanel resumenPanel;
    
    /** La etiqueta. */
    private JLabel label;

    /**
     * Crea una nueva instancia de ReportePublicaciones.
     *
     * @param perfil : el perfil de Instagram.
     */
    public ReportePublicaciones(PerfilInstagram perfil) {
    	perfilInstagram = perfil;
        setTitle("Reporte de Publicaciones");
        setAlwaysOnTop(true);
        setSize(1200,600);
        setBackground(Color.LIGHT_GRAY);
        contentPanel.setBorder(null);
        getContentPane().add(contentPanel, BorderLayout.CENTER);
		renderizarTabla();
		renderizarResumen();
	    cargarDatos();
	}
    
    /**
     * Renderizar tabla.
     */
    private void renderizarTabla() {
    	String[] columnNames = { "Nombre", "Tipo de publicacion", "Cantidad de Me gusta","Fecha de subida","Albumes asociados" };
        model = new DefaultTableModel(null, columnNames);
        table = new JTable(model);
        table.setFont(new Font("Tahoma", Font.PLAIN, 11));
        table.setBackground(new Color(186, 189, 182));
        table.setEnabled(false);
        table.setRowSelectionAllowed(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setSize(600,400);
        
		scrollPane = new JScrollPane(table);
		scrollPane.setLocation(5, 5);
		scrollPane.setSize(1169,390);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		btnNewButton = new JButton("Actualizar datos");
        btnNewButton.setIcon(IconosUtilidades.obtenerIcono("reload"));
        
        btnNewButton.setBounds(940, 480, 196, 23);
		contentPanel.setLayout(null);
		contentPanel.add(scrollPane);
		contentPanel.add(btnNewButton);
		contentPanel.setBackground(Color.LIGHT_GRAY);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarDatos();
			}
	    });
    }
    
    /**
     * Renderizar resumen.
     * Este método crea y configura los componentes visuales del resumen.
     */
    private void renderizarResumen() {
    	resumenPanel = new JPanel();
    	resumenPanel.setBorder(null);
		resumenPanel.setBounds(10, 433, 875, 117);
		lblVideo = new JLabel("Video");
		lblVideo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblVideo.setIcon(IconosUtilidades.obtenerIcono("video"));
		lblAudio = new JLabel("Audio");
		lblAudio.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAudio.setIcon(IconosUtilidades.obtenerIcono("audio"));
		lblImagen = new JLabel("Imagen");
		lblImagen.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblImagen.setIcon(IconosUtilidades.obtenerIcono("image"));
		lblPromMGVideo = new JLabel("-");
		lblPromMGVideo.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblPromMGAudio = new JLabel("-");
		lblPromMGAudio.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblPromMGImagen = new JLabel("-");
		lblPromMGImagen.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblCantPubliImagen = new JLabel("-");
		lblCantPubliImagen.setFont(new Font("Tahoma", Font.ITALIC, 11));
		resumenPanel.setLayout(new GridLayout(0, 4, 0, 0));
		resumenPanel.setBackground(Color.LIGHT_GRAY);
		
		label = new JLabel("");
		label.setForeground(new Color(128, 128, 128));
		label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		resumenPanel.add(label);
		resumenPanel.add(lblVideo);
		resumenPanel.add(lblAudio);
		resumenPanel.add(lblImagen);
		lblPromedioMG = new JLabel("Promedio de Me gusta:");
		lblPromedioMG.setFont(new Font("Tahoma", Font.BOLD, 11));
		resumenPanel.add(lblPromedioMG);
		resumenPanel.add(lblPromMGVideo);
		resumenPanel.add(lblPromMGAudio);
		resumenPanel.add(lblPromMGImagen);
		lblPromedioCantPub = new JLabel("Cantidad de publicaciones:");
		lblPromedioCantPub.setFont(new Font("Tahoma", Font.BOLD, 11));
		resumenPanel.add(lblPromedioCantPub);
		lblPromedioCantPub.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantPubliVideo = new JLabel("-");
		lblCantPubliVideo.setFont(new Font("Tahoma", Font.ITALIC, 11));
		resumenPanel.add(lblCantPubliVideo);
		lblCantPubliAudio = new JLabel("-");
		lblCantPubliAudio.setFont(new Font("Tahoma", Font.ITALIC, 11));
		resumenPanel.add(lblCantPubliAudio);
		resumenPanel.add(lblCantPubliImagen);
		
		contentPanel.add(resumenPanel);
		
		JSeparator separatorIzq = new JSeparator();
		separatorIzq.setForeground(new Color(128, 128, 128));
		separatorIzq.setBounds(10, 420, 370, 2);
		contentPanel.add(separatorIzq);
		
		JSeparator separatorDer = new JSeparator();
		separatorDer.setForeground(new Color(128, 128, 128));
		separatorDer.setBounds(515, 420, 370, 2);
		contentPanel.add(separatorDer);
		
		JLabel lblResumen = new JLabel("Resumen");
		lblResumen.setForeground(new Color(128, 128, 128));
		lblResumen.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblResumen.setBounds(420, 412, 63, 14);
		contentPanel.add(lblResumen);
    }
    
    /**
     * Carga datos.
     * Este método invoca los métodos para cargar la tabla y el resumen.
     */
    private void cargarDatos() {
    	cargarTabla();
    	cargarResumen();
    }
    
    /**
     * Carga tabla.
     * Obtiene las publicaciones ordenadas por Me gusta y las muestra en la tabla.
     */
    public void cargarTabla() {
    	Map<String,List<Publicacion>> listaporMG = perfilInstagram.ordenarPublicacionesPorMg();
		model.setRowCount(0);
		for(Map.Entry<String, List<Publicacion>> entry: listaporMG.entrySet()) {
			List<Publicacion> publicaciones=entry.getValue();
			for(Publicacion publicacion  : publicaciones ) {
				String albumesStr = new String();
				for (Album album : publicacion.getListaAlbumesPertenece() ) {
					albumesStr+=album.getNombreAlbum()+", ";
				}
				model.addRow(new Object[] {publicacion.getNombrePublicacion(), publicacion.getTipoPublicacion(), publicacion.getCantidadMG(),publicacion.getFechaSubida(),albumesStr});
			}
		}
    }
    
    /**
     * Carga resumen.
     * Obtiene los reportes de cantidad y promedio de Me gusta por tipo de publicación
     * y actualiza las etiquetas correspondientes en el resumen.
     */
    private void cargarResumen() {
    	List<ReportePublicacion> reportes = perfilInstagram.cantidadYpromedioDeMg();
		for (ReportePublicacion reporte : reportes) {
			
			switch (reporte.getTipoPublicacion().toLowerCase()) {
				case "video": 
					lblPromMGVideo.setText(Float.toString(reporte.getPromedio()));
					lblPromMGVideo.setIcon(IconosUtilidades.obtenerIcono("like"));
					lblCantPubliVideo.setText(Integer.toString(reporte.getCantidadPublicaciones()));
					lblCantPubliVideo.setIcon(IconosUtilidades.obtenerIcono("post"));
					break;
				case "audio": 
					lblPromMGAudio.setText(Float.toString(reporte.getPromedio()));
					lblPromMGAudio.setIcon(IconosUtilidades.obtenerIcono("like"));
					lblCantPubliAudio.setText(Integer.toString(reporte.getCantidadPublicaciones()));
					lblCantPubliAudio.setIcon(IconosUtilidades.obtenerIcono("post"));
					break;
				case "imagen": 
					lblPromMGImagen.setText(Float.toString(reporte.getPromedio()));
					lblPromMGImagen.setIcon(IconosUtilidades.obtenerIcono("like"));
					lblCantPubliImagen.setText(Integer.toString(reporte.getCantidadPublicaciones()));
					lblCantPubliImagen.setIcon(IconosUtilidades.obtenerIcono("post"));
					break;
			}	
		}
    }
    
}
	
