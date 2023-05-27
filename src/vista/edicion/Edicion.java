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

public class Edicion extends JPanel {
	public Edicion(Publicacion publicacion) {
		super();
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setLayout(new GridLayout(2, 1, 0, 15));
		Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border lineBorder = BorderFactory.createLineBorder(Color.GRAY, 1);
        Border compoundBorder = BorderFactory.createCompoundBorder(lineBorder, paddingBorder);
        this.setBorder(compoundBorder);
        
		if (publicacion.getTipoPublicacion() == EnumTipoPublicacion.AUDIO || publicacion.getTipoPublicacion() == EnumTipoPublicacion.VIDEO) {
			add(new PanelDuracion((IDurable)publicacion));
		}
		if (publicacion.getTipoPublicacion() == EnumTipoPublicacion.IMAGEN || publicacion.getTipoPublicacion() == EnumTipoPublicacion.VIDEO) {
			add(new PanelFiltros((IFiltrable)publicacion));
		}
	}
}
