package model;
import parser.CargaXML;
import reports.ReportePublicacion;

import java.util.*;
public class PerfilInstagram {
	private TreeSet<Publicacion> listaPublicaciones;
	private ArrayList<Album> listaAlbumes;
	public PerfilInstagram() {
		this.listaPublicaciones = new TreeSet<Publicacion>();
		this.listaAlbumes=new ArrayList<Album>();
	}

	
	public void cargarPublicaciones() {
		CargaXML cargador =new CargaXML();
	    cargador.cargarPublicacionesXML(this);// es como si le pasara perfil osea la instancia donde se ejecuta el cargarPublicaciones() 
	}
	
	public void muestraLista() {
		for(Publicacion p: listaPublicaciones) {
			System.out.println(p.toString());
		}
	}
	
	public void addPublicacion(Publicacion publi) {
		if(publi!= null) {
			listaPublicaciones.add(publi);
	
		}
	}
	
	
	
	public Map<String,List<Publicacion>> agruparPublicacionesPorTipo() {
		Map<String,List<Publicacion>> publicacionesPorTipo=new HashMap();
		for(Publicacion publicacion: listaPublicaciones) {
			String tipoPublicacion=publicacion.getTipoPublicacion();
			//putIfAbsent agrega un par clave-valor solo si la clave no existe en el mapa
			publicacionesPorTipo.putIfAbsent(tipoPublicacion,new ArrayList<>());
			//cuando al metodo get del map le paso una clave me devuelve el valor correspondiente a esa
			//clave. en este caso la clave es una lista vacia q cree arriba
			publicacionesPorTipo.get(tipoPublicacion).add(publicacion);
		}
		return publicacionesPorTipo;	
	}
	
	void muestraPublicacionesPorTipo() {
		Map<String, List<Publicacion>> publicacionesPorTipo = this.ordenarPublicacionesPorMg();
	    for (Map.Entry<String, List<Publicacion>> entry : publicacionesPorTipo.entrySet()) {
	        System.out.println("Tipo: " + entry.getKey());
	        for (Publicacion publicacion : entry.getValue()) {
	            System.out.println(publicacion);
	        }
	    }
	}
	
	public Map<String,List<Publicacion>> ordenarPublicacionesPorMg() {
		Map<String,List<Publicacion>> publicacionesPorTipo=this.agruparPublicacionesPorTipo();
		for(Map.Entry<String, List<Publicacion>> entry: publicacionesPorTipo.entrySet()) {
			List<Publicacion> publicaciones=entry.getValue();//obtengo la lista completa 
			
			Collections.sort(publicaciones, new Comparator<Publicacion>(){
			@Override
		         public int compare(Publicacion p1, Publicacion p2) {
					//al cambiar el orden de p2 y p1 los ordena descendentemente
		            return Integer.compare(p2.getCantMG(), p1.getCantMG());
			}
		});
		
		}
		return publicacionesPorTipo;
	}
	
	
	public List<ReportePublicacion> cantidadYpromedioDeMg(){
		Map<String, List<Publicacion>> publicacionesPorTipo=this.ordenarPublicacionesPorMg();
		List<ReportePublicacion> reporte=new ArrayList<ReportePublicacion>();
		for(Map.Entry<String, List<Publicacion>> entry: publicacionesPorTipo.entrySet()) {
			String tipoPublicacion=entry.getKey();
			List<Publicacion> publicaciones=entry.getValue();
			float promedio=0;
			int sum=0;
			int totalPublicaciones=publicaciones.size();
			for(Publicacion publi: publicaciones) {
				sum+=publi.getCantMG();
			}
			promedio=(sum/totalPublicaciones);
			
			reporte.add(new ReportePublicacion(tipoPublicacion,totalPublicaciones,promedio));
		}
		return reporte;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void confListaReproduccion() {
		/*
		 * Permita la consulta y reproducción de un grupo de publicaciones seleccionadas
		 * de acuerdo a filtros flexibles aplicados a los atributos que se crean más
		 * relevantes. El orden de reproducción puede ser configurable de acuerdo a
		 * algún o algunos atributos.
		 */
	}

	public void resporteAlfabeticoAlbumes() {
		/*
		 * Listado alfabético de Álbumes, detallando para cada uno cantidad de
		 * publicaciones subidas en un rango de fechas solicitado al operador. Incluir
		 * la cantidad de comentarios correspondientes a esas publicaciones.(por
		 * pantalla y en archivos de texto):
		 */
	}

	@Override
	public String toString() {
		return "PerfilInstagram [listaPublicaciones=" + listaPublicaciones + ", listaAlbumes=" + listaAlbumes + "]";
	}
}
