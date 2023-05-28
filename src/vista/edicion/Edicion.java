package vista.edicion;


import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import modelo.Publicacion;
import modelo.enums.EnumTipoPublicacion;
import modelo.interfaces.IDurable;
import modelo.interfaces.IFiltrable;
import javax.swing.BoxLayout;

/**
 * Clase Edicion.
 * 
 * La clase Edicion representa el panel de edición para una publicación seleccionada.
 * Proporciona opciones de configuración según el tipo de publicación.
 */
public class Edicion extends JPanel {
	
	/**
	 * Crea una instancia de Edicion para una publicación dada.
	 *
	 * @param publicacion : la publicación a editar.
	 */
	public Edicion(Publicacion publicacion) {
		super();
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border lineBorder = BorderFactory.createLineBorder(Color.GRAY, 1);
        Border compoundBorder = BorderFactory.createCompoundBorder(lineBorder, paddingBorder);
        this.setBorder(compoundBorder);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        PanelGeneral panelDuracionGeneral = new PanelGeneral(publicacion);
        add(panelDuracionGeneral);
		if (publicacion.getTipoPublicacion() == EnumTipoPublicacion.AUDIO || publicacion.getTipoPublicacion() == EnumTipoPublicacion.VIDEO) {
			add(new PanelDuracion((IDurable)publicacion, panelDuracionGeneral::cargarDatosDuracion));
		}
		if (publicacion.getTipoPublicacion() == EnumTipoPublicacion.IMAGEN || publicacion.getTipoPublicacion() == EnumTipoPublicacion.VIDEO) {
			add(new PanelFiltros((IFiltrable)publicacion));
		}
	}
}
