package utilidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Clase FechaUtilidades.
 * 
 */

public class FechaUtilidades {

	/**
	 * Verifica que la fecha ingresada este en un rango particular.
	 *
	 * @param inicio : fecha de inicio del rango.
	 * @param fin    : fecha fin del rango.
	 * @param fecha  : fecha que deseas corroborar que este en el rango.
	 * @return true, si es verdadero.
	 */
	public static boolean estaFechaEnRango(LocalDate inicio, LocalDate fin, LocalDate fecha) {
		return (fecha.isEqual(inicio) || fecha.isAfter(inicio)) && (fecha.isEqual(fin) || fecha.isBefore(fin));
	}

	/**
	 * Formatea fecha a LocalDate.
	 *
	 * @param fechaStr : fecha con formato String
	 * @return fecha con formato LocalDate
	 * @throws DateTimeParseException the date time parse exception
	 */
	public static LocalDate formatearFecha(String fechaStr) throws DateTimeParseException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(fechaStr, formatter);
	}

	/**
	 * Formatea fecha a String.
	 *
	 * @param fecha: fecha con formato LocalDate.
	 * @return fecha con formato String
	 */
	public static String formatearFechaAString(LocalDate fecha) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return fecha.format(formatter);
	}

	/**
	 * Verifica que una fecha es mayor a otra.
	 *
	 * @param fechaAnterior  : fecha en formato LocalDate
	 * @param fechaPosterior :fecha en formato LocalDate
	 * @return true, si es verdaderp
	 */
	public static boolean fechaEsMayorA(LocalDate fechaAnterior, LocalDate fechaPosterior) {
		return fechaPosterior.isAfter(fechaAnterior);
	}
}
