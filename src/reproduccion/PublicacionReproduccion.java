package reproduccion;

import java.time.LocalDate;

import model.EnumTipoPublicacion;
import model.Publicacion;

public class PublicacionReproduccion extends Publicacion {

	private float inicio;
	private float fin;
	
	public PublicacionReproduccion(String nombrePublicacion, LocalDate fechaSubida, int cantMG, float duracion, EnumTipoPublicacion tipoPublicacion ) {
		super(nombrePublicacion, fechaSubida, cantMG, tipoPublicacion);
		this.inicio = 0;
		this.fin = duracion;
	}

	@Override
	public float getDuracion() {
		return  fin - inicio;
		
	}

	public float getInicio() {
		return inicio;
	}

	public void setInicio(float inicio) {
		this.inicio = inicio;
	}

	public float getFin() {
		return fin;
	}

	public void setFin(float fin) {
		this.fin = fin;
	}
	
	
}
