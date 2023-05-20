package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import java.util.List;
import java.util.Random;

public class GraficoTorta extends JPanel {
    private int[] values;
    private Color[] colors;
    private List<String> labels;
    
    	
    public GraficoTorta(List<Integer> values, List<String> labels) {
        this.values = values.stream().mapToInt(Integer::intValue).toArray();
        this.colors = generarColoresAleatorios(values.size());
        this.labels = labels;

        if (values.size() != colors.length || values.size() != labels.size()) {
            throw new IllegalArgumentException("La cantidad de valores, colores y etiquetas no coincide");
        }
        setBackground(Color.DARK_GRAY); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int total = 0;
        for (int value : values) {
            total += value;
        }

        // Mostrar gráfico
        int centerX = getWidth() / 2; // Coordenada x del centro del panel
        int centerY = getHeight() / 2; // Coordenada y del centro del panel
        int radius = Math.min(centerX, centerY) - 100; // Reducir el valor del radio para achicar el gráfico
        int startAngle = 0;

        for (int i = 0; i < values.length; i++) {
            int arcAngle = (int) Math.round(values[i] / (double) total * 360);

            g.setColor(colors[i]);
            g.fillArc(centerX - radius, centerY - radius, radius * 2, radius * 2, startAngle, arcAngle);

            // Calcular posición del texto
            double angle = Math.toRadians(startAngle + arcAngle / 2.0);
            int labelX = (int) (centerX + radius * Math.cos(angle)); // Coordenada x del texto
            int labelY = (int) (centerY - radius * Math.sin(angle)); // Coordenada y del texto

            g.setColor(colors[i]);
            g.fillArc(centerX - radius, centerY - radius, radius * 2, radius * 2, startAngle, arcAngle);

            startAngle += arcAngle;
        }

        // Mostrar referencias de colores, nombres de etiquetas y porcentajes
        int referenceX = centerX + radius + 20; // Posición x de la referencia de colores, nombres y porcentajes
        int referenceY = centerY - radius; // Posición y inicial de la referencia de colores, nombres y porcentajes
        int lineHeight = 20; // Altura de cada línea de referencia

        for (int i = 0; i < labels.size(); i++) {
            g.setColor(colors[i]);
            g.fillRect(referenceX, referenceY + i * lineHeight, 10, 10); // Rectángulo de color

            g.setColor(Color.WHITE);
            String label = labels.get(i);
            int percentage = (int) Math.round(values[i] / (double) total * 100);
            String text = label + " (" + percentage + "%)";
            g.drawString(text, referenceX + 20, referenceY + i * lineHeight + 10); // Nombre de etiqueta y porcentaje
        }

        // Mostrar título en varias líneas
        String titulo = "Porcentaje de veces que se repiten las etiquetas";
        String subtitulo = "en las publicaciones";
        g.setFont(new Font("Arial", Font.BOLD, 16));

        // Calcular posición y dibujar el título centrado
        int y = centerY - radius - g.getFontMetrics().getHeight() - 30; // Posición vertical del título
        String[] lineasTitulo = titulo.split("\\r?\\n");
        for (String linea : lineasTitulo) {
            int x = centerX - g.getFontMetrics().stringWidth(linea) / 2; // Posición horizontal del título centrado
            g.drawString(linea, x, y);
            y += g.getFontMetrics().getHeight(); // Incrementar posición vertical por la altura de la fuente
        }

        // Dibujar el subtítulo centrado
        int x = centerX - g.getFontMetrics().stringWidth(subtitulo) / 2; // Posición horizontal del subtítulo centrado
        g.drawString(subtitulo, x, y + 10);
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


