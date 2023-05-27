package utilidades;

public class TiempoUtilidades {
	public static String duracionFormateada(double tiempoTotal) {
		int horas = (int) (tiempoTotal / 3600);
		int minutos = (int) ((tiempoTotal % 3600) / 60);
		int segundos = (int) (tiempoTotal % 60);
		return String.format("%02d:%02d:%02d", horas, minutos, segundos);
	}
}
