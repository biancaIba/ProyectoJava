package vista.estadisticas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;
import excepciones.SinDatosExcepcion;
import utilidades.RandomUtilidades;

/**
 * Clase Grafito deTorta
 * 
 * La clase GraficoTorta representa un panel que muestra un gráfico de torta.
 * Recibe una lista de valores numéricos y una lista de etiquetas para generar
 * el gráfico.
 */
public class GraficoTorta extends JPanel {
	private int[] values;
	private Color[] colors;
	private List<String> labels;

	/**
	 * Crea una instancia del panel de gráfico de torta con los valores y etiquetas
	 * especificados.
	 * 
	 * @param values : la lista de valores numéricos para el gráfico.
	 * @param labels : la lista de etiquetas para el gráfico.
	 * @throws SinDatosExcepcion        : si la lista de valores está vacía o nula.
	 * @throws IllegalArgumentException : si la cantidad de valores, colores y
	 *                                  etiquetas no coincide.
	 */
	public GraficoTorta(List<Integer> values, List<String> labels) throws SinDatosExcepcion {
		if (values == null || values.size() == 0) {
			throw new SinDatosExcepcion("Sin datos");
		} else {
			this.values = values.stream().mapToInt(Integer::intValue).toArray();
			this.colors = RandomUtilidades.generarColoresAleatorios(values.size());
			this.labels = labels;

			if (values.size() != colors.length || values.size() != labels.size()) {
				throw new IllegalArgumentException("La cantidad de valores, colores y etiquetas no coincide");
			}
			setBackground(Color.DARK_GRAY);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int total = 0;
		for (int value : values) {
			total += value;
		}

		int centerX = getWidth() / 2;
		int centerY = getHeight() / 2;
		int radius = Math.min(centerX, centerY) - 100;
		int startAngle = 0;

		for (int i = 0; i < values.length; i++) {
			int arcAngle = (int) Math.round(values[i] / (double) total * 360);

			g.setColor(colors[i]);
			g.fillArc(centerX - radius, centerY - radius, radius * 2, radius * 2, startAngle, arcAngle);

			double angle = Math.toRadians(startAngle + arcAngle / 2.0);
			int labelX = (int) (centerX + radius * Math.cos(angle));
			int labelY = (int) (centerY - radius * Math.sin(angle));

			g.setColor(colors[i]);
			g.fillArc(centerX - radius, centerY - radius, radius * 2, radius * 2, startAngle, arcAngle);

			startAngle += arcAngle;
		}

		int referenceX = centerX + radius + 20;
		int referenceY = centerY - radius;
		int lineHeight = 20;

		for (int i = 0; i < labels.size(); i++) {
			g.setColor(colors[i]);
			g.fillRect(referenceX, referenceY + i * lineHeight, 10, 10);

			g.setColor(Color.WHITE);
			String label = labels.get(i);
			int percentage = (int) Math.round(values[i] / (double) total * 100);
			String text = "#" + label + " (" + percentage + "%)";
			Font font = new Font("Arial", Font.BOLD, 14);
			g.setFont(font);
			g.drawString(text, referenceX + 20, referenceY + i * lineHeight + 10);
		}

		String titulo = "Porcentaje de veces que se repiten las etiquetas";
		String subtitulo = "en las publicaciones";
		g.setFont(new Font("Arial", Font.BOLD, 16));

		int y = centerY - radius - g.getFontMetrics().getHeight() - 30;
		String[] lineasTitulo = titulo.split("\\r?\\n");
		for (String linea : lineasTitulo) {
			int x = centerX - g.getFontMetrics().stringWidth(linea) / 2;
			g.drawString(linea, x, y);
			y += g.getFontMetrics().getHeight();
		}

	}

}
