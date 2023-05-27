package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import excepciones.AlbumExistenteExcepcion;
import excepciones.AlbumNoEncontradoExcepcion;
import excepciones.PublicacionNoEncontradaExcepcion;
import excepciones.SinDatosExcepcion;
import modelo.enums.EnumTipoPublicacion;
import modelo.reportes.ReporteAlbum;
import modelo.reportes.ReportePublicacion;
import utilidades.CargaXML;
import utilidades.FechaUtilidades;

public class PerfilInstagram implements Serializable {
	private String nombrePerfil;
	private static final long serialVersionUID = 1L;
	private static PerfilInstagram perfil;
	private Set<Publicacion> listaPublicaciones;
	private List<Album> listaAlbumes;

	private PerfilInstagram() {
		this.listaPublicaciones = new TreeSet<Publicacion>();
		this.listaAlbumes = new ArrayList<Album>();
	}

	public static PerfilInstagram getInstance() {
		if (perfil == null) {
			PerfilInstagram _perfil = PerfilInstagram.recuperaPerfilSerializado();
			perfil = _perfil != null ? _perfil : new PerfilInstagram();
		}
		return perfil;
	}
	
	private static PerfilInstagram recuperaPerfilSerializado() {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Perfil.ser"))) {
			return (PerfilInstagram) in.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("Archivo no encontrado: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Clase PerfilInstagram no encontrada: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error de E/S: " + e.getMessage());
		}
		return null;
	}
	
	public static void serializar() {
		File datos = new File("Perfil.ser");
        if (datos.exists()) {
            datos.delete(); 
        }
        
        try (
        	ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Perfil.ser"))) {
			out.writeObject(PerfilInstagram.getInstance());
		} catch (NotSerializableException e) {
			System.out.println("Un objeto no es serializable: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error de E/S: " + e.getMessage());
		}

	}
	
	public void setNombrePerfil(String nombre) {
		this.nombrePerfil = nombre;
	}
	
	public String getNombrePerfil() {
		return this.nombrePerfil;
	}

	public void cargarPublicaciones() {
		CargaXML cargador = new CargaXML();
		cargador.cargarPublicacionesXML(this);

	}

	public List<Album> getListaAlbumes() {
		return listaAlbumes;
	}

	public void setListaAlbumes(List<Album> listaAlbumes) {
		this.listaAlbumes = listaAlbumes;
	}

	public Set<Publicacion> getPublicaciones() throws SinDatosExcepcion {
		if (listaPublicaciones.isEmpty()) {
			throw new SinDatosExcepcion("No hay datos.");
		}
		Set<String> nombres = new TreeSet<>();
		for (Publicacion p : listaPublicaciones) {
			nombres.add(p.getNombrePublicacion());
		}
		return listaPublicaciones;
	}

	public Set<String> getNombresPublicaciones() throws SinDatosExcepcion {
		if (listaPublicaciones.isEmpty()) {
			throw new SinDatosExcepcion("No hay datos.");
		}
		Set<String> nombres = new TreeSet<>();
		for (Publicacion p : listaPublicaciones) {
			nombres.add(p.getNombrePublicacion());
		}
		return nombres;
	}

	public void agregarPublicacion(Publicacion publi) {
		if (publi != null) {
			this.listaPublicaciones.add(publi);
		}
	}

	public void agregarAlbum(Album album) throws AlbumExistenteExcepcion {
	    if (listaAlbumes.contains(album)) {
	        throw new AlbumExistenteExcepcion("El álbum ya existe");
	    } else {
	        listaAlbumes.add(album);
	    }
	}


	public Album buscarAlbum(String nombre) throws AlbumNoEncontradoExcepcion {
		for (Album album : listaAlbumes) {
			if (album.getNombreAlbum().equals(nombre)) {
				return album;
			}
			for (Album subAlbum : album.getSublistaAlbumes()) {
				if (subAlbum.getNombreAlbum().equals(nombre)) {
					return subAlbum;
				}
			}
		}
		throw new AlbumNoEncontradoExcepcion("El álbum '" + nombre + "' no existe.");
	}

	public Publicacion buscarPublicacion(String nombre) throws PublicacionNoEncontradaExcepcion {
		Iterator<Publicacion> i = listaPublicaciones.iterator();
		while (i.hasNext()) {
			Publicacion publi = i.next();
			if (publi.getNombrePublicacion().equals(nombre))
				return publi;
		}
		throw new PublicacionNoEncontradaExcepcion("La publicación no se encuentra en la lista.");
	}

	public Map<String, List<Publicacion>> agruparPublicacionesPorTipo() {
		Map<String, List<Publicacion>> publicacionesPorTipo = new HashMap<>();
		for (Publicacion publicacion : listaPublicaciones) {
			EnumTipoPublicacion tipoPublicacion = publicacion.getTipoPublicacion();
			publicacionesPorTipo.putIfAbsent(tipoPublicacion.getDisplayName(), new ArrayList<>());
			publicacionesPorTipo.get(tipoPublicacion.getDisplayName()).add(publicacion);
		}
		return publicacionesPorTipo;
	}

	public void mostrarPublicacionesPorTipo() {
		Map<String, List<Publicacion>> publicacionesPorTipo = this.ordenarPublicacionesPorMg();
		for (Map.Entry<String, List<Publicacion>> entry : publicacionesPorTipo.entrySet()) {
			System.out.println("Tipo: " + entry.getKey());
			for (Publicacion publicacion : entry.getValue()) {
				System.out.println(publicacion);
			}
		}
	}

	public Map<String, List<Publicacion>> ordenarPublicacionesPorMg() {
		Map<String, List<Publicacion>> publicacionesPorTipo = this.agruparPublicacionesPorTipo();
		for (Map.Entry<String, List<Publicacion>> entry : publicacionesPorTipo.entrySet()) {
			List<Publicacion> publicaciones = entry.getValue();// obtengo la lista completa
			Collections.sort(publicaciones, new Comparator<Publicacion>() {
				@Override
				public int compare(Publicacion p1, Publicacion p2) {
					// al cambiar el orden de p2 y p1 los ordena descendentemente
					return Integer.compare(p2.getCantidadMG(), p1.getCantidadMG());
				}
			});
		}
		return publicacionesPorTipo;
	}

	public List<ReportePublicacion> cantidadYpromedioDeMg() {
		Map<String, List<Publicacion>> publicacionesPorTipo = this.ordenarPublicacionesPorMg();
		List<ReportePublicacion> reporte = new ArrayList<ReportePublicacion>();
		for (Map.Entry<String, List<Publicacion>> entry : publicacionesPorTipo.entrySet()) {
			String tipoPublicacion = entry.getKey();
			List<Publicacion> publicaciones = entry.getValue();
			float promedio = 0;
			int sum = 0;
			int totalPublicaciones = publicaciones.size();
			for (Publicacion publi : publicaciones) {
				sum += publi.getCantidadMG();
			}
			promedio = (sum / totalPublicaciones);
			reporte.add(new ReportePublicacion(tipoPublicacion, totalPublicaciones, promedio));
		}
		return reporte;
	}

	public void agregarPublicacionDentroAlbum(Album album, Publicacion publicacion) {
		publicacion.agregaAlbumPertenece(album);
		album.agregaPublicacionAalbum(publicacion);
	}

	public void eliminarAlbum(Album albumAEliminar) throws AlbumNoEncontradoExcepcion {
		if (listaAlbumes.contains(albumAEliminar)) {
			desasociarSubalbumDeAlbum(albumAEliminar);
			eliminarAlbumYSubalbumes(albumAEliminar);
		} else {
			throw new AlbumNoEncontradoExcepcion("Album no encontrado");
		}
	}
	
	private void eliminarAlbumYSubalbumes(Album albumAEliminar) throws AlbumNoEncontradoExcepcion {
		if(listaAlbumes.contains(albumAEliminar)) {
			albumAEliminar.desasociarReferenciasAPublicaciones();
			for(Album subAlbum : albumAEliminar.getSublistaAlbumes()) {
				eliminarAlbumYSubalbumes(subAlbum);
			}
			listaAlbumes.remove(albumAEliminar);
		} else {
			throw new AlbumNoEncontradoExcepcion("Album no encontrado");
		}
	}
	
	private void desasociarSubalbumDeAlbum(Album subAlbum) throws AlbumNoEncontradoExcepcion {
		// Recorrer todos los albumes -> Por cada album recorrer todos los subalbumes hasta fijarse si se encontró ese subalbum -> eliminar de la lista de subalbumes.
		// Esto se debería hacer siempre, por mas que el album no sea un subalbum, porque no se tiene esa información hasta que se finalice el recorrido.
		// Esto se evitaría si agregamos un atributo albumPadre a la clase album. Este atributo, si es != null indica que el album actual es un subalbum, y además guarda la referencia a su padre
		// (y recordar que su padre guarda una referencia al subalbum en el listado) -> Doble referencia.
		if(listaAlbumes.contains(subAlbum)) {
			Album albumPadre = subAlbum.getAlbumPadre();
			if(albumPadre != null) {
				albumPadre.desasociarSubAlbum(subAlbum);
			}
		} else {
			throw new AlbumNoEncontradoExcepcion("Album no encontrado");
		}
	}

	public void sacarPublicacionDelAlbum(Publicacion publicacionASacar, Album album)
			throws PublicacionNoEncontradaExcepcion, AlbumNoEncontradoExcepcion {
		album.sacarPublicacion(publicacionASacar);
		publicacionASacar.sacarAlbum(album);
	}

	public List<ReporteAlbum> listadoDeAlbumes(LocalDate inicio, LocalDate fin) {
		List<ReporteAlbum> listaReportesAlbumes = new ArrayList<ReporteAlbum>();
		for (Album album : listaAlbumes) {
			String nombreAlbum = album.getNombreAlbum();
			int contComentarios = 0;
			List<Album> listaSubAlbumes = new ArrayList<>();
			for (Album subAlbum : album.getSublistaAlbumes()) {
				listaSubAlbumes.add(subAlbum);
			}
			ArrayList<Publicacion> publicaciones = (ArrayList<Publicacion>) album.getListaPublicaciones().stream()
					.filter(publicacion -> FechaUtilidades.estaFechaEnRango(inicio, fin, publicacion.getFechaSubida()))
					.collect(Collectors.toList());
			int contPublicaciones = publicaciones.size();
			contComentarios = publicaciones.stream().mapToInt(publicacion -> publicacion.getCantidadDeComentarios())
					.sum();
			ReporteAlbum reporte = new ReporteAlbum(nombreAlbum, contPublicaciones, contComentarios, listaSubAlbumes);
			listaReportesAlbumes.add(reporte);
		}

		return listaReportesAlbumes;
	}

	public Map<String, Integer> cantidadDeEtiquetasPorNombre() {
		Map<String, Integer> etiquetasContador = new HashMap<>();
		for (Publicacion publicacion : listaPublicaciones) {
			ArrayList<String> etiquetas = publicacion.getListaEtiquetas();

			for (String etiqueta : etiquetas) {
				etiquetasContador.put(etiqueta, etiquetasContador.getOrDefault(etiqueta, 0) + 1);
			}
		}
		return etiquetasContador;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Lista de publicaciones:\n");
		for (Publicacion publicacion : listaPublicaciones) {
			sb.append("\t- " + publicacion.toString() + "\n");
		}
		sb.append("Lista de álbumes:\n");
		for (Album album : listaAlbumes) {
			sb.append("\t- " + album.toString() + "\n");
		}
		return sb.toString();
	}
}
