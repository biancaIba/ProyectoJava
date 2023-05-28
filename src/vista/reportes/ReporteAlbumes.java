package vista.reportes;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import modelo.Album;
import modelo.PerfilInstagram;
import modelo.reportes.ReporteAlbum;
import utilidades.FechaUtilidades;
import utilidades.IconosUtilidades;

/**
 * Clase ReporteAlbumes.
 * 
 * La clase ReporteAlbumes representa una ventana de diálogo que muestra una tabla de los albumes y de las publicaciones de un perfil de Instagram que estan dentro de albumes filtradas por fecha.
 */
public class ReporteAlbumes extends JDialog {

	   /** El serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** El panel de contenido. */
    private final JPanel contentPanel = new JPanel();

    /** Fecha de inicio predeterminada. */
    private final String FECHA_INICIO_DEFAULT = LocalDate.now().minusMonths(3).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    /** Fecha de fin predeterminada. */
    private final String FECHA_FIN_DEFAULT = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    /** El perfil de Instagram. */
    private static PerfilInstagram perfilInstagram;

    /** El modelo. */
    private DefaultTableModel model;

    /** La tabla. */
    private JTable table;

    /** El panel de desplazamiento. */
    private JScrollPane scrollPane;

    /** El botón Filtrar. */
    private JButton btnFiltrar;

    /** La etiqueta Fecha de inicio. */
    private JLabel lblFechaInicio;

    /** La etiqueta Fecha de fin. */
    private JLabel lblFechaFin;

    /** El campo de texto Fecha de inicio. */
    private JTextField txtFechaInicio;

    /** El campo de texto Fecha de fin. */
    private JTextField txtFechaFin;

    /**
     * Crea una nueva instancia de ReporteAlbumes.
     *
     * @param perfil : el perfil de Instagram.
     */
	public ReporteAlbumes(PerfilInstagram perfil) {

		perfilInstagram = perfil;
		LocalDate inicio = LocalDate.parse("2023-04-20"); 
		LocalDate fin = LocalDate.parse("2023-05-05"); 
		setTitle("Reporte de albumes");
		setAlwaysOnTop(true);
		setSize(1200, 600);
		setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		renderizarTabla();
		cargarTabla(txtFechaInicio.getText(), txtFechaFin.getText());
	}

	/**
     * Renderiza la tabla.
     */
	private void renderizarTabla() {
		String[] columnNames = { "Nombre", "Cantidad de Publicaciones", "Cantidad de Comentarios","Subálbumes asociados"};
		model = new DefaultTableModel(null, columnNames);
		table = new JTable(model);
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		table.setBackground(new Color(186, 189, 182));
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setSize(600, 400);

		scrollPane = new JScrollPane(table);
		scrollPane.setLocation(5, 5);
		scrollPane.setSize(1169, 390);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setIcon(IconosUtilidades.obtenerIcono("filtrar"));

		btnFiltrar.setBounds(721, 410, 196, 23);
		contentPanel.setLayout(null);
		contentPanel.add(scrollPane);
		contentPanel.add(btnFiltrar);
		contentPanel.setBackground(Color.LIGHT_GRAY);

		lblFechaInicio = new JLabel("Fecha de inicio (dd/MM/yyyy):");
		lblFechaInicio.setBounds(10, 410, 200, 20);
		contentPanel.add(lblFechaInicio);

		txtFechaInicio = new JTextField(FECHA_INICIO_DEFAULT);
		txtFechaInicio.setBounds(220, 410, 100, 20);
		contentPanel.add(txtFechaInicio);

		lblFechaFin = new JLabel("Fecha de finalización (dd/MM/yyyy):");
		lblFechaFin.setBounds(330, 410, 250, 20);
		contentPanel.add(lblFechaFin);

		txtFechaFin = new JTextField(FECHA_FIN_DEFAULT);
		txtFechaFin.setBounds(590, 410, 100, 20);
		contentPanel.add(txtFechaFin);

		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla(txtFechaInicio.getText(), txtFechaFin.getText());
			}
		});
	}

	/**
     * Carga los datos en la tabla.
     *
     * @param fechaInicioStr : la fecha de inicio en formato de cadena.
     * @param fechaFinStr : la fecha de fin en formato de cadena.
     */
	private void cargarTabla(String fechaInicioStr, String fechaFinStr) {
		try {
			LocalDate fechaInicio = FechaUtilidades.formatearFecha(fechaInicioStr);
			LocalDate fechaFin = FechaUtilidades.formatearFecha(fechaFinStr);
			if (!FechaUtilidades.fechaEsMayorA(fechaInicio, fechaFin)) {
				throw new IllegalArgumentException();
			}

			List<ReporteAlbum> listadoDeAlbumes = perfilInstagram.listadoDeAlbumes(fechaInicio, fechaFin);
			model.setRowCount(0);
			for (ReporteAlbum album : listadoDeAlbumes) {
				String subAlbumesStr = "";
				List<Album> listaSubAlbumes = album.getListaSubAlbumes();
				for (Album subAlbum : listaSubAlbumes ) {
					subAlbumesStr+=subAlbum.getNombreAlbum()+", ";
				}
				model.addRow(new Object[] { album.getNombreAlbum(), album.getCantidadPublicaciones(),
						album.getCantidadComentarios(), subAlbumesStr });

			}
		} catch (DateTimeParseException e) {
			JOptionPane.showMessageDialog(null, "El formato de la fecha es incorrecto");
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, "La fecha de inicio debe ser mayor a la fecha de fin");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error. Inténtelo nuevamente.");
		}
	}
}
