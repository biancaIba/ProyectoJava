package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {
	
	public static boolean estaFechaEnRango(LocalDate inicio, LocalDate fin, LocalDate fecha) {
	    return (fecha.isEqual(inicio) || fecha.isAfter(inicio)) && (fecha.isEqual(fin) || fecha.isBefore(fin));
	}
	
	public static LocalDate formatearFecha(String fechaStr) throws DateTimeParseException {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	return LocalDate.parse(fechaStr, formatter);
    }
    
	public static String formatearFechaAString(LocalDate fecha) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    return fecha.format(formatter);
	}
	
	public static boolean fechaEsMayorA(LocalDate fechaAnterior, LocalDate fechaPosterior) {
    	return fechaPosterior.isAfter(fechaAnterior);
    }
}
