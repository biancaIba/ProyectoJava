package view;

import javax.swing.JPanel;

import exception.SinDatosException;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;
import java.awt.FlowLayout;
import java.util.Random;
public class Histograma extends JPanel {
    private static final int MARGIN = 40; 
    private static final int AXIS_SPACE = 20; 
    private static final int LABEL_MARGIN = 10; 

    private int[] histogramData; 
    private String[] labels; 
    public Histograma() {
    	setBackground(Color.DARK_GRAY); 
    }

    public void setHistogramData(int[] data, String[] labels) throws SinDatosException{
        this.histogramData = data;
        this.labels = labels;
        repaint(); 
    }
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
            
            Color[] barColors = generarColoresAleatorios(histogramData.length);
            
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

    private int getMaxValue(int[] data) {
        int maxValue = Integer.MIN_VALUE;
        for (int value : data) {
            if (value > maxValue) {
                maxValue = value;
            }
        }
        return maxValue;
    }
    private Color[] generarColoresAleatorios(int cantidad) {
        Random random = new Random();
        Color[] colores = new Color[cantidad];

        for (int i = 0; i < cantidad; i++) {
            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);
            colores[i] = new Color(r, g, b);
        }

        return colores;
    }

}


