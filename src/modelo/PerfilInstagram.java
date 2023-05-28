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

/**
 * La Class PerfilInstagram.
 * Implementa Serializable.
 * Modelo Singelton.
 */
public class PerfilInstagram implements Serializable {
	
	/** El nombre del perfil. */
	private String nombrePerfil;
	
	/** Estatico serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** El perfil. */
	private static PerfilInstagram perfil;
	
	/** La lista de publicaciones. */
	private Set<Publicacion> listaPublicaciones;
	
	/** La lista de albumes. */
	private List<Album> listaAlbumes;

	/**
	 * Instacia un perfil.
	 */
	private PerfilInstagram() {
		this.listaPublicaciones = new TreeSet<Publicacion>();
		this.listaAlbumes = new ArrayList<Album>();
	}

	/**
	 * Obtiene la unica instacia de PerfilInstagram.
	 *
	 * @return la unica instacia del perfil
	 */
	public static PerfilInstagram getInstance() {
		if (perfil == null) {
			PerfilInstagram _perfil = PerfilInstagram.recuperaPerfilSerializado();
			perfil = _perfil != null ? _perfil : new PerfilInstagram();
		}
		return perfil;
	}

	/**
	 * Recupera perfil serializado.
	 *
	 * @return la unica instacia del perfil
	 */
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

