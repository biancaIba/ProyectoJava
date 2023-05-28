package utilidades;

import java.io.File;
import java.time.LocalDate;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import modelo.Album;
import modelo.Audio;
import modelo.Imagen;
import modelo.PerfilInstagram;
import modelo.Publicacion;
import modelo.Video;

/**
 * Clase CargaXML.
 */
public class CargaXML {

	/**
	 * Cargar publicaciones XML.
	 *
	 * @param perfil : instancia de perfil Instagram
	 */
	public void cargarPublicacionesXML(PerfilInstagram perfil) {
		try {
			File archivoXML = new File("src/xml/publicaciones.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(archivoXML);
			doc.getDocumentElement().normalize();

			NodeList listaPublicaciones = doc.getElementsByTagName("publicacion");

			for (int i = 0; i < listaPublicaciones.getLength(); i++) {
				Node nodoPublicacion = listaPublicaciones.item(i);

				if (nodoPublicacion.getNodeType() == Node.ELEMENT_NODE) {
					Element elementoPublicacion = (Element) nodoPublicacion;

					Element elementoAudio = (Element) elementoPublicacion.getElementsByTagName("audio").item(0);
					Element elementoImagen = (Element) elementoPublicacion.getElementsByTagName("imagen").item(0);
					Element elementoVideo = (Element) elementoPublicacion.getElementsByTagName("video").item(0);

					String nombrePublicacion = elementoPublicacion.getElementsByTagName("nombrePublicacion").item(0)
							.getTextContent();
					String fechaSubida = elementoPublicacion.getElementsByTagName("fechaSubida").item(0)
							.getTextContent();
					LocalDate fechaSubidaLocalDate = LocalDate.parse(fechaSubida);
					int cantMG = Integer
							.parseInt(elementoPublicacion.getElementsByTagName("cantMG").item(0).getTextContent());

					if (elementoAudio != null) {
						int velocidadBits = Integer
								.parseInt(elementoAudio.getElementsByTagName("velocidadBits").item(0).getTextContent());
						float duracion = Float
								.parseFloat(elementoAudio.getElementsByTagName("duracion").item(0).getTextContent());
						Audio audio = new Audio(nombrePublicacion, fechaSubidaLocalDate, cantMG, velocidadBits,
								duracion);
						cargaEtiquetaComentarioAlbum(elementoAudio, audio);
						perfil.agregarPublicacion(audio);
					} else if (elementoImagen != null) {
						String resolucion = elementoImagen.getElementsByTagName("resolucion").item(0).getTextContent();
						int ancho = Integer
								.parseInt(elementoImagen.getElementsByTagName("ancho").item(0).getTextContent());
						int alto = Integer
								.parseInt(elementoImagen.getElementsByTagName("alto").item(0).getTextContent());
						Imagen imagen = new Imagen(nombrePublicacion, fechaSubidaLocalDate, cantMG, resolucion, ancho,
								alto);
						cargaEtiquetaComentarioAlbum(elementoImagen, imagen);
						perfil.agregarPublicacion(imagen);
					} else if (elementoVideo != null) {
						String resolucion = elementoVideo.getElementsByTagName("resolucion").item(0).getTextContent();
						int cantCuadros = Integer
								.parseInt(elementoVideo.getElementsByTagName("cantCuadros").item(0).getTextContent());
						float duracion = Float
								.parseFloat(elementoVideo.getElementsByTagName("duracion").item(0).getTextContent());
						Video video = new Video(nombrePublicacion, fechaSubidaLocalDate, cantMG, resolucion,
								cantCuadros, duracion);
						cargaEtiquetaComentarioAlbum(elementoVideo, video);
						perfil.agregarPublicacion(video);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Carga etiquetas y comentarios de los álbumes en una publicación.
	 *
	 * @param elementoPublicacion : el elemento XML que contiene los atributos de la
	 *                            publicación.
	 * @param publicacion         : la publicación a la cual se agregarán las
	 *                            etiquetas, comentarios y álbumes.
	 */
	private void cargaEtiquetaComentarioAlbum(Element elementoPublicacion, Publicacion publicacion) {
		NodeList listaEtiquetas = elementoPublicacion.getElementsByTagName("etiqueta");
		for (int i = 0; i < listaEtiquetas.getLength(); i++) {
			String etiqueta = listaEtiquetas.item(i).getTextContent();
			publicacion.agregarEtiqueta(etiqueta);
		}

		NodeList listaComentarios = elementoPublicacion.getElementsByTagName("comentario");
		for (int i = 0; i < listaComentarios.getLength(); i++) {
			String comentario = listaComentarios.item(i).getTextContent();
			publicacion.agregarComentario(comentario);
		}

		NodeList listaAlbumes = elementoPublicacion.getElementsByTagName("album");
		for (int i = 0; i < listaAlbumes.getLength(); i++) {
			String albumNombre = listaAlbumes.item(i).getTextContent();
			Album album = new Album(albumNombre);
			publicacion.agregarAlbum(album);
		}
	}
}
