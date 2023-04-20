package instagram;
import java.util.*;
public class Album {
	private String nombreAlbum;
	private ArrayList<Album> sublistaAlbumes;
	
	public String getNombreAlbum() {
		return nombreAlbum;
	}

	public Album(String nombreAlbum) {
		this.nombreAlbum = nombreAlbum;
		//creo una lista vacia de subAlbumes cuando creo el obj Album
		this.sublistaAlbumes = new ArrayList<Album>();
	}

	public void setNombreAlbum(String nombreAlbum) {
		this.nombreAlbum = nombreAlbum;
	}
	
	
}
