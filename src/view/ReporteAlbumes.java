package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
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

import model.Album;
import model.PerfilInstagram;
import reports.ReporteAlbum;
import utils.AssetsUtils;
import utils.DateUtils;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ReporteAlbumes extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final String FECHA_INICIO_DEFAULT = LocalDate.now().minusMonths(3)
			.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	private final String FECHA_FIN_DEFAULT = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	private static PerfilInstagram perfilInstagram;
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnFiltrar;
	private JLabel lblFechaInicio;
	private JLabel lblFechaFin;
	private JTextField txtFechaInicio;
	private JTextField txtFechaFin;


	public ReporteAlbumes(PerfilInstagram perfil) {

		perfilInstagram = perfil;
		LocalDate inicio = LocalDate.parse("2023-04-20"); // deberia ser lo q el usuario ingresa
		LocalDate fin = LocalDate.parse("2023-05-05"); // idem
		setTitle("Reporte de Publicaciones");
		setAlwaysOnTop(true);
		setSize(1200, 600);
		setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		perfilInstagram = PerfilInstagram.getInstance();
		renderizarTabla();
		cargarTabla(txtFechaInicio.getText(), txtFechaFin.getText());
	}

	private void renderizarTabla() {
		String[] columnNames = { "Nombre", "Cantidad de Publicaciones", "Cantidad de Comentarios",
				"Cantidad de subAlbumes" };
		// Crear un DefaultTableModel con los datos y nombres de columnas
		model = new DefaultTableModel(null, columnNames);
		// Crear un JTable con el DefaultTableModel
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
		btnFiltrar.setIcon(AssetsUtils.obtenerIcono("filtrar"));

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

	private void cargarTabla(String fechaInicioStr, String fechaFinStr) {
		try {
			LocalDate fechaInicio = DateUtils.formatearFecha(fechaInicioStr);
			LocalDate fechaFin = DateUtils.formatearFecha(fechaFinStr);
			if (!DateUtils.fechaEsMayorA(fechaInicio, fechaFin)) {
				throw new IllegalArgumentException();
			}
			
			perfilInstagram = PerfilInstagram.getInstance();

			List<ReporteAlbum> listadoDeAlbumes = perfilInstagram.listadoDeAlbumes(fechaInicio, fechaFin);
			model.setRowCount(0);
			for (ReporteAlbum album : listadoDeAlbumes) {
				List<Album> listaSubAlbumes = album.getListaSubAlbumes();
				model.addRow(new Object[] { album.getNombreAlbum(), album.getCantidadPublicaciones(),
						album.getCantidadComentarios(), album.getListaSubAlbumes() });

				for (Album subAlbum : listaSubAlbumes) {
					// Agregar una fila nuev para cada subalbum
					model.addRow(new Object[] { subAlbum.getNombreAlbum(), 0, 0, null });
				}

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
