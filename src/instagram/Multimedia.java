/*Cree la clase multimedia pq las clases audio y video comparten los metodos
avanzar y detener pero imagen no lo necesita. Entonces los defino como abstractos
y los redefino en las clases hijas(audio y video). Imagen heredaria de publicaion*/
package instagram;

public abstract class Multimedia extends Publicacion {
	private int duracion;//pq lo comparten audio y video
	 public Multimedia(String nombrePublicacion, String fechaSubida, int cantMG, int duracion) {
	    super(nombrePublicacion, fechaSubida, cantMG);
	    this.duracion = duracion;
	 }
	
	public abstract void avanzar();
	public abstract void detener();
	

	
}