	/**
	 * Serializa los datos en un archivo "Perfil.ser".
	 */
	public static void serializar() {
		File datos = new File("Perfil.ser");
		if (datos.exists()) {
			datos.delete();
		}
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Perfil.ser"))) {
			out.writeObject(PerfilInstagram.getInstance());
		} catch (NotSerializableException e) {
			System.out.println("Un objeto no es serializable: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error de E/S: " + e.getMessage());
		}
	}

	/**
	 * Setea el nombre del perfil.
	 *
	 * @param nombre
	 */
	public void setNombrePerfil(String nombre) {
		this.nombrePerfil = nombre;
	}

	/**
	 * Obtiene el nombre del perfil.
	 *
	 * @return el nombre del perfil
	 */
	public String getNombrePerfil() {
		return this.nombrePerfil;
	}

	/**
	 * Carga las publicaciones y datos del perfil desde un XML.
	 * Instancia un objeto de la clase CargaXML
	 * e invoca al metodo cargarPublicacionesXML().
	 */
	public void cargarPublicaciones() {
		CargaXML cargador = new CargaXML();
		cargador.cargarPublicacionesXML(this);

	}

	/**
	 * Obtiene la lista de albumes.
	 *
	 * @return la lista de albumes
	 */
	public List<Album> getListaAlbumes() {
		return listaAlbumes;
	}

	/**
	 * Setea la lista de albumes.
	 *
	 * @param listaAlbumes
	 */
	public void setListaAlbumes(List<Album> listaAlbumes) {
		this.listaAlbumes = listaAlbumes;
	}

	/**
	 * Obtiene la lista de publicaciones.
	 *
	 * @return lista de publicaciones
	 * @throws SinDatosExcepcion
	 */
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

	/**
	 * Obtiene los nombres de todas las publicaciones en una lista.
	 *
	 * @return la lista de nombres
	 * @throws SinDatosExcepcion
	 */
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

	/**
	 * Agrega una publicacion a la lista de publicaciones.
	 *
	 * @param publi publicacion
	 */
	public void agregarPublicacion(Publicacion publi) {
		if (publi != null) {
			this.listaPublicaciones.add(publi);
		}
	}

	/**
	 * Agrega un album a la lista de albumes del perfil.
	 *
	 * @param album
	 * @throws AlbumExistenteExcepcion
	 */
	public void agregarAlbum(Album album) throws AlbumExistenteExcepcion {
		if (listaAlbumes.contains(album)) {
			throw new AlbumExistenteExcepcion("El álbum ya existe");
		} else {
			listaAlbumes.add(album);
		}
	}

	/**
	 * Busca el album por nombre.
	 *
	 * @param nombre nombre del album a buscar
	 * @return el album si existe
	 * @throws AlbumNoEncontradoExcepcion
	 */
	public Album buscarAlbum(String nombre) throws AlbumNoEncontradoExcepcion {
		String nombreMinuscula = nombre.toLowerCase();
		for (Album album : listaAlbumes) {
			if (album.getNombreAlbum().equals(nombreMinuscula)) {
				return album;
			}
			for (Album subAlbum : album.getSublistaAlbumes()) {
				if (subAlbum.getNombreAlbum().equals(nombreMinuscula)) {
					return subAlbum;
				}
			}
		}
		throw new AlbumNoEncontradoExcepcion("El álbum '" + nombre + "' no existe.");
	}

	/**
	 * Busca una publicacion por nombre.
	 *
	 * @param nombre nombre de la publicacion a buscar.
	 * @return la publicacion si existe.
	 * @throws PublicacionNoEncontradaExcepcion.
	 */
	public Publicacion buscarPublicacion(String nombre) throws PublicacionNoEncontradaExcepcion {
		String nombreMinuscula = nombre.toLowerCase();
		Iterator<Publicacion> i = listaPublicaciones.iterator();
		while (i.hasNext()) {
			Publicacion publi = i.next();
			if (publi.getNombrePublicacion().equals(nombreMinuscula))
				return publi;
		}
		throw new PublicacionNoEncontradaExcepcion("La publicación no se encuentra en la lista.");
	}

	/**
	 * Agrupa las publicaciones por tipo.
	 *@return una lista que contiene las publicaciones agrupadas por tipo.
	 */
	public Map<String, List<Publicacion>> agruparPublicacionesPorTipo() {
		Map<String, List<Publicacion>> publicacionesPorTipo = new HashMap<>();
		for (Publicacion publicacion : listaPublicaciones) {
			EnumTipoPublicacion tipoPublicacion = publicacion.getTipoPublicacion();
			publicacionesPorTipo.putIfAbsent(tipoPublicacion.getDisplayName(), new ArrayList<>());
			publicacionesPorTipo.get(tipoPublicacion.getDisplayName()).add(publicacion);
		}
		return publicacionesPorTipo;
	}

	/**
	 * Muestra las publicaciones ordenadas por tipo.
	 */
	public void mostrarPublicacionesPorTipo() {
		Map<String, List<Publicacion>> publicacionesPorTipo = this.ordenarPublicacionesPorMg();
		for (Map.Entry<String, List<Publicacion>> entry : publicacionesPorTipo.entrySet()) {
			System.out.println("Tipo: " + entry.getKey());
			for (Publicacion publicacion : entry.getValue()) {
				System.out.println(publicacion);
			}
		}
	}

	/**
	 *Ordenar las publicaciones por cantidad de "Me gusta".
	 *@return una lista que contiene las publicaciones agrupadas por tipo, 
	 *ordenadas por la cantidad de "Me gusta" de forma descendente.
	 */
	public Map<String, List<Publicacion>> ordenarPublicacionesPorMg() {
		Map<String, List<Publicacion>> publicacionesPorTipo = this.agruparPublicacionesPorTipo();
		for (Map.Entry<String, List<Publicacion>> entry : publicacionesPorTipo.entrySet()) {
			List<Publicacion> publicaciones = entry.getValue();
			Collections.sort(publicaciones, new Comparator<Publicacion>() {
				@Override
				public int compare(Publicacion p1, Publicacion p2) {
					return Integer.compare(p2.getCantidadMG(), p1.getCantidadMG());
				}
			});
		}
		return publicacionesPorTipo;
	}

	/**
	 * Ordenar publicaciones por cantidad y promedio de "Me gusta".
	 * @return una lista de objetos ReportePublicacion que contiene el tipo de publicación,
	 * la cantidad total de publicaciones y el promedio de "Me gusta",
	 * ordenados por la cantidad de "Me gusta" de forma descendente
	 */
	public List<ReportePublicacion> ordenarPublicacionPorCantidadYPromedioDeMg() {
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

	/**
	 * Agrega una publicacion dentro de un album.
	 *
	 * @param album ya existente.
	 * @param publicacion a agregar al album.
	 */
	public void agregarPublicacionDentroAlbum(Album album, Publicacion publicacion) {
		publicacion.agregaAlbumPertenece(album);
		album.agregaPublicacionAalbum(publicacion);
	}

	/**
	 * Elimina un album de la lista de albumes.
	 *
	 * @param albumAEliminar
	 * @throws AlbumNoEncontradoExcepcion.
	 */
	public void eliminarAlbum(Album albumAEliminar) throws AlbumNoEncontradoExcepcion {
		if (listaAlbumes.contains(albumAEliminar)) {
			desasociarSubalbumDeAlbum(albumAEliminar);
			eliminarAlbumYSubalbumes(albumAEliminar);
		} else {
			throw new AlbumNoEncontradoExcepcion("Album no encontrado");
		}
	}

	/**
	 * Elimina un album y sus subalbumes.
	 *
	 * @param albumAEliminar
	 * @throws AlbumNoEncontradoExcepcion
	 */
	private void eliminarAlbumYSubalbumes(Album albumAEliminar) throws AlbumNoEncontradoExcepcion {
		if (listaAlbumes.contains(albumAEliminar)) {
			albumAEliminar.desasociarReferenciasAPublicaciones();
			for (Album subAlbum : albumAEliminar.getSublistaAlbumes()) {
				eliminarAlbumYSubalbumes(subAlbum);
			}
			listaAlbumes.remove(albumAEliminar);
		} else {
			throw new AlbumNoEncontradoExcepcion("Album no encontrado");
		}
	}

	/**
	 * Desasocia un subalbum de su album padre.
	 * Recorre todos los albumes. Y por cada album recorre todos los subalbumes 
	 * hasta fijarse si encontró el subalbum y eliminarlo de la lista de subalbumes.
	 * Esto se hace siempre, aunque el album no sea un subalbum,
	 * porque no se tiene esa información hasta que se finaliza el recorrido.
	 *
	 * @param subAlbum
	 * @throws AlbumNoEncontradoExcepcion
	 */
	private void desasociarSubalbumDeAlbum(Album subAlbum) throws AlbumNoEncontradoExcepcion {
		if (listaAlbumes.contains(subAlbum)) {
			Album albumPadre = subAlbum.getAlbumPadre();
			if (albumPadre != null) {
				albumPadre.desasociarSubAlbum(subAlbum);
			}
		} else {
			throw new AlbumNoEncontradoExcepcion("Album no encontrado");
		}
	}

	/**
	 * Saca una publicacion de un album.
	 *
	 * @param publicacionASacar.
	 * @param album.
	 * @throws PublicacionNoEncontradaExcepcion.
	 * @throws AlbumNoEncontradoExcepcion.
	 */
	public void sacarPublicacionDelAlbum(Publicacion publicacionASacar, Album album)
			throws PublicacionNoEncontradaExcepcion, AlbumNoEncontradoExcepcion {
		album.sacarPublicacion(publicacionASacar);
		publicacionASacar.sacarAlbum(album);
	}

	/**
	 * Listado de albumes.
	 *Filtra las publicaciones de los albumes por una fecha indicada de inicio y fin. 
	 *
	 *@param inicio la fecha de inicio del rango de filtrado.
	 *@param fin la fecha de fin del rango de filtrado.
	 *@return la lista de ReporteAlbum con la información filtrada por fecha.
	 */
	public List<ReporteAlbum> listadoDeAlbumesFiltradoPorFecha(LocalDate inicio, LocalDate fin) {
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

	/**
	 * Cantidad de etiquetas por nombre.
	 *
	 * @return una lista que contiene el nombre de la etiqueta como clave y la cantidad de veces que aparece como valor
	 */
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

	/**
	 * To string.
	 *
	 * @return una cadena de texto que representa la lista de publicaciones y la lista de álbumes.
	 */
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
