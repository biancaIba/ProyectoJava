package utilidades;

import java.awt.Color;
import java.util.Random;
/**
 * Clase RandomUtlidades
 * 
 * La clase RandomUtilidades tienen metodos utiles para generar colores random.
 */

public class RandomUtilidades {

	/**
     * Genera colores aleatorios.
     * @param cantidad la cantidad de colores a generar
     * @return un array de colores aleatorios
     */
	  public static Color[] generarColoresAleatorios(int cantidad) {
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
