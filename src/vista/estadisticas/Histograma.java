package vista.estadisticas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import excepciones.SinDatosExcepcion;
import utilidades.RandomUtilidades;

/**
 * Clase Histograma.
 *
 * La clase Histograma representa un panel que muestra un histograma gráfico.
 */
public class Histograma extends JPanel {

	/** Constante que indica el margen del histograma. */
	private static final int MARGIN = 40;

	/** Constante que indica el espacio del eje en el histograma. */
	private static final int AXIS_SPACE = 20;

	/** Constante que indica el margen de las etiquetas en el histograma. */
	private static final int LABEL_MARGIN = 10;

	/** Datos del histograma. */
	private int[] histogramData;

	/** Etiquetas del histograma. */
	private String[] labels;

	/**
	 * Crea una nueva instancia de la clase Histograma.
	 */
	public Histograma() {
		setBackground(Color.DARK_GRAY);
	}

	/**
	 * Establece los datos del histograma.
	 * 
	 * @param data   : los datos del histograma.
	 * @param labels : las etiquetas del histograma.
	 * @throws SinDatosExcepcion : si no se proporcionan datos para el histograma.
	 */
	public void setHistogramData(int[] data, String[] labels) throws SinDatosExcepcion {
		if (data == null || data.length == 0) {
			throw new SinDatosExcepcion("Sin datos");
		} else {
			this.histogramData = data;
			this.labels = labels;
			repaint();
		}
	}

	/**
	 * Método que se encarga de dibujar el componente.
	 * 
	 * @param g : el objeto Graphics utilizado para dibujar.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.WHITE);
		g.drawLine(MARGIN + AXIS_SPACE, getHeight() - MARGIN, MARGIN + AXIS_SPACE, MARGIN);
		g.drawLine(MARGIN, getHeight() - MARGIN - AXIS_SPACE, getWidth() - MARGIN, getHeight() - MARGIN - AXIS_SPACE);

		g.setFont(new Font("Arial", Font.PLAIN, 14));
		g.drawString("Cantidad de Me gusta", MARGIN - LABEL_MARGIN - 10, MARGIN - AXIS_SPACE);

		g.setFont(new Font("Arial", Font.PLAIN, 14));
		g.drawString("Tipo de Publicación", getWidth() / 2 - 60, getHeight() - MARGIN + AXIS_SPACE + LABEL_MARGIN + 5);

		if (histogramData != null && labels != null && histogramData.length == labels.length) {
			int barWidth = (getWidth() - 2 * MARGIN - 2 * AXIS_SPACE) / histogramData.length;
			int maxBarHeight = getHeight() - 2 * MARGIN - 2 * AXIS_SPACE;

			int x = MARGIN + AXIS_SPACE;

			Color[] barColors = RandomUtilidades.generarColoresAleatorios(histogramData.length);

			for (int i = 0; i < histogramData.length; i++) {
				int barHeight = histogramData[i] * maxBarHeight / getMaxValue(histogramData);
				int y = getHeight() - MARGIN - AXIS_SPACE - barHeight;

				g.setColor(barColors[i % barColors.length]);
				g.fillRect(x, y, barWidth, barHeight);

				String label = labels[i];
				int labelX = x + barWidth / 2 - g.getFontMetrics().stringWidth(label) / 2;
				int labelY = y - 5;
				g.setColor(Color.WHITE);
				g.drawString(label, labelX, labelY);

				String value = String.valueOf(histogramData[i]);
				int valueX = x + barWidth / 2 - g.getFontMetrics().stringWidth(value) / 2;
				int valueY = y + barHeight + 12;
				g.setColor(Color.WHITE);
				g.drawString(value, valueX, valueY);

				x += barWidth;
			}
		}
	}

	/**
	 * Obtiene el valor máximo de un conjunto de datos.
	 * 
	 * @param data : el conjunto de datos.
	 * @return el valor máximo.
	 */

	private int getMaxValue(int[] data) {
		int maxValue = Integer.MIN_VALUE;
		for (int value : data) {
			if (value > maxValue) {
				maxValue = value;
			}
		}
		return maxValue;
	}

}
